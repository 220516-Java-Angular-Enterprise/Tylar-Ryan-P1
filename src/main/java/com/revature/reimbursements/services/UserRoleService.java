package com.revature.reimbursements.services;

import com.revature.reimbursements.daos.UserRoleDAO;
import com.revature.reimbursements.util.annotations.Inject;

import java.sql.ResultSet;

public class UserRoleService {
    @Inject
    private final UserRoleDAO userRoleDAO;

    public UserRoleService(UserRoleDAO userRoleDAO) {
        this.userRoleDAO = userRoleDAO;
    }

    public ResultSet getRoleById(String id) {
        return userRoleDAO.getRoleById(id);
    }
}
