package com.revature.reimbursements.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.revature.reimbursements.daos.UserDAO;
import com.revature.reimbursements.models.User;
import com.revature.reimbursements.util.annotations.Inject;


public class NewUserRequest {
    //@Inject
    //private final UserDAO userDAO;
    private String username;
    private String password;
    private String email;
   // @JsonProperty("given_name")
    private String givenName;
    private boolean isActive;
    private String surname;
    private String role = "DEFAULT";

    public NewUserRequest() {
        super();
    }

    public NewUserRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
//jsonpart
    public NewUserRequest(String username, String email, String password, String givenName, String surname, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.givenName = givenName;
        this.surname = surname;
        this.role = role;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public User extractUser() {
        String roleId;
        if (role.equals("ADMIN")) {
            roleId = "1";
        } else if (role.equals("FINANCE MANAGER")) {
            roleId = "2";
        } else {
            roleId = "3";
        }
        return new User(username, password,roleId,email, givenName,surname, isActive);
    }


    @Override
    public String toString() {
        return "NewUserRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", givenName='" + givenName + '\'' +
                ", isActive=" + isActive +
                ", surname='" + surname + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
