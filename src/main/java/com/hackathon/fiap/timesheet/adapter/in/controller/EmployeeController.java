package com.hackathon.fiap.timesheet.adapter.in.controller;

import com.hackathon.fiap.timesheet.adapter.in.controller.mapper.EmployeeMapper;
import com.hackathon.fiap.timesheet.adapter.in.controller.request.EmployeeRequest;
import com.hackathon.fiap.timesheet.adapter.in.controller.response.EmployeeResponse;
import com.hackathon.fiap.timesheet.application.core.domain.Employee;
import com.hackathon.fiap.timesheet.application.core.ports.in.EmployeeInputPort;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("Employees")
public class EmployeeController {
    private final EmployeeInputPort employeeInputPort;
    private final EmployeeMapper employeeMapper;


    @PostMapping()
    @Operation(summary = "Criar funcionário", description = "Cria um funcionário")
    public ResponseEntity<EmployeeResponse> create(@RequestBody @Valid EmployeeRequest employeeRequest) {
        Employee employee = employeeInputPort.create(employeeRequest.getName(), employeeRequest.getRole());
        EmployeeResponse employeeResponse = employeeMapper.toEmployeeResponse(employee);
        return ResponseEntity.ok(employeeResponse);
    }
}
