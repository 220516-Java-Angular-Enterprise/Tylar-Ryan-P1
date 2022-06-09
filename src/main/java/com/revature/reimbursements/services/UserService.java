package com.revature.reimbursements.services;

import com.revature.reimbursements.daos.UserDAO;
import com.revature.reimbursements.dtos.request.LoginRequest;
import com.revature.reimbursements.dtos.request.NewUserRequest;
import com.revature.reimbursements.models.User;
import com.revature.reimbursements.util.annotations.Inject;
import com.revature.reimbursements.util.custom_exceptions.InvalidRequestException;
import com.revature.reimbursements.util.custom_exceptions.InvalidUserException;
import com.revature.reimbursements.util.custom_exceptions.ResourceConflictException;

import java.util.List;
import java.util.UUID;

public class UserService {
    private final UserDAO userDAO;

    @Inject
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User login(LoginRequest request) {
        /* List<User> users = new ArrayList<>() */
        /* users = userDAO.getAll() */

        User user = new User();
        List<User> users = userDAO.getAll();


        return isValidCredentials(user);
    }

    public User register(NewUserRequest request) {
        User user = request.extractUser();

        if (isNotDuplicateUsername(user.getUsername())) {
            if (isValidUsername(user.getUsername())) {
                if (isValidPassword(user.getPassword())) {
                    user.setId(UUID.randomUUID().toString());
                    userDAO.save(user);
                } else throw new InvalidRequestException("Invalid password. Minimum eight characters, at least one letter, one number and one special character.");
            } else throw new InvalidRequestException("Invalid username. Username needs to be 8-20 characters long.");
        } else throw new ResourceConflictException("Username is already taken :(");

        return user;
    }

    public User getUserById(String id) {
        return userDAO.getById(id);
    }

    private boolean isValidUsername(String username) {
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    }

    private boolean isNotDuplicateUsername(String username) {
        return !userDAO.getAllUsernames().contains(username);
    }

    private boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");
    }

    private User isValidCredentials(User user) {
        if (user.getUsername() == null && user.getPassword() == null)
            throw new InvalidUserException("Incorrect username and password.");
        else if (user.getUsername() == null) throw new InvalidUserException("Incorrect username.");
        else if (user.getPassword() == null) throw new InvalidUserException("Incorrect password.");

        return user;
    }
}