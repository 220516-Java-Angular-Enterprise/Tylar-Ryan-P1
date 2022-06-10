package com.revature.reimbursements.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.reimbursements.dtos.request.LoginRequest;
import com.revature.reimbursements.dtos.responses.Principal;
import com.revature.reimbursements.services.TokenService;
import com.revature.reimbursements.services.UserService;
import com.revature.reimbursements.util.annotations.Inject;
import com.revature.reimbursements.util.custom_exceptions.AuthenticationException;
import com.revature.reimbursements.util.custom_exceptions.InvalidRequestException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthServlet extends HttpServlet {
    @Inject
    private final ObjectMapper mapper;
    private final UserService userService;

    private final TokenService tokenService;

    public AuthServlet(ObjectMapper mapper, UserService userService, TokenService tokenService) {
        this.mapper = mapper;
        this.userService = userService;
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
        resp.getWriter().write("<h1>Hello</h1>");
    }
}