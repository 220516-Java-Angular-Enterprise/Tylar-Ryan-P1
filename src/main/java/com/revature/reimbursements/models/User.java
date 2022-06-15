package com.revature.reimbursements.models;


public class User {
    private String userId;
    private String username;
    private String password;
    private String roleId = "3";
    private String role;
    private String email;
    private String surname;
    private boolean isActive = true;

    public User() {
        super();
    }

    public User(String userId, String username, String password, String roleId, String email, String surname, boolean isActive) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.roleId = roleId;
        this.email = email;
        this.surname = surname;
        this.isActive = isActive;
    }

    public User(String username, String password, String email, String surname, Boolean isActive) {
        this.username = username;
        this.password = password;
        this.roleId = roleId;
        this.isActive = isActive;
        this.email = email;
        this.surname = surname;
    }
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String role){
        this.role = role;
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
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
                ", surname='" + surname + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}