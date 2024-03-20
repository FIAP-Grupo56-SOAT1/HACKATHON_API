package com.hackathon.fiap.timesheet.application.core.domain;

import com.hackathon.fiap.timesheet.application.core.contants.EmployeeRole;

public class Employee {
    private Long employeeId;
    private String name;
    private EmployeeRole role;
    private String userId;
    private Boolean active;

    public Employee() {
    }

    public Employee(Long employeeId, String name, EmployeeRole role, String userId, Boolean active) {
        this.employeeId = employeeId;
        this.name = name;
        this.role = role;
        this.userId = userId;
        this.active = active;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmployeeRole getRole() {
        return role;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
