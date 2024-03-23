package com.hackathon.fiap.timesheet.adapter.out;


import com.hackathon.fiap.timesheet.application.core.ports.out.ApplicationNameOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationNameAdapter implements ApplicationNameOutputPort {

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public String getApplicationName() {
        return applicationName;
    }
}
