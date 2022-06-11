package com.revature.reimbursements.dtos.request;

public class deactivateUserRequest {
    String username;

    public deactivateUserRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
