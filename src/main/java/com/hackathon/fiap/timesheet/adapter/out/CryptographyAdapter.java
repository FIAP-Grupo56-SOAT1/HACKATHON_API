package com.hackathon.fiap.timesheet.adapter.out;

import com.hackathon.fiap.timesheet.application.core.ports.out.CryptographyOutputPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component

public class CryptographyAdapter implements CryptographyOutputPort {
    @Override
    public String encrypt(String value) {
        return new BCryptPasswordEncoder().encode(value);
    }
}
