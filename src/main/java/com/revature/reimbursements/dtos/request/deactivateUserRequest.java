package com.revature.reimbursements.dtos.request;

public class deactivateUserRequest {
    String userId;

    public deactivateUserRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String username) {
        this.userId = username;
    }
}
