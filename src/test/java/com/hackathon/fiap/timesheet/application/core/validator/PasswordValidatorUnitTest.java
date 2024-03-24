package com.hackathon.fiap.timesheet.application.core.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordValidatorUnitTest {

    @Test
    void isValidPassword() {
        // Arrange
        String validPassword = "Password1@";
        String invalidPassword = "password";

        // Act
        boolean validResult = PasswordValidator.isValidPassword(validPassword);
        boolean invalidResult = PasswordValidator.isValidPassword(invalidPassword);

        // Assert
        assertTrue(validResult);
        assertFalse(invalidResult);
    }
}