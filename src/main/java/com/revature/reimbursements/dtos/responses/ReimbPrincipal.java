package com.revature.reimbursements.dtos.responses;

import com.revature.reimbursements.models.Reimbursement;

public class ReimbPrincipal {
    private String id;
    private double amount;
    private String description;
    private String statusId;

    public ReimbPrincipal(Reimbursement reimbursement) {
        this.id = reimbursement.getId();
        this.amount = reimbursement.getAmount();
        this.description = reimbursement.getDescription();
        this.statusId = reimbursement.getStatusId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    @Override
    public String toString() {
        return "ReimbPrincipal{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", statusId='" + statusId + '\'' +
                '}';
    }
}
