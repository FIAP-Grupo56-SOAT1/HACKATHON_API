package com.hackathon.fiap.timesheet.adapter.in.controller;

import com.hackathon.fiap.timesheet.adapter.in.controller.mapper.UserMapper;
import com.hackathon.fiap.timesheet.adapter.in.controller.request.UserRequest;
import com.hackathon.fiap.timesheet.adapter.in.controller.response.UserResponse;
import com.hackathon.fiap.timesheet.application.core.domain.User;
import com.hackathon.fiap.timesheet.application.core.ports.in.UserInputPort;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
    private final UserInputPort userInputPort;
    private final UserMapper userMapper;

    @PostMapping()
    @Operation(summary = "Criar usuário", description = "Cria um usuário para o funcionário")
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest) {
        User user = userInputPort.create(userRequest.getUserId(), userRequest.getPassword(), userRequest.getEmployeeId());
        UserResponse userResponse = userMapper.toUserResponse(user);
        return ResponseEntity.ok(userResponse);
    }
}
