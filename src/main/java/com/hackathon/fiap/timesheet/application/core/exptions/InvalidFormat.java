package com.hackathon.fiap.timesheet.application.core.exptions;

public class InvalidFormat extends RuntimeException{
    public InvalidFormat(String message) {
        super(message);
    }
}
