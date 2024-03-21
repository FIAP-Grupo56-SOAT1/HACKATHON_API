package com.hackathon.fiap.timesheet.application.core.usecase;

import com.hackathon.fiap.timesheet.application.core.domain.User;
import com.hackathon.fiap.timesheet.application.core.exptions.BusinessRuleException;
import com.hackathon.fiap.timesheet.application.core.exptions.EmployeeNotFoundException;
import com.hackathon.fiap.timesheet.application.core.exptions.InvalidFormatException;
import com.hackathon.fiap.timesheet.application.core.exptions.UserNotFoundException;
import com.hackathon.fiap.timesheet.application.core.ports.in.UserInputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.CryptographyOutputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.EmployeeOutputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.UserOutputPort;
import com.hackathon.fiap.timesheet.application.core.validator.EmailValidator;
import com.hackathon.fiap.timesheet.application.core.validator.PasswordValidator;

import java.util.List;

public class UserUseCase implements UserInputPort {
    private final UserOutputPort userOutputPort;
    private final EmployeeOutputPort employeeOutputPort;
    private final CryptographyOutputPort cryptographyOutputPort;
    private static final String USER_NOT_FOUND = "User not found";

    public UserUseCase(UserOutputPort userOutputPort,
                       EmployeeOutputPort employeeOutputPort,
                       CryptographyOutputPort cryptographyOutputPort) {
        this.userOutputPort = userOutputPort;
        this.employeeOutputPort = employeeOutputPort;
        this.cryptographyOutputPort = cryptographyOutputPort;
    }

    @Override
    public User create(String userId, String password, Long employeeId) {
        boolean employeeExists = employeeOutputPort.exists(employeeId);
        if (!employeeExists) throw new EmployeeNotFoundException("Employee not found");
        if (userOutputPort.exists(userId)) throw new BusinessRuleException("User already exists");
        if (userOutputPort.getByEmployeeId(employeeId).isPresent()) throw new BusinessRuleException("The employee already has a user");
        if (!EmailValidator.isValidEmail(userId)) throw new InvalidFormatException("Invalid User Id");
        if (!PasswordValidator.isValidPassword(password)) throw new InvalidFormatException("Invalid password");
        String passEncrypted = cryptographyOutputPort.encrypt(password);
        User user = new User(userId, passEncrypted, employeeId, true);
        return userOutputPort.save(user);
    }

    @Override
    public void updatePassword(String userId, String password) {
        if (!PasswordValidator.isValidPassword(password)) throw new InvalidFormatException("Invalid password");
        User user = userOutputPort.get(userId).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        user.setPassword(cryptographyOutputPort.encrypt(password));
        userOutputPort.save(user);
    }

    @Override
    public User updateStatus(String userId, Boolean active) {
        User user = userOutputPort.get(userId).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        user.setActive(active);
        return userOutputPort.save(user);
    }

    @Override
    public void delete(String userId) {
        if (!userOutputPort.exists(userId)) throw new UserNotFoundException(USER_NOT_FOUND);
        userOutputPort.delete(userId);
    }

    @Override
    public User get(String userId) {
        return userOutputPort.get(userId).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
    }

    @Override
    public List<User> listUsers() {
        return userOutputPort.listUsers();
    }
}
