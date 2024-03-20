package com.hackathon.fiap.timesheet.config;

import com.hackathon.fiap.timesheet.adapter.out.EmployeeAdapter;
import com.hackathon.fiap.timesheet.application.core.usecase.EmployeeUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeConfig {
    @Bean
    public EmployeeUseCase employeeOutputPort(EmployeeAdapter employeeAdapter) {
        return new EmployeeUseCase(employeeAdapter);
    }
}
