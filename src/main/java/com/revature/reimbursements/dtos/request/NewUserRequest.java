package com.revature.reimbursements.dtos.request;

import com.revature.reimbursements.models.User;

public class NewUserRequest {
    private String username;
    private String password;
    private String email;
    private String givenName;
    private String surname;
    private boolean isActive = true;

//    private String role = "DEFAULT";
    private String role;

    public NewUserRequest() {
        super();
    }

    public NewUserRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public NewUserRequest(String username, String password, String email, String givenName, String surname, boolean isActive, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.givenName = givenName;
        this.surname = surname;
        this.isActive = isActive;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User extractUser() {
        return new User(username, password, role);
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "NewUserRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
