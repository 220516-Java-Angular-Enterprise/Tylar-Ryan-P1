package com.revature.reimbursements.services;

import com.revature.reimbursements.daos.ReimbursementDAO;
import com.revature.reimbursements.daos.UserDAO;
import com.revature.reimbursements.dtos.request.ApproveDenyRequest;
import com.revature.reimbursements.dtos.request.ReimbursementRequest;
import com.revature.reimbursements.models.Reimbursement;
import com.revature.reimbursements.models.User;
import com.revature.reimbursements.util.annotations.Inject;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class ReimbursementService {
    @Inject
    private final ReimbursementDAO reimbursementDAO;


    @Inject
    public ReimbursementService(ReimbursementDAO reimbursementDAO) {
        this.reimbursementDAO = reimbursementDAO;
    }


    public Reimbursement requestReimb(ReimbursementRequest reimbursementRequest){
        Reimbursement reimbursement = reimbursementRequest.extractReimb();

        reimbursement.setId(UUID.randomUUID().toString());
        reimbursement.setDescription(reimbursement.getDescription());
        reimbursement.setAmount(reimbursement.getAmount());
        reimbursement.setStatusId(reimbursement.getStatusId());

        reimbursementDAO.save(reimbursement);

        return reimbursement;
    }

    public String approveReimb(String reimbId, String approveDeny, String resolverId){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        Reimbursement reimbursement = approveDenyRequest.extractApproveDeny();
//
//        reimbursement.setId(reimbursement.getId());
//        reimbursement.setStatusId(approveDenyRequest.getApproveDeny());
//        reimbursement.setResolved(timestamp);
//        reimbursement.setResolverId(approveDenyRequest.getResolverId());

        //update status id where reimb id == id

        //update resolved where reimb id == id

        //update resolver id where reimb id == id

        //approveDenyRequest.setReimbId(approveDenyRequest.getReimbId());

        reimbursementDAO.updateById(reimbId, approveDeny, resolverId);

        return (approveDeny);
    }
    public List<Reimbursement> getByStatusId(){
        return reimbursementDAO.getByStatusId();
    }
}
