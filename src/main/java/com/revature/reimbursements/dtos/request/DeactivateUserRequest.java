package com.revature.reimbursements.dtos.request;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.reimbursements.models.User;

//@JsonIgnoreProperties(ignoreUnknown = true)
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

    public User extractUser() {
        return new User(userId);
    }

}
