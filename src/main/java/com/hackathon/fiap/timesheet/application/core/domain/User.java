package com.hackathon.fiap.timesheet.application.core.domain;

public class User {
    private String userId;
    private String password;
    private Long employeeId;
    private Boolean active;

    public User(String userId, String password, Long employeeId, Boolean active) {
        this.userId = userId;
        this.password = password;
        this.employeeId = employeeId;
        this.active = active;
    }


    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
