package com.hackathon.fiap.timesheet.application.core.ports.in;

import com.hackathon.fiap.timesheet.adapter.out.repository.entity.UserEntity;
import com.hackathon.fiap.timesheet.application.core.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface AutenticationInputPort {
    UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(String userId, String password);

    String generateTokenJwt(UserEntity user);

    String getSubject(String tokenJWT);

    User getUserByToken(HttpServletRequest request);
}
