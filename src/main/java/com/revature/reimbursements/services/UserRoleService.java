package com.revature.reimbursements.services;

import com.revature.reimbursements.daos.UserDAO;
import com.revature.reimbursements.daos.UserRoleDAO;
import com.revature.reimbursements.models.User;
import com.revature.reimbursements.models.UserRole;
import com.revature.reimbursements.util.annotations.Inject;

import java.util.List;

public class UserRoleService {


    @Inject
    private final UserRoleDAO userRoleDAO;

    @Inject
    public UserRoleService(UserRoleDAO userRoleDAO) {
        this.userRoleDAO = userRoleDAO;
    }

    public UserRole getUserRoleByName(String name) {
        return userRoleDAO.getByRoleName(name);
    }
}
