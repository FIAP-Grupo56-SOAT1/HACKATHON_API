package com.hackathon.fiap.timesheet.application.core.domain;

import com.hackathon.fiap.timesheet.application.core.contants.EmployeeRole;

public class Employee {
    private Long employeeId;
    private String name;
    private String email;
    private EmployeeRole role;
    private Boolean active;

    public Employee() {
    }

    public Employee(Long employeeId, String name, String email, EmployeeRole role, Boolean active) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.role = role;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EmployeeRole getRole() {
        return role;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
