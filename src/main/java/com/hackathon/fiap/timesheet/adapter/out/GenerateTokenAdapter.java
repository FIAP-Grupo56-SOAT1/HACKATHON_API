package com.hackathon.fiap.timesheet.adapter.out;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.hackathon.fiap.timesheet.adapter.out.exception.GenerateTokenException;
import com.hackathon.fiap.timesheet.adapter.out.repository.entity.UserEntity;
import com.hackathon.fiap.timesheet.application.core.ports.out.GenerateTokenOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Component
@RequiredArgsConstructor
public class GenerateTokenAdapter implements GenerateTokenOutputPort {

    @Value("${api.security.token.secret}")
    private String secret;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${api.security.token.expirationMinutes}")
    private int expirationMinutes;


    @Override
    public String generateTokenJwt(UserEntity user) {

        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(applicationName)
                    .withSubject(user.getUserId())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new GenerateTokenException("Error generating JWT token", exception);
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusMinutes(expirationMinutes).toInstant(ZoneOffset.of("-03:00"));
    }
}
