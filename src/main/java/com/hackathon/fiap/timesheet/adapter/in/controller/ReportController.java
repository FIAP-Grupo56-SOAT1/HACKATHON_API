package com.hackathon.fiap.timesheet.adapter.in.controller;

import com.hackathon.fiap.timesheet.adapter.in.controller.mapper.DefaultDotMirrorReportMapper;
import com.hackathon.fiap.timesheet.adapter.in.controller.response.DefaultDotMirrorReportResponse;
import com.hackathon.fiap.timesheet.application.core.ports.in.DotMirrorReportInputPort;
import com.hackathon.fiap.timesheet.application.core.ports.in.EmployeeInputPort;
import com.hackathon.fiap.timesheet.application.core.reports.DefaultDotMirrorReportData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("reports")
@Tag(name = "Relatórios", description = "Controller que gerencia a emisão de relatório")
public class ReportController {
    private final DotMirrorReportInputPort dotMirrorReportInputPort;
    private final DefaultDotMirrorReportMapper defaultDotMirrorReportMapper;
    private final EmployeeInputPort employeeInputPort;

    @PostMapping("/generate/{employeeId}/dot-mirror")
    @Operation(summary = "Gerar relatório espelho de ponto", description = "Gera o relatório espelho de ponto de um funcionário")
    public ResponseEntity<DefaultDotMirrorReportResponse> generateDotMirrorReport(@PathVariable("employeeId") Long employeeId) {
        DefaultDotMirrorReportData defaultDotMirrorReportData = dotMirrorReportInputPort.generate(employeeId);
        DefaultDotMirrorReportResponse defaultDotMirrorReportResponse = defaultDotMirrorReportMapper.toResponse(defaultDotMirrorReportData);
        return ResponseEntity.ok(defaultDotMirrorReportResponse);
    }

    @PostMapping("/generate/dot-mirror/send-mail/{employeeId}")
    @Operation(summary = "Envia o relatório espelho de ponto por e-mail", description = "Gera o relatório espelho de ponto e envia por e-mail para o funcionário")
    public ResponseEntity<Void> generateAndSendByEmail(@PathVariable("employeeId") Long employeeId) {
        String email = employeeInputPort.get(employeeId).getEmail();
        dotMirrorReportInputPort.generateAndSendByEmail(email, employeeId);
        return ResponseEntity.ok().build();
    }
}
