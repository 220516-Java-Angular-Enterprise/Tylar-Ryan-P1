package com.revature.reimbursements.dtos.request;

public class DeactivateUserRequest {
    String userId;

    public DeactivateUserRequest() {
        super();
    }
    public DeactivateUserRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String username) {
        this.userId = username;
    }
}
