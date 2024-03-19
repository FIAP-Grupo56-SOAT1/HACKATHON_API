package com.hackathon.fiap.timesheet.application.core.domain;

public class Employee {
    private Long employeeId;
    private String email;
    private String name;
    private Boolean active;

    public Employee(Long employeeId, String email, String name, Boolean active) {
        this.employeeId = employeeId;
        this.email = email;
        this.name = name;
        this.active = active;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
