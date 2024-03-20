package com.hackathon.fiap.timesheet.application.core.usecase;

import com.hackathon.fiap.timesheet.application.core.domain.User;
import com.hackathon.fiap.timesheet.application.core.exptions.EmployeeNotFound;
import com.hackathon.fiap.timesheet.application.core.exptions.InvalidFormat;
import com.hackathon.fiap.timesheet.application.core.exptions.UserNotFound;
import com.hackathon.fiap.timesheet.application.core.ports.in.UserInputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.CryptographyOutputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.EmployeeOutputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.UserOutputPort;
import com.hackathon.fiap.timesheet.application.core.validator.EmailValidator;
import com.hackathon.fiap.timesheet.application.core.validator.PasswordValidator;

import java.util.List;

public class UserUseCase implements UserInputPort {
    private UserOutputPort userOutputPort;
    private EmployeeOutputPort employeeOutputPort;
    private CryptographyOutputPort cryptographyOutputPort;

    public UserUseCase(UserOutputPort userOutputPort,
                       EmployeeOutputPort employeeOutputPort,
                       CryptographyOutputPort cryptographyOutputPort) {
        this.userOutputPort = userOutputPort;
        this.employeeOutputPort = employeeOutputPort;
        this.cryptographyOutputPort = cryptographyOutputPort;
    }

    @Override
    public User create(String userId, String password, Long employeeId) {
        if(!employeeOutputPort.exists(employeeId)) throw new EmployeeNotFound("Employee not found");
        if (!EmailValidator.isValidEmail(userId)) throw new InvalidFormat("Invalid email");
        if(!PasswordValidator.isValidPassword(password)) throw new InvalidFormat("Invalid password");
        String passEncrypted = cryptographyOutputPort.encrypt(password);
        User user = new User(userId, passEncrypted, employeeId, true);
        return userOutputPort.save(user);
    }

    @Override
    public void updatePassword(String userId, String password) {
        if(!PasswordValidator.isValidPassword(password)) throw new InvalidFormat("Invalid password");
        if(!userOutputPort.exists(userId)) throw new UserNotFound("User not found");
        String passEncrypted = cryptographyOutputPort.encrypt(password);
        //userOutputPort.updatePassword(userId, passEncrypted);
        //return null;
    }

    @Override
    public User updateStatus(String userId, Boolean active) {
        return null;
    }


    @Override
    public void delete(String userId) {

    }

    @Override
    public User get(String userId) {
        return null;
    }

    @Override
    public List<User> listUsers() {
        return null;
    }
}
