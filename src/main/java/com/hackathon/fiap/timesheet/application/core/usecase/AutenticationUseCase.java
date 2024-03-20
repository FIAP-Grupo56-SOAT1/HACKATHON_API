package com.hackathon.fiap.timesheet.application.core.usecase;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.hackathon.fiap.timesheet.application.core.domain.User;
import com.hackathon.fiap.timesheet.application.core.ports.in.AutenticationInputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.CryptographyOutputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.EmployeeOutputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.UserOutputPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class AutenticationUseCase implements AutenticationInputPort {

    @Value("${api.security.token.secret}")
    private String secret;
    @Value("${spring.application.name}")
    private String applicationName;

    private final UserOutputPort userOutputPort;
    private final EmployeeOutputPort employeeOutputPort;
    private final CryptographyOutputPort cryptographyOutputPort;

    public AutenticationUseCase(UserOutputPort userOutputPort,
                                EmployeeOutputPort employeeOutputPort,
                                CryptographyOutputPort cryptographyOutputPort) {
        this.userOutputPort = userOutputPort;
        this.employeeOutputPort = employeeOutputPort;
        this.cryptographyOutputPort = cryptographyOutputPort;
    }

    @Override
    public UsernamePasswordAuthenticationToken UsernamePasswordAuthenticationToken(String userId, String password) {
        UsernamePasswordAuthenticationToken authenticationToken =  UsernamePasswordAuthenticationToken(userId, password);
        return authenticationToken;
    }

    @Override
    public String GenerateTokenJwt(User user) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(applicationName)
                    .withSubject(user.getUserId())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("erro ao gerar token jwt", exception);
        }
    }

    @Override
    public String GetSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);

            return JWT.require(algoritmo)
                    .withIssuer(applicationName)
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
