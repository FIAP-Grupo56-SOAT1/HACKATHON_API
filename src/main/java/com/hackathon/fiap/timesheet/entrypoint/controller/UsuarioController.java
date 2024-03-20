package com.hackathon.fiap.timesheet.entrypoint.controller;


import com.hackathon.fiap.timesheet.dataprovider.repository.UsuarioRepository;
import com.hackathon.fiap.timesheet.dataprovider.repository.entity.UsuarioEntity;
import com.hackathon.fiap.timesheet.entrypoint.controller.request.FormCreateUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {


    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    public ResponseEntity createUser(@RequestBody @Valid FormCreateUser formCreateUser) {

        var usaurioEntity = new UsuarioEntity(formCreateUser);

        repository.save(usaurioEntity);

        return ResponseEntity.ok("Usuário criado com sucesso!.");
    }
}
