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

    public void deleteUser(String request){
        //User user = request.extractUser();
      //  user.setUserId(user.getUserId());
        //user = userDAO.getById(request.getUserId());
        userDAO.delete("c3d92734-4f55-4d8b-bf69-46a2006a35cf");


    }

}
