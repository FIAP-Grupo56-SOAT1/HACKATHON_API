package com.hackathon.fiap.timesheet.adapter.in.controller;

import com.hackathon.fiap.timesheet.adapter.in.controller.mapper.PointRecordMapper;
import com.hackathon.fiap.timesheet.adapter.in.controller.request.PointRecordRequest;
import com.hackathon.fiap.timesheet.adapter.in.controller.response.PointRecordResponse;
import com.hackathon.fiap.timesheet.adapter.in.controller.response.PointRecordTotalResponse;
import com.hackathon.fiap.timesheet.application.core.contants.PointRecordType;
import com.hackathon.fiap.timesheet.application.core.domain.PointRecord;
import com.hackathon.fiap.timesheet.application.core.ports.in.PointRecordInputPort;
import com.hackathon.fiap.timesheet.application.core.valueobject.WorkedHours;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @PostMapping("{employeeId}/record-point/{type}")
    @Operation(summary = "Registrar ponto", description = "Registra o ponto do funcionário")
    public ResponseEntity<PointRecordResponse> recordPoint(@PathVariable("employeeId") Long employeeId,
                                                           @PathVariable("type") PointRecordType type) {
        PointRecord pointRecord = pointRecordInputPort.recordPoint(employeeId, type);
        PointRecordResponse pointRecordResponse = pointRecordMapper.toPointRecordResponse(pointRecord);
        return ResponseEntity.ok(pointRecordResponse);
    }

    @PostMapping("/manual-record-point")
    @Operation(summary = "Registrar ponto manual", description = "Registra o ponto do funcionário manualmente")
    public ResponseEntity<PointRecordResponse> manualRecordPoint(@RequestBody @Valid PointRecordRequest pointRecordRequest) {
        PointRecord pointRecord =
                pointRecordInputPort.manualRecordPoint(pointRecordRequest.getEmployeeId(), pointRecordRequest.getDate(),
                                                       pointRecordRequest.getTime(), pointRecordRequest.getType());
        PointRecordResponse pointRecordResponse = pointRecordMapper.toPointRecordResponse(pointRecord);
        return ResponseEntity.ok(pointRecordResponse);
    }

    @GetMapping("{pointRecordId}")
    @Operation(summary = "Consultar ponto", description = "Retorna um ponto")
    public ResponseEntity<PointRecordResponse> findById(@PathVariable("pointRecordId") Long pointRecordId) {
        PointRecord pointRecord = pointRecordInputPort.get(pointRecordId);
        PointRecordResponse pointRecordResponse = pointRecordMapper.toPointRecordResponse(pointRecord);
        return ResponseEntity.ok(pointRecordResponse);
    }

    @GetMapping()
    @Operation(summary = "Listar pontos", description = "Retorna todos pontos")
    public ResponseEntity<PointRecordTotalResponse> list() {
        List<PointRecord> pointRecord = pointRecordInputPort.list();
        LocalTime totalWorkedHours = WorkedHours.calculateTotal(pointRecord);
        List<PointRecordResponse> pointRecordResponse = pointRecordMapper.toPointRecordResponse(pointRecord);
        PointRecordTotalResponse pointRecordTotalResponse = new PointRecordTotalResponse(pointRecordResponse, totalWorkedHours);
        return ResponseEntity.ok(pointRecordTotalResponse);
    }

    @GetMapping("list-by-date/{date}")
    @Operation(summary = "Listar pontos por data", description = "Retorna todos pontos por data")
    public ResponseEntity<PointRecordTotalResponse> listByDate(@PathVariable("date") LocalDate date) {
        List<PointRecord> pointRecord = pointRecordInputPort.listByDate(date);
        LocalTime totalWorkedHours = WorkedHours.calculateTotal(pointRecord);
        List<PointRecordResponse> pointRecordResponse = pointRecordMapper.toPointRecordResponse(pointRecord);
        PointRecordTotalResponse pointRecordTotalResponse = new PointRecordTotalResponse(pointRecordResponse, totalWorkedHours);
        return ResponseEntity.ok(pointRecordTotalResponse);
    }

    @GetMapping("list/{employeeId}")
    @Operation(summary = "Listar pontos", description = "Retorna todos pontos de um funcionário")
    public ResponseEntity<PointRecordTotalResponse> listByEmployeeId(@PathVariable("employeeId") Long employeeId) {
        List<PointRecord> pointRecord = pointRecordInputPort.listByEmployeeId(employeeId);
        LocalTime totalWorkedHours = WorkedHours.calculateTotal(pointRecord);
        List<PointRecordResponse> pointRecordResponse = pointRecordMapper.toPointRecordResponse(pointRecord);
        PointRecordTotalResponse pointRecordTotalResponse = new PointRecordTotalResponse(pointRecordResponse, totalWorkedHours);
        return ResponseEntity.ok(pointRecordTotalResponse);
    }

    @GetMapping("/list-by-date/employees/{employeeId}/{date}")
    @Operation(summary = "Listar pontos por data", description = "Retorna todos pontos de um funcionário por data")
    public ResponseEntity<PointRecordTotalResponse> listByDateAndEmployeeId(@PathVariable("employeeId") Long employeeId,
                                                                            @PathVariable("date") LocalDate date) {
        List<PointRecord> pointRecord = pointRecordInputPort.listByDateAndEmployeeId(employeeId, date);
        LocalTime totalWorkedHours = WorkedHours.calculateTotal(pointRecord);
        List<PointRecordResponse> pointRecordResponse = pointRecordMapper.toPointRecordResponse(pointRecord);
        PointRecordTotalResponse pointRecordTotalResponse = new PointRecordTotalResponse(pointRecordResponse, totalWorkedHours);
        return ResponseEntity.ok(pointRecordTotalResponse);
    }
}
