package com.hackathon.fiap.timesheet.adapter.in.controller;

import com.hackathon.fiap.timesheet.adapter.in.controller.mapper.UserMapper;
import com.hackathon.fiap.timesheet.adapter.in.controller.request.PasswordUpdRequest;
import com.hackathon.fiap.timesheet.adapter.in.controller.request.UserActivationRequest;
import com.hackathon.fiap.timesheet.adapter.in.controller.request.UserRequest;
import com.hackathon.fiap.timesheet.adapter.in.controller.response.UserResponse;
import com.hackathon.fiap.timesheet.application.core.constant.EmployeeRole;
import com.hackathon.fiap.timesheet.application.core.domain.User;
import com.hackathon.fiap.timesheet.application.core.ports.in.AutenticationInputPort;
import com.hackathon.fiap.timesheet.application.core.ports.in.EmployeeInputPort;
import com.hackathon.fiap.timesheet.application.core.ports.in.UserInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
@Tag(name = "Usuários", description = "Controller que gerencia as ações possíveis de um usuário")
public class UserController {
    private final UserInputPort userInputPort;
    private final UserMapper userMapper;
    private final EmployeeInputPort employeeInputPort;
    private final AutenticationInputPort autenticationInputPort;

    @PostMapping()
    @Operation(summary = "Criar usuário", description = "Cria um usuário para o funcionário")
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserRequest userRequest) {
        User user = userInputPort.create(userRequest.getUserId(), userRequest.getPassword(), userRequest.getEmployeeId());
        UserResponse userResponse = userMapper.toUserResponse(user);
        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping("{userId}")
    @Operation(summary = "Deletar usuário", description = "Deleta um usuário")
    public ResponseEntity<Void> delete(@PathVariable("userId") String userId, HttpServletRequest request) {
        if (!isManager(getUser(request).getEmployeeId())) return ResponseEntity.status(403).build();
        userInputPort.delete(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{userId}")
    @Operation(summary = "Consultar usuário", description = "Retorna um usuário")
    public ResponseEntity<UserResponse> findById(@PathVariable("userId") String userId, HttpServletRequest request) {
        User userAuth = getUser(request);
        if (!isManager(userAuth.getEmployeeId()) && !userId.equals(userAuth.getUserId()))
            return ResponseEntity.status(403).build();
        User user = userInputPort.get(userId);
        UserResponse userResponse = userMapper.toUserResponse(user);
        return ResponseEntity.ok(userResponse);
    }

    @PutMapping("{userId}/change-password")
    @Operation(summary = "Atualizar senha", description = "Atualiza a senha de um usuário")
    public ResponseEntity<UserResponse> updatePassword(@PathVariable("userId") String userId,
                                                       @RequestBody @Valid PasswordUpdRequest passwordUpdRequest,
                                                       HttpServletRequest request) {
        User userAuth = getUser(request);
        if (!isManager(userAuth.getEmployeeId()) && !userId.equals(userAuth.getUserId()))
            return ResponseEntity.status(403).build();
        userInputPort.updatePassword(userId, passwordUpdRequest.getPassword());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{userId}/activation")
    @Operation(summary = "Ativar ou desativar usuário", description = "Deixa o usuario como ativo ou inativo um usuário")
    public ResponseEntity<Void> activateOrDeactivate(@PathVariable("userId") String userId,
                                                     @RequestBody @Valid UserActivationRequest userActivationRequest,
                                                     HttpServletRequest request) {
        User userAuth = getUser(request);
        if (!isManager(userAuth.getEmployeeId()) && !userId.equals(userAuth.getUserId()))
            return ResponseEntity.status(403).build();
        userInputPort.updateStatus(userId, userActivationRequest.getActive());
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    @Operation(summary = "Listar usuários", description = "Lista todos os usuários")
    public ResponseEntity<List<UserResponse>> list(HttpServletRequest request) {
        if (!isManager(getUser(request).getEmployeeId())) return ResponseEntity.status(403).build();
        List<User> user = userInputPort.listUsers();
        List<UserResponse> userResponse = userMapper.toUserResponseList(user);
        return ResponseEntity.ok(userResponse);
    }

    private User getUser(HttpServletRequest request) {
        return autenticationInputPort.getUserByToken(request);
    }

    private boolean isManager(Long employeeId) {
        return employeeInputPort.get(employeeId).getRole().equals(EmployeeRole.MANAGER);
    }
}
