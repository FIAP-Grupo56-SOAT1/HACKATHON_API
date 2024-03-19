package com.hackathon.fiap.timesheet.application.core.domain;

public class User {
    private String id;
    private String password;
    private Long employeeId;

    public User(String id, String password, Long employeeId) {
        this.id = id;
        this.password = password;
        this.employeeId = employeeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
