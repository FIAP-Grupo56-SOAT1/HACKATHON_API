package com.hackathon.fiap.timesheet.config;

import com.hackathon.fiap.timesheet.adapter.out.CryptographyAdapter;
import com.hackathon.fiap.timesheet.adapter.out.EmployeeAdapter;
import com.hackathon.fiap.timesheet.adapter.out.UserAdapter;
import com.hackathon.fiap.timesheet.application.core.usecase.UserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean
    public UserUseCase userUseCase(UserAdapter userAdapter,
                                   EmployeeAdapter employeeAdapter,
                                   CryptographyAdapter cryptographyAdapter) {
        return new UserUseCase(userAdapter, employeeAdapter, cryptographyAdapter);
    }
}
