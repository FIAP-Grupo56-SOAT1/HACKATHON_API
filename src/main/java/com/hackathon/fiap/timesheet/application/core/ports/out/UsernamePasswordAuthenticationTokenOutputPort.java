package com.hackathon.fiap.timesheet.application.core.ports.out;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface UsernamePasswordAuthenticationTokenOutputPort {
    UsernamePasswordAuthenticationToken GetUsernamePasswordAuthenticationToken(String userId, String password);
}
