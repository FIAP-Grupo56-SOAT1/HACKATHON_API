package com.hackathon.fiap.timesheet.application.core.exptions;

public class UserNotFound extends RuntimeException {
    public UserNotFound(String message) {
        super(message);
    }
}
