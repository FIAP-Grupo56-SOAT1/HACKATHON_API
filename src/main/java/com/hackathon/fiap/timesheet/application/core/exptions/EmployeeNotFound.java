package com.hackathon.fiap.timesheet.application.core.exptions;

public class EmployeeNotFound extends RuntimeException {
    public EmployeeNotFound(String message) {
        super(message);
    }
}
