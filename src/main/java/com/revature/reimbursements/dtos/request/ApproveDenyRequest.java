package com.revature.reimbursements.dtos.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.reimbursements.models.Reimbursement;
import com.revature.reimbursements.models.User;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApproveDenyRequest {

    private String resolverId;
    private String reimbId;
    private String approveDeny;

    public ApproveDenyRequest() {
        super();
    }

    public ApproveDenyRequest(String resolverId, String reimbId, String approveDeny) {
        this.resolverId = resolverId;
        this.reimbId = reimbId;
        this.approveDeny = approveDeny;
    }


    public String getReimbId() {
        return reimbId;
    }

    public void setReimbId(String reimbId) {
        this.reimbId = reimbId;
    }

    public String getApproveDeny() {
        return approveDeny;
    }

    public void setApproveDeny(String approveDeny) {
        this.approveDeny = approveDeny;
    }

    public String getResolverId() {
        return resolverId;
    }

    public void setResolverId(String resolverId) {
        this.resolverId = resolverId;
    }

    public Reimbursement extractApproveDeny() {
        return new Reimbursement(reimbId, approveDeny);
    }

}
