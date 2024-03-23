package com.hackathon.fiap.timesheet.adapter.out;

import com.hackathon.fiap.timesheet.application.core.ports.out.SecretOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class SecretAdapter implements SecretOutputPort {

    @Value("${api.security.token.secret}")
    private String secret;
    @Override
    public String getSecret() {
        return secret;
    }
}
