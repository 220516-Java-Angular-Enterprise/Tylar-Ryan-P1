package com.revature.reimbursements.servlets;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.reimbursements.dtos.request.DeactivateUserRequest;
import com.revature.reimbursements.dtos.request.LoginRequest;
import com.revature.reimbursements.dtos.responses.Principal;
import com.revature.reimbursements.models.User;
import com.revature.reimbursements.services.AdminService;
import com.revature.reimbursements.services.TokenService;
import com.revature.reimbursements.services.UserService;
import com.revature.reimbursements.util.annotations.Inject;
import com.revature.reimbursements.util.custom_exceptions.AuthenticationException;
import com.revature.reimbursements.util.custom_exceptions.InvalidRequestException;
import com.revature.reimbursements.util.custom_exceptions.ResourceConflictException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AuthServlet extends HttpServlet {
    @Inject
    private final ObjectMapper mapper;
    private final UserService userService;
    private final AdminService adminService;

    private final TokenService tokenService;

    public AuthServlet(ObjectMapper mapper, UserService userService, AdminService adminService, TokenService tokenService) {
        this.mapper = mapper;
        this.userService = userService;
        this.adminService = adminService;
        this.tokenService = tokenService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            LoginRequest request = mapper.readValue(req.getInputStream(), LoginRequest.class);
            Principal principal = new Principal(userService.login(request));

            /* Stateful session management. */
            String token = tokenService.generateToken(principal);
            resp.setHeader("Authorization", token);
            resp.setContentType("application/json");
            resp.getWriter().write(mapper.writeValueAsString(principal));
        } catch (InvalidRequestException e) {
            resp.setStatus(404);
        } catch (AuthenticationException e) {
            resp.setStatus(401);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Principal requester = tokenService.extractRequesterDetails(req.getHeader("Authorization"));

        if (requester == null) {
            resp.setStatus(401); // UNAUTHORIZED
            return;
        }

        if (!requester.getRole().equals("ADMIN")) {
            resp.setStatus(403); // FORBIDDEN
            return;
        }

        List<User> users = userService.getAllUsers();
        resp.setContentType("application/json");
        resp.getWriter().write(mapper.writeValueAsString(users));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            DeactivateUserRequest deactivateUserRequest = mapper.readValue(req.getInputStream(), DeactivateUserRequest.class);
            String[] uris = req.getRequestURI().split("/");

            if (uris.length == 4 && uris[3].equals("username")) {
                Principal requester = tokenService.extractRequesterDetails(req.getHeader("Authorization"));

                if (requester == null) {
                    resp.setStatus(401); // UNAUTHORIZED
                    return;
                }

                if (!requester.getRole().equals("ADMIN")) {
                    resp.setStatus(403); // FORBIDDEN
                    return;
                }

                if (deactivateUserRequest.getUserId().equals("")) {
                    resp.setStatus(404);
                    return;
                }

                return;
            }

            adminService.deleteUser(deactivateUserRequest.getUserId());
            resp.setStatus(201); // Deleted
            resp.setContentType("application/json");
            resp.getWriter().write(mapper.writeValueAsString(deactivateUserRequest.getUserId()));

        } catch (InvalidRequestException e) {
            resp.setStatus(404); // BAD REQUEST
        } catch (ResourceConflictException e) {
            resp.setStatus(409); // RESOURCE CONFLICT
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
        }
    }
}



//        DeactivateUserRequest deactivateUserRequest = mapper.readValue(req.getInputStream(), DeactivateUserRequest.class);
//        Principal requester = tokenService.extractRequesterDetails(req.getHeader("Authorization"));
//
//        if (requester == null) {
//            resp.setStatus(401); // UNAUTHORIZED
//            return;
//        }
//
//        if (!requester.getRole().equals("ADMIN")) {
//            resp.setStatus(403); // FORBIDDEN
//            return;
//        }
//
//        User deletedUser = adminService.deleteUser(deactivateUserRequest);
//        resp.setStatus(201); // Deleted
//        resp.setContentType("application/json");
//        resp.getWriter().write(mapper.writeValueAsString(deletedUser.toString()));
//
//    }
//}