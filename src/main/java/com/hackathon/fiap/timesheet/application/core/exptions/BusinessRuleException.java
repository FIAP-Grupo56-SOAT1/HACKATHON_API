package com.hackathon.fiap.timesheet.application.core.exptions;

public class BusinessRuleException extends RuntimeException {
    public BusinessRuleException(String message) {
        super(message);
    }
}
