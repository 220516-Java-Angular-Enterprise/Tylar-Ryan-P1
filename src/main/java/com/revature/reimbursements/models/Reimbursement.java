package com.revature.reimbursements.models;

import java.sql.Blob;
import java.sql.Timestamp;

public class Reimbursement {
    private String id;

    private double amount;

    private Timestamp submitted;

    private Timestamp resolved;

    private String description;

    private String paymentId;

    private String authorId;

    private String resolverId;

    private String statusId = "1";

    private String typeId;

    public Reimbursement(String id, double amount, Timestamp submitted, Timestamp resolved, String description, String paymentId, String authorId, String resolverId, String statusId, String typeId) {
        this.id = id;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.paymentId = paymentId;
        this.authorId = authorId;
        this.resolverId = resolverId;
        this.statusId = statusId;
        this.typeId = typeId;
    }


    public Reimbursement(String description, double amount) {
        this.amount = amount;
        this.description = description;
    }

    public Reimbursement(String id, double amount, String description, String statusId) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.statusId = statusId;
    }

    public Reimbursement(String id, String statusId) {
        this.id = id;
        this.statusId = statusId;
    }

    public Reimbursement(String id, String resolverId, String statusId, Timestamp resolved) {
        this.id = id;
        this.resolverId = resolverId;
        this.statusId = statusId;
        this.resolved = resolved;
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

    public Timestamp getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Timestamp submitted) {
        this.submitted = submitted;
    }

    public Timestamp getResolved() {
        return resolved;
    }

    public void setResolved(Timestamp resolved) {
        this.resolved = resolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getResolverId() {
        return resolverId;
    }

    public void setResolverId(String resolverId) {
        this.resolverId = resolverId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", statusId='" + statusId + '\'' +
                '}';
    }
}
