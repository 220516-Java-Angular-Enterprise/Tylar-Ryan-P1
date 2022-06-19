package com.revature.reimbursements.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.reimbursements.dtos.request.NewUserRequest;
import com.revature.reimbursements.dtos.request.ReimbursementRequest;
import com.revature.reimbursements.dtos.responses.Principal;
import com.revature.reimbursements.models.Reimbursement;
import com.revature.reimbursements.models.User;
import com.revature.reimbursements.services.*;
import com.revature.reimbursements.util.annotations.Inject;
import com.revature.reimbursements.util.custom_exceptions.InvalidRequestException;
import com.revature.reimbursements.util.custom_exceptions.ResourceConflictException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ReimbursementServlet extends HttpServlet {
    @Inject
    private final ObjectMapper mapper;

    private final ReimbursementService reimbursementService;

    private final TokenService tokenService;

    public ReimbursementServlet(ObjectMapper mapper, ReimbursementService reimbursementService, TokenService tokenService) {
        this.mapper = mapper;
        this.reimbursementService = reimbursementService;
        this.tokenService = tokenService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ReimbursementRequest reimbursementRequest = mapper.readValue(req.getInputStream(), ReimbursementRequest.class);
            String[] uris = req.getRequestURI().split("/");

            Reimbursement reimbursement = reimbursementService.approveReimb(reimbursementRequest);
            resp.setStatus(201); // CREATED
            resp.setContentType("application/json");
            resp.getWriter().write(mapper.writeValueAsString(reimbursement.getAmount()));
            resp.getWriter().write(mapper.writeValueAsString(reimbursement.getDescription()));
            //resp.getWriter().write(mapper.writeValueAsString(createdUser.getRole()));
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
}
