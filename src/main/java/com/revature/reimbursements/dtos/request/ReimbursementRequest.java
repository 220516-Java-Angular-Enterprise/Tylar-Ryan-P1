package com.revature.reimbursements.dtos.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.reimbursements.models.Reimbursement;
import com.revature.reimbursements.models.User;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReimbursementRequest {
    private double amount;

    private String type;

    private String description;

    public ReimbursementRequest(){super();}

    public ReimbursementRequest(double amount, String type, String description) {
        this.amount = amount;
        this.type = type;
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Reimbursement extractReimb() {
        return new Reimbursement(description, amount);
    }

}
