package com.hackathon.fiap.timesheet.application.core.domain;

public class User {
    private String userId;
    private Long employeeId;
    private Boolean active;

    public User() {
    }

    public User(String userId, Long employeeId, Boolean active) {
        this.userId = userId;
        this.employeeId = employeeId;
        this.active = active;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
