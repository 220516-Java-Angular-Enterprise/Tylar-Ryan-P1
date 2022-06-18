package com.revature.reimbursements.daos;

import com.revature.reimbursements.models.User;
import com.revature.reimbursements.models.UserRole;
import com.revature.reimbursements.services.UserService;
import com.revature.reimbursements.util.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRoleDAO implements CrudDAO<UserRole> {
    @Override
    public void save(UserRole obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {

            //delete crypt
            PreparedStatement ps = con.prepareStatement("INSERT INTO user_role (role_id, role) VALUES (?, ?)");

            ps.setString(1,obj.getRoleId());
            ps.setString(2, obj.getRole());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            //throw new RuntimeException("An error occurred when tyring to get data from to the database.");
        }
    }

    @Override
    public void update(UserRole obj) {

    }


    @Override
    public void delete(String s) {

    }

    @Override
    public UserRole getById(String roleId) {
        return null;
    }

    public ResultSet getRoleById(String roleId){
        ResultSet resultSet;

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT role FROM user_role where role_id = ?");
            ps.setString(1, roleId);
            resultSet= ps.executeQuery();
            //rs = String.valueOf(ps.executeQuery());

            //userRole = rs.getString("role");

        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when tyring to get data from to the database.");
        }

        return resultSet;
    }

    @Override
    public List<UserRole> getAll() {
        return null;
    }
}
