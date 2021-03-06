package com.revature.reimbursements.models;

public class UserRole {
    private String roleId;
    private String role;

    public UserRole(String roleId, String role) {
        this.roleId = roleId;
        this.role = role;
    }

    public UserRole(String role) {
        this.role = role;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String role_id) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
