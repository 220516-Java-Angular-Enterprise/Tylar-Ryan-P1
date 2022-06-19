package com.revature.reimbursements.services;

import com.revature.reimbursements.daos.ReimbursementDAO;
import com.revature.reimbursements.daos.UserDAO;
import com.revature.reimbursements.dtos.request.ReimbursementRequest;
import com.revature.reimbursements.models.Reimbursement;
import com.revature.reimbursements.models.User;
import com.revature.reimbursements.util.annotations.Inject;

import java.util.UUID;

public class ReimbursementService {
    @Inject
    private final ReimbursementDAO reimbursementDAO;


    @Inject
    public ReimbursementService(ReimbursementDAO reimbursementDAO) {
        this.reimbursementDAO = reimbursementDAO;
    }


    public Reimbursement approveReimb(ReimbursementRequest reimbursementRequest){
        Reimbursement reimbursement = reimbursementRequest.extractReimb();

        reimbursement.setId(UUID.randomUUID().toString());
        reimbursement.setDescription(reimbursement.getDescription());
        reimbursement.setAmount(reimbursement.getAmount());

        reimbursementDAO.save(reimbursement);

        return reimbursement;
    }
}
