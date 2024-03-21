package com.hackathon.fiap.timesheet.config;

import com.hackathon.fiap.timesheet.adapter.out.EmployeeAdapter;
import com.hackathon.fiap.timesheet.adapter.out.PointRecordAdapter;
import com.hackathon.fiap.timesheet.application.core.usecase.PointRecordUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PointRecordConfig {
    @Bean
    public PointRecordUseCase pointRecordUseCase(PointRecordAdapter pointRecordAdapter,
                                                 EmployeeAdapter employeeAdapter) {
        return new PointRecordUseCase(pointRecordAdapter, employeeAdapter);
    }
}
