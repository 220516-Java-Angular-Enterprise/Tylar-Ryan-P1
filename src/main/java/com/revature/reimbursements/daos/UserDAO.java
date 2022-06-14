package com.revature.reimbursements.daos;

import com.revature.reimbursements.models.User;
import com.revature.reimbursements.util.custom_exceptions.InvalidSQLException;
import com.revature.reimbursements.util.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements CrudDAO<User> {

    @Override
    public void save(User obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
//            PreparedStatement ps = con.prepareStatement("INSERT INTO ers_user (user_id, username, email, password, given_name, surname, is_active, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
//            PreparedStatement ps = con.prepareStatement("INSERT INTO ers_user (user_id, username, email, password, given_name, surname, is_active, role) VALUES (?, ?, ?, crypt(?, gen_salt('bf')), ?, ?, ?, ?)");
//            PreparedStatement ps = con.prepareStatement("INSERT INTO users (id, username, password, role) VALUES (?, ?, crypt(?, gen_salt('bf')), ?)");
//            PreparedStatement ps = con.prepareStatement("INSERT INTO users (id, username, password, role) VALUES (?, ?, ?, ?)");

            //delete crypt
            PreparedStatement ps = con.prepareStatement("INSERT INTO ers_user (user_id, username, email, password, given_name, surname, is_active, role_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

            ps.setString(1,obj.getUserId());
            ps.setString(2, obj.getUsername());
            ps.setString(3, obj.getEmail());
            ps.setString(4, obj.getPassword());
            ps.setString(5, obj.getGivenName());
            ps.setString(6, obj.getSurname());
            ps.setBoolean(7, obj.isActive());
            ps.setString(8, obj.getRoleId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            //throw new RuntimeException("An error occurred when tyring to get data from to the database.");
        }
    }

    @Override
    public void update(User obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public User getById(String id) {
        User user = new User();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users where id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user = new User(rs.getString("username"), rs.getString("password"), rs.getString("role"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when tyring to get data from to the database.");
        }

        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User(); // user -> null
                user.setUserId(rs.getString("id")); // user (id) -> 1232abce231dsf
                user.setUsername(rs.getString("username")); // user (username) -> bduong0929
                user.setPassword(rs.getString("password")); // user (password) -> P@ssw0rd
                user.setRoleId(rs.getString("role")); // user (role) -> DEFAULT

                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when tyring to get data from to the database.");
        }

        return users;
    }

    public List<String> getAllUsernames() {
        List<String> usernames = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT username FROM users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // String username = rs.getString("username");
                // usernames.add(username);

                usernames.add(rs.getString("username").toLowerCase());
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            //throw new RuntimeException("An error occurred when tyring to get data from to the database.");
        }

        return usernames;
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        User user = null;

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User(rs.getString("username"), rs.getString("password"), rs.getString("role"));
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to get data from to the database.");
        }

        return user;
    }

    public List<User> getUsersByUsername(String name) {
        List<User> users = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username LIKE ?");
            ps.setString(1, name + '%');
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                users.add(new User(rs.getString("username"), rs.getString("password"), rs.getString("role")));
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to get data from to the database.");
        }

        return users;
    }
}