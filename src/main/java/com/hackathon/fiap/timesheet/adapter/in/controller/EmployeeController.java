package com.hackathon.fiap.timesheet.adapter.in.controller;

import com.hackathon.fiap.timesheet.adapter.in.controller.mapper.EmployeeMapper;
import com.hackathon.fiap.timesheet.adapter.in.controller.request.EmployeeRequest;
import com.hackathon.fiap.timesheet.adapter.in.controller.response.EmployeeResponse;
import com.hackathon.fiap.timesheet.application.core.contants.EmployeeRole;
import com.hackathon.fiap.timesheet.application.core.domain.Employee;
import com.hackathon.fiap.timesheet.application.core.ports.in.AutenticationInputPort;
import com.hackathon.fiap.timesheet.application.core.ports.in.EmployeeInputPort;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("employees")
public class EmployeeController {
    private final EmployeeInputPort employeeInputPort;
    private final EmployeeMapper employeeMapper;
    private final AutenticationInputPort autenticationInputPort;

    @PostMapping()
    @Operation(summary = "Criar funcionário", description = "Cria um funcionário")
    public ResponseEntity<EmployeeResponse> create(@RequestBody @Valid EmployeeRequest employeeRequest, HttpServletRequest request) {
        Long userEmployeeId = getUserEmployeeId(request);
        if(!isManager(userEmployeeId)) return ResponseEntity.status(403).build();
        Employee employee = employeeInputPort.create(employeeRequest.getName(), employeeRequest.getEmail(), employeeRequest.getRole());
        EmployeeResponse employeeResponse = employeeMapper.toEmployeeResponse(employee);
        return ResponseEntity.ok(employeeResponse);
    }

    @DeleteMapping("{employeeId}")
    @Operation(summary = "Deletar funcionário", description = "Deleta um funcionário")
    public ResponseEntity<Void> delete(@PathVariable("employeeId") Long employeeId, HttpServletRequest request) {
        Long userEmployeeId = getUserEmployeeId(request);
        if(!isManager(userEmployeeId)) return ResponseEntity.status(403).build();
        employeeInputPort.delete(employeeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{employeeId}")
    @Operation(summary = "Consultar funcionário", description = "Consulta um funcionário")
    public ResponseEntity<EmployeeResponse> findById(@PathVariable("employeeId") Long employeeId, HttpServletRequest request) {
        Long userEmployeeId = getUserEmployeeId(request);
        if(!isManager(userEmployeeId) && !employeeId.equals(userEmployeeId)) return ResponseEntity.status(403).build();
        Employee employee = employeeInputPort.get(employeeId);
        EmployeeResponse employeeResponse = employeeMapper.toEmployeeResponse(employee);
        return ResponseEntity.ok(employeeResponse);
    }

    private Long getUserEmployeeId(HttpServletRequest request) {
        return autenticationInputPort.GetUserByToken(request).getEmployeeId();
    }

    private boolean isManager(Long employeeId) {
        return employeeInputPort.get(employeeId).getRole().equals(EmployeeRole.MANAGER);
    }
}
