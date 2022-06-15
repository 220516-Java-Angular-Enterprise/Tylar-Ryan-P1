package com.revature.reimbursements.models;


import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private String userId;
    private String username;
    private String password;
    private String roleId = "3";
    private String email;
    //@Column(name = "given_name")
    @JsonProperty("given_name")
    private String givenName="worker";
    private String surname;
    private boolean isActive = true;

    public User() {
        super();
    }

    public User(String userId, String username, String password, String roleId, String email, String givenName, String surname, boolean isActive) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.roleId = roleId;
        this.email = email;
        this.givenName = givenName;
        this.surname = surname;
        this.isActive = isActive;
    }

    public User(String username, String password, String roleId,String email, String givenName,String surname, Boolean isActive) {
        this.username = username;
        this.password = password;
        this.isActive = isActive;
        this.email = email;
        this.surname = surname;
        this.roleId=roleId;
        this.givenName=givenName;
    }

    public User(String username, String password, String roleId) {
        this.username = username;
        this.password = password;
        this.roleId = roleId;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
        return roleId;
    }

    public void setRole(String role) {
        this.roleId = role;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @JsonProperty("given_name")
    public String getGivenName() {
        return givenName;
    }
    @JsonProperty("given_name")
    public void setGivenName(String givenName) {
        this.givenName ="worker";
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roleId='" + roleId + '\'' +
                ", email='" + email + '\'' +
                ", givenName='" + givenName + '\'' +
                ", surname='" + surname + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}