package com.hackathon.fiap.timesheet.application.core.exptions;

public class InvalidFormatException extends RuntimeException{
    public InvalidFormatException(String message) {
        super(message);
    }
}
