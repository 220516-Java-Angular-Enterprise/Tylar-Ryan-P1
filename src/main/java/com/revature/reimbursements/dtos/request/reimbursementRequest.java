package com.revature.reimbursements.dtos.request;

public class reimbursementRequest {
    private double amount;
    private String type;
    private String req_id;

    public reimbursementRequest(double amount, String type) {
        this.amount = amount;
        this.type = type;
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
}
