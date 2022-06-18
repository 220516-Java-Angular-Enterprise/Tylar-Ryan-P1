package com.revature.reimbursements.dtos.request;

public class DeactivateUserRequest {
    private String userId;

    public DeactivateUserRequest() {
        super();
    }

    public DeactivateUserRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
