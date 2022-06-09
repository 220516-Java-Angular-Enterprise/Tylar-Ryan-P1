package com.revature.reimbursements.daos;

import com.revature.reimbursements.models.User;
import com.revature.reimbursements.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDAO implements CrudDAO<User>{
    Connection con = DatabaseConnection.getCon();
    String path = "src/main/resources/database/user.txt";

    public UserDAO() {
    }

    @Override
    public void save(User obj) {

    }

    @Override
    public void update(User obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public User getById(String id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    public List<String> getAllUsernames() {
        List<String> usernames = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT username FROM users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // String username = rs.getString("username");
                // usernames.add(username);

                usernames.add(rs.getString("username").toLowerCase());
            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when tyring to get data from to the database.");
        }

        return usernames;
    }
}
