package com.hackathon.fiap.timesheet.application.core.ports.in;

import com.hackathon.fiap.timesheet.adapter.out.repository.entity.UserEntity;
import com.hackathon.fiap.timesheet.application.core.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import java.util.List;
import java.util.Optional;

public interface AutenticationInputPort {
    UsernamePasswordAuthenticationToken GetUsernamePasswordAuthenticationToken(String userId,String password);
    String GenerateTokenJwt(UserEntity user);
    String GetSubject(String tokenJWT);
    User GetUserByToken(HttpServletRequest request);
    String RecuperarToken(HttpServletRequest request);
}
