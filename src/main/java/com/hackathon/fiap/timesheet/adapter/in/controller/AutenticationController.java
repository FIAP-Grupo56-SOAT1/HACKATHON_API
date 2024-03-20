package com.hackathon.fiap.timesheet.adapter.in.controller;

import com.hackathon.fiap.timesheet.adapter.in.controller.mapper.AutenticationMapper;
import com.hackathon.fiap.timesheet.adapter.in.controller.request.DadosAutenticacao;
import com.hackathon.fiap.timesheet.adapter.in.controller.response.DadosTokenJWTReponse;
import com.hackathon.fiap.timesheet.application.core.domain.User;
import com.hackathon.fiap.timesheet.application.core.ports.in.AutenticationInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final AutenticationInputPort autenticationInputPort;
    private final AutenticationMapper autenticationMapper;

    @Autowired
    private AuthenticationManager manager;


    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var authenticationToken = autenticationInputPort.UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = autenticationInputPort.GenerateTokenJwt((User) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWTReponse(tokenJWT));
    }
}
