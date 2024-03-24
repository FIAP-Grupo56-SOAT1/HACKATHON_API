package com.hackathon.fiap.timesheet.adapter.out.exception;

public class ExpiredTokenException extends RuntimeException {
    public ExpiredTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
