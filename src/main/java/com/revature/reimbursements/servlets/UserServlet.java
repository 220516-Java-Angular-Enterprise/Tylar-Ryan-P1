package com.revature.reimbursements.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.reimbursements.dtos.request.NewUserRequest;
import com.revature.reimbursements.models.User;
import com.revature.reimbursements.services.UserService;
import com.revature.reimbursements.util.custom_exceptions.InvalidRequestException;
import com.revature.reimbursements.util.custom_exceptions.ResourceConflictException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    private final ObjectMapper mapper;
    private final UserService userService;

    public UserServlet(ObjectMapper mapper, UserService userService) {
        this.mapper = mapper;
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("<h1>Users work!</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            NewUserRequest request = mapper.readValue(req.getInputStream(), NewUserRequest.class);
            User createdUser = userService.register(request);
            resp.setStatus(200);
            resp.setContentType("application/json");
            resp.getWriter().write(mapper.writeValueAsString(createdUser.getId()));
        }catch(InvalidRequestException e){
            resp.setStatus(404);
        }catch(ResourceConflictException e){
            resp.setStatus(409);
        }catch(Exception e){
            e.printStackTrace();
            resp.setStatus(500);
        }
    }



























}
