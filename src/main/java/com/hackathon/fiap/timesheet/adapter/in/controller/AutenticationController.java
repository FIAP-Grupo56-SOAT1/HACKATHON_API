package com.hackathon.fiap.timesheet.adapter.in.controller;

import com.hackathon.fiap.timesheet.adapter.in.controller.request.AuthenticationDataRequest;
import com.hackathon.fiap.timesheet.adapter.in.controller.response.DataTokenJWTReponse;
import com.hackathon.fiap.timesheet.adapter.out.repository.entity.UserEntity;
import com.hackathon.fiap.timesheet.application.core.ports.in.AutenticationInputPort;
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
public class AutenticationController {
    private final AuthenticationManager manager;
    private final AutenticationInputPort autenticationInputPort;

    @PostMapping
    public ResponseEntity<DataTokenJWTReponse> login(@RequestBody @Valid AuthenticationDataRequest authData) {
        var authenticationToken = autenticationInputPort.GetUsernamePasswordAuthenticationToken(authData.login(), authData.password());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = autenticationInputPort.GenerateTokenJwt((UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(new DataTokenJWTReponse(tokenJWT));
    }
}
