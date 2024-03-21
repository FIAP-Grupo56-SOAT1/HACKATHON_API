package com.hackathon.fiap.timesheet.config;

import com.hackathon.fiap.timesheet.adapter.out.EmployeeAdapter;
import com.hackathon.fiap.timesheet.adapter.out.SendReportEmailAdapter;
import com.hackathon.fiap.timesheet.application.core.usecase.DotMirrorReportUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DotMirrorReportConfig {
    @Bean
    public DotMirrorReportUseCase dotMirrorReportUseCase(EmployeeAdapter employeeAdapter,
                                                         SendReportEmailAdapter sendReportEmailAdapter) {
        return new DotMirrorReportUseCase(employeeAdapter, sendReportEmailAdapter);
    }
}
