package com.hackathon.fiap.timesheet.application.core.usecase;

import com.hackathon.fiap.timesheet.application.core.domain.User;
import com.hackathon.fiap.timesheet.application.core.exptions.EmployeeNotFound;
import com.hackathon.fiap.timesheet.application.core.exptions.InvalidFormat;
import com.hackathon.fiap.timesheet.application.core.exptions.UserNotFound;
import com.hackathon.fiap.timesheet.application.core.ports.in.AutenticationInputPort;
import com.hackathon.fiap.timesheet.application.core.ports.in.UserInputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.CryptographyOutputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.EmployeeOutputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.UserOutputPort;
import com.hackathon.fiap.timesheet.application.core.validator.EmailValidator;
import com.hackathon.fiap.timesheet.application.core.validator.PasswordValidator;

import java.util.List;

public class AutenticationUseCase implements AutenticationInputPort {
    private final UserOutputPort userOutputPort;
    private final EmployeeOutputPort employeeOutputPort;
    private final CryptographyOutputPort cryptographyOutputPort;

    public AutenticationUseCase(UserOutputPort userOutputPort,
                                EmployeeOutputPort employeeOutputPort,
                                CryptographyOutputPort cryptographyOutputPort) {
        this.userOutputPort = userOutputPort;
        this.employeeOutputPort = employeeOutputPort;
        this.cryptographyOutputPort = cryptographyOutputPort;
    }

}
