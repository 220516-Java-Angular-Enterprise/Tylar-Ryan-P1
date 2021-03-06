package com.revature.reimbursements.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.reimbursements.dtos.request.DeactivateUserRequest;
import com.revature.reimbursements.dtos.request.NewUserRequest;
import com.revature.reimbursements.models.User;
import com.revature.reimbursements.services.AdminService;
import com.revature.reimbursements.services.TokenService;
import com.revature.reimbursements.services.UserRoleService;
import com.revature.reimbursements.services.UserService;
import com.revature.reimbursements.dtos.responses.Principal;
import com.revature.reimbursements.util.annotations.Inject;
import com.revature.reimbursements.util.custom_exceptions.InvalidRequestException;
import com.revature.reimbursements.util.custom_exceptions.ResourceConflictException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//extends the capabilities of server by means of request-response programming model
public class UserServlet extends HttpServlet {

    /*we need mapper to translate between java and json when sending requests and vise versa when receiving*/

    @Inject
    private final ObjectMapper mapper;
    private final UserService userService;

    private final UserRoleService userRoleService;
    private final AdminService adminService;

    private final TokenService tokenService;

    @Inject
    public UserServlet(ObjectMapper mapper, UserService userService, UserRoleService userRoleService, TokenService tokenService, AdminService adminService) {
        this.mapper = mapper;
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.tokenService = tokenService;
        this.adminService = adminService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            NewUserRequest userRequest = mapper.readValue(req.getInputStream(), NewUserRequest.class);

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

                if (userRequest.getUsername().equals("")) {
                    resp.setStatus(404);
                    return;
                }

                List<User> users = userService.getUserByUsername(userRequest.getUsername());
                resp.setContentType("application/json");
                resp.getWriter().write(mapper.writeValueAsString(users));
                return;
            }


            User createdUser = userService.register(userRequest);
            resp.setStatus(201); // CREATED
            resp.setContentType("application/json");
            resp.getWriter().write(mapper.writeValueAsString(createdUser.getUserId()));
            resp.getWriter().write(mapper.writeValueAsString(createdUser.getRoleId()));
           // resp.getWriter().write(mapper.writeValueAsString(userRoleService.getRoleById(createdUser.getUserId())));
        } catch (InvalidRequestException e) {
            resp.setStatus(404); // BAD REQUEST
        } catch (ResourceConflictException e) {
            resp.setStatus(409); // RESOURCE CONFLICT
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
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            DeactivateUserRequest userRequest = mapper.readValue(req.getInputStream(), DeactivateUserRequest.class);

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

                if (userRequest.getUserId().equals("")) {
                    resp.setStatus(404);
                    return;
                }

                //User user = userService.getUserById(userRequest.getUserId());
                //resp.setContentType("application/json");
                //resp.getWriter().write(mapper.writeValueAsString(user));
                return;
            }

           // User deletedUser = adminService.deleteUser(userRequest);
            resp.setStatus(201); // DELETED
            resp.setContentType("application/json");
            //resp.getWriter().write(mapper.writeValueAsString(deletedUser.getUserId()));
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