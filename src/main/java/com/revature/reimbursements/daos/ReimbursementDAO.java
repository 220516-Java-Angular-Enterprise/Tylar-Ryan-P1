package com.revature.reimbursements.daos;

import com.revature.reimbursements.models.Reimbursement;
import com.revature.reimbursements.models.User;
import com.revature.reimbursements.util.custom_exceptions.InvalidSQLException;
import com.revature.reimbursements.util.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDAO implements CrudDAO<Reimbursement> {
    @Override
    public void save(Reimbursement obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {

            //delete crypt
            PreparedStatement ps = con.prepareStatement("INSERT INTO reimbursement (reimb_id, amount, submitted, resolved, description, payment_id, author_id, resolver_id, status_id, type_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            ps.setString(1,obj.getId());
            ps.setDouble(2, obj.getAmount());
            ps.setTimestamp(3, obj.getSubmitted());
            ps.setTimestamp(4, obj.getResolved());
            ps.setString(5, obj.getDescription());
            ps.setString(6, obj.getPaymentId());
            ps.setString(7, obj.getAuthorId());
            ps.setString(8, obj.getResolverId());
            ps.setString(9, obj.getStatusId());
            ps.setString(10, obj.getTypeId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            //throw new RuntimeException("An error occurred when tyring to get data from to the database.");
        }
    }

    @Override
    public void update(Reimbursement obj) {

    }

    public void updateById(String reimbId, String statusId, String resolverId){
        try(Connection con = ConnectionFactory.getInstance().getConnection()){

            PreparedStatement ps = con.prepareStatement("UPDATE reimbursement SET (status_id,resolver_id) = (?,?) WHERE reimb_id = ?");
            ps.setString(1, statusId);
            ps.setString(2, resolverId);
            ps.setString(3, reimbId);
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            throw new RuntimeException("An error occurred when tyring to get data from to the database.");
        }
    }

    @Override
    public void delete(String s) {

    }

    @Override
    public Reimbursement getById(String id) {
        Reimbursement reimbursement = null;

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM reimbursement WHERE reimb_id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                reimbursement = new Reimbursement(rs.getString("reimb_id"), rs.getDouble("amount"), rs.getString("description"), rs.getString("status_id"));
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to get data from to the database.");
        }

        return reimbursement;
    }

    public List<Reimbursement> getByStatusId(){
        List<Reimbursement> reimbursement = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM reimbursement WHERE status_id = ?");
            ps.setString(1, "1");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                reimbursement.add(new Reimbursement(rs.getString("reimb_id"), rs.getDouble("amount"), rs.getString("description"), rs.getString("status_id")));
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to get data from to the database.");
        }

        return reimbursement;
    }

    @Override
    public List<Reimbursement> getAll() {
        return null;
    }
}
