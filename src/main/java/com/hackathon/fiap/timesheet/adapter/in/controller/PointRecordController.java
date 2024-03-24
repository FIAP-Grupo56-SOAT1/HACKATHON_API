package com.hackathon.fiap.timesheet.adapter.in.controller;

import com.hackathon.fiap.timesheet.adapter.in.controller.mapper.PointRecordMapper;
import com.hackathon.fiap.timesheet.adapter.in.controller.request.PointRecordRequest;
import com.hackathon.fiap.timesheet.adapter.in.controller.response.PointRecordResponse;
import com.hackathon.fiap.timesheet.adapter.in.controller.response.PointRecordTotalResponse;
import com.hackathon.fiap.timesheet.application.core.constant.EmployeeRole;
import com.hackathon.fiap.timesheet.application.core.constant.PointRecordType;
import com.hackathon.fiap.timesheet.application.core.domain.PointRecord;
import com.hackathon.fiap.timesheet.application.core.ports.in.AutenticationInputPort;
import com.hackathon.fiap.timesheet.application.core.ports.in.EmployeeInputPort;
import com.hackathon.fiap.timesheet.application.core.ports.in.PointRecordInputPort;
import com.hackathon.fiap.timesheet.application.core.valueobject.WorkedHours;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("point-records")
@Tag(name = "Registro de Ponto", description = "Controller que gerencia as ações possíveis de um ponto de um funcionário")
public class PointRecordController {
    private final PointRecordInputPort pointRecordInputPort;
    private final PointRecordMapper pointRecordMapper;
    private final EmployeeInputPort employeeInputPort;
    private final AutenticationInputPort autenticationInputPort;

    @PostMapping("/employees/record-point/{type}")
    @Operation(summary = "Registrar ponto", description = "Registra o ponto do funcionário")
    public ResponseEntity<PointRecordResponse> recordPoint(@PathVariable("type") PointRecordType type,
                                                           HttpServletRequest request) {
        Long userEmployeeId = getUserEmployeeId(request);
        PointRecord pointRecord = pointRecordInputPort.recordPoint(userEmployeeId, type);
        PointRecordResponse pointRecordResponse = pointRecordMapper.toPointRecordResponse(pointRecord);
        return ResponseEntity.ok(pointRecordResponse);
    }

    @PostMapping("/manual-record-point")
    @Operation(summary = "Registrar ponto manual", description = "Registra o ponto do funcionário manualmente")
    public ResponseEntity<PointRecordResponse> manualRecordPoint(@RequestBody @Valid PointRecordRequest pointRecordRequest,
                                                                 HttpServletRequest request) {
        Long userEmployeeId = getUserEmployeeId(request);
        if (!isManager(userEmployeeId)) return ResponseEntity.status(403).build();
        PointRecord pointRecord =
                pointRecordInputPort.manualRecordPoint(pointRecordRequest.getEmployeeId(), pointRecordRequest.getDate(),
                        pointRecordRequest.getTime(), pointRecordRequest.getType());
        PointRecordResponse pointRecordResponse = pointRecordMapper.toPointRecordResponse(pointRecord);
        return ResponseEntity.ok(pointRecordResponse);
    }

    @GetMapping("{pointRecordId}")
    @Operation(summary = "Consultar ponto", description = "Retorna um ponto")
    public ResponseEntity<PointRecordResponse> findById(@PathVariable("pointRecordId") Long pointRecordId,
                                                        HttpServletRequest request) {
        Long userEmployeeId = getUserEmployeeId(request);
        PointRecord pointRecord = pointRecordInputPort.get(pointRecordId);
        if (!pointRecord.getEmployeeId().equals(userEmployeeId)) return ResponseEntity.status(403).build();
        PointRecordResponse pointRecordResponse = pointRecordMapper.toPointRecordResponse(pointRecord);
        return ResponseEntity.ok(pointRecordResponse);
    }

    @GetMapping()
    @Operation(summary = "Listar pontos", description = "Retorna todos pontos")
    public ResponseEntity<PointRecordTotalResponse> list(HttpServletRequest request) {
        Long userEmployeeId = getUserEmployeeId(request);
        return getPointRecordTotalResponseResponseEntity(userEmployeeId);
    }

    @GetMapping("list-by-date/{date}")
    @Operation(summary = "Listar pontos por data", description = "Retorna todos pontos por data")
    public ResponseEntity<PointRecordTotalResponse> listByDate(@PathVariable("date") LocalDate date,
                                                               HttpServletRequest request) {
        Long userEmployeeId = getUserEmployeeId(request);
        List<PointRecord> pointRecord = pointRecordInputPort.listByDateAndEmployeeId(userEmployeeId, date);
        LocalTime totalWorkedHours = WorkedHours.calculateTotal(pointRecord);
        List<PointRecordResponse> pointRecordResponse = pointRecordMapper.toPointRecordResponse(pointRecord);
        PointRecordTotalResponse pointRecordTotalResponse = new PointRecordTotalResponse(pointRecordResponse, totalWorkedHours);
        return ResponseEntity.ok(pointRecordTotalResponse);
    }

    @GetMapping("list/{employeeId}")
    @Operation(summary = "Listar pontos por funcionário", description = "Retorna todos pontos de um funcionário")
    public ResponseEntity<PointRecordTotalResponse> listByEmployeeId(@PathVariable("employeeId") Long employeeId,
                                                                     HttpServletRequest request) {
        Long userEmployeeId = getUserEmployeeId(request);
        if (!isManager(userEmployeeId)) return ResponseEntity.status(403).build();
        return getPointRecordTotalResponseResponseEntity(employeeId);
    }

    @GetMapping("/list-by-date/employees/{employeeId}/{date}")
    @Operation(summary = "Listar pontos de funcionário por data", description = "Retorna todos pontos de um funcionário por data")
    public ResponseEntity<PointRecordTotalResponse> listByDateAndEmployeeId(@PathVariable("employeeId") Long employeeId,
                                                                            @PathVariable("date") LocalDate date,
                                                                            HttpServletRequest request) {
        Long userEmployeeId = getUserEmployeeId(request);
        if (!isManager(userEmployeeId)) return ResponseEntity.status(403).build();
        return getPointRecordTotalResponseResponseEntity(employeeId, date);
    }

    @GetMapping("/list-full")
    @Operation(summary = "Listar todos os pontos", description = "Retorna todos os pontos")
    public ResponseEntity<List<PointRecordResponse>> listFull(HttpServletRequest request) {
        Long userEmployeeId = getUserEmployeeId(request);
        if (!isManager(userEmployeeId)) return ResponseEntity.status(403).build();
        List<PointRecord> pointRecord = pointRecordInputPort.list();
        return ResponseEntity.ok(pointRecordMapper.toPointRecordResponse(pointRecord));
    }

    private ResponseEntity<PointRecordTotalResponse> getPointRecordTotalResponseResponseEntity(@PathVariable("employeeId") Long employeeId, @PathVariable("date") LocalDate date) {
        List<PointRecord> pointRecord = pointRecordInputPort.listByDateAndEmployeeId(employeeId, date);
        LocalTime totalWorkedHours = WorkedHours.calculateTotal(pointRecord);
        List<PointRecordResponse> pointRecordResponse = pointRecordMapper.toPointRecordResponse(pointRecord);
        PointRecordTotalResponse pointRecordTotalResponse = new PointRecordTotalResponse(pointRecordResponse, totalWorkedHours);
        return ResponseEntity.ok(pointRecordTotalResponse);
    }

    private ResponseEntity<PointRecordTotalResponse> getPointRecordTotalResponseResponseEntity(@PathVariable("employeeId") Long employeeId) {
        List<PointRecord> pointRecord = pointRecordInputPort.listByEmployeeId(employeeId);
        LocalTime totalWorkedHours = WorkedHours.calculateTotal(pointRecord);
        List<PointRecordResponse> pointRecordResponse = pointRecordMapper.toPointRecordResponse(pointRecord);
        PointRecordTotalResponse pointRecordTotalResponse = new PointRecordTotalResponse(pointRecordResponse, totalWorkedHours);
        return ResponseEntity.ok(pointRecordTotalResponse);
    }

    private Long getUserEmployeeId(HttpServletRequest request) {
        return autenticationInputPort.getUserByToken(request).getEmployeeId();
    }

    private boolean isManager(Long employeeId) {
        return employeeInputPort.get(employeeId).getRole().equals(EmployeeRole.MANAGER);
    }
}
