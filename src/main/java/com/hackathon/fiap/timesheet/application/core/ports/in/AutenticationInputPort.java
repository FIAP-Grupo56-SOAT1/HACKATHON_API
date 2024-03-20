package com.hackathon.fiap.timesheet.application.core.ports.in;

import com.hackathon.fiap.timesheet.application.core.domain.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import java.util.List;

public interface AutenticationInputPort {
    UsernamePasswordAuthenticationToken UsernamePasswordAuthenticationToken(String userId,String password);
    String GenerateTokenJwt(User user);
    String GetSubject(String tokenJWT);
}
