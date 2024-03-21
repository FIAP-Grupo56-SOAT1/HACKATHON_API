package com.hackathon.fiap.timesheet.application.core.exptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
