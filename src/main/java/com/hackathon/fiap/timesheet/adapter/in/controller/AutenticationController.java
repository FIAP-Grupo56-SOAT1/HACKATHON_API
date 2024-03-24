package com.hackathon.fiap.timesheet.adapter.in.controller;

import com.hackathon.fiap.timesheet.adapter.in.controller.request.AuthenticationDataRequest;
import com.hackathon.fiap.timesheet.adapter.in.controller.response.DataTokenJWTReponse;
import com.hackathon.fiap.timesheet.adapter.out.repository.entity.UserEntity;
import com.hackathon.fiap.timesheet.application.core.ports.in.AutenticationInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
@Tag(name = "Autenticação", description = "Controller que gerencia a autenticação")
public class AutenticationController {
    private final AuthenticationManager manager;
    private final AutenticationInputPort autenticationInputPort;

    @PostMapping
    @Operation(summary = "Login", description = "Realiza o login do usuário")
    public ResponseEntity<DataTokenJWTReponse> login(@RequestBody @Valid AuthenticationDataRequest authData) {
        var authenticationToken = autenticationInputPort.getUsernamePasswordAuthenticationToken(authData.login(), authData.password());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = autenticationInputPort.generateTokenJwt((UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(new DataTokenJWTReponse(tokenJWT));
    }
}
