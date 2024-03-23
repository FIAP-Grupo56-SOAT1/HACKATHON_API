package com.hackathon.fiap.timesheet.config;

import com.hackathon.fiap.timesheet.adapter.out.GenerateTokenAdapter;
import com.hackathon.fiap.timesheet.adapter.out.GetSubjectAdapter;
import com.hackathon.fiap.timesheet.adapter.out.GetUserByTokenAdapter;
import com.hackathon.fiap.timesheet.adapter.out.UsernamePasswordAuthenticationTokenAdapter;
import com.hackathon.fiap.timesheet.application.core.usecase.AutenticationUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutenticationConfig {
    @Bean
    public AutenticationUseCase autenticationUseCase(GenerateTokenAdapter generateTokenAdapter,
                                                     GetSubjectAdapter getSubjectAdapter,
                                                     GetUserByTokenAdapter getUserByTokenAdapter,
                                                     UsernamePasswordAuthenticationTokenAdapter usernamePasswordAuthenticationTokenAdapter) {
        return new AutenticationUseCase(generateTokenAdapter, getSubjectAdapter, getUserByTokenAdapter, usernamePasswordAuthenticationTokenAdapter);
    }
}
