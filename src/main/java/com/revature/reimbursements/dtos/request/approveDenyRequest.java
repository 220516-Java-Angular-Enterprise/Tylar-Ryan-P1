package com.revature.reimbursements.dtos.request;

public class approveDenyRequest {
    private String username;
    private String reqId;
    private String approveDeny;

    public approveDenyRequest(String username, String reqId, String approveDeny) {
        this.username = username;
        this.reqId = reqId;
        this.approveDeny = approveDeny;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getApproveDeny() {
        return approveDeny;
    }

    public void setApproveDeny(String approveDeny) {
        this.approveDeny = approveDeny;
    }
}
