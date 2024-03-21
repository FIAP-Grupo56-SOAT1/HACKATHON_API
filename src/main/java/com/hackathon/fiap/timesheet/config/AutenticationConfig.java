package com.hackathon.fiap.timesheet.config;

import com.hackathon.fiap.timesheet.adapter.out.CryptographyAdapter;
import com.hackathon.fiap.timesheet.adapter.out.EmployeeAdapter;
import com.hackathon.fiap.timesheet.adapter.out.UserAdapter;
import com.hackathon.fiap.timesheet.application.core.usecase.AutenticationUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutenticationConfig {
    @Bean
    public AutenticationUseCase autenticationUseCase(UserAdapter userAdapter,
                                            EmployeeAdapter employeeAdapter,
                                            CryptographyAdapter cryptographyAdapter) {
        return new AutenticationUseCase(userAdapter, employeeAdapter, cryptographyAdapter);
    }
}
