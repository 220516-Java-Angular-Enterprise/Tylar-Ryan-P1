package com.revature.reimbursements.services;

import com.revature.reimbursements.daos.UserDAO;
import com.revature.reimbursements.dtos.request.DeactivateUserRequest;
import com.revature.reimbursements.models.User;
import com.revature.reimbursements.util.annotations.Inject;

public class AdminService {
    @Inject
    private final UserDAO userDAO;

    @Inject
    public AdminService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User deleteUser(DeactivateUserRequest request){
        User user;
        request.setUserId(request.getUserId());
        user = userDAO.getById(request.getUserId());
        userDAO.delete(request.getUserId());

        return user;
    }

}
