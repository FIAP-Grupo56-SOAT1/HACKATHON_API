package com.hackathon.fiap.timesheet.application.core.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailValidatorUnitTest {

    @Test
    void isValidEmail() {
        // Arrange
        String validEmail = "test@example.com";
        String invalidEmail = "testexample.com";

        // Act
        boolean validResult = EmailValidator.isValidEmail(validEmail);
        boolean invalidResult = EmailValidator.isValidEmail(invalidEmail);

        // Assert
        assertTrue(validResult);
        assertFalse(invalidResult);
    }
}