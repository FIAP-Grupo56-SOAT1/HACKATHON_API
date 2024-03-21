package com.hackathon.fiap.timesheet.adapter.in.controller;

import com.hackathon.fiap.timesheet.adapter.out.DefaultDotMirrorReportDataAdapter;
import com.hackathon.fiap.timesheet.application.core.ports.in.DotMirrorReportInputPort;
import com.hackathon.fiap.timesheet.application.core.reports.ReportData;
import com.hackathon.fiap.timesheet.application.core.reports.impl.DefaultDotMirrorReport;
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
    private final DefaultDotMirrorReportDataAdapter defaultDotMirrorReportDataAdapter;

    @PostMapping("/generate/{employeeId}/dot-mirror")
    @Operation(summary = "Gerar relatório espelho de ponto", description = "Gera o relatório espelho de ponto de um funcionário")
    public ResponseEntity<Void> generateDotMirrorReport(@PathVariable("employeeId") Long employeeId) {
        DefaultDotMirrorReport defaultDotMirrorReport = new DefaultDotMirrorReport(defaultDotMirrorReportDataAdapter);
        ReportData reportData = dotMirrorReportInputPort.generate(employeeId, defaultDotMirrorReport);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/generate/{employeeId}/dot-mirror/{year}/{month}")
    @Operation(summary = "Gerar relatório espelho de ponto para um mês e ano especifico", description = "Gera o relatório espelho de ponto de um mês e ano especifico de um funcionário ")
    public ResponseEntity<Void> generateDotMirrorReport(@PathVariable("employeeId") Long employeeId,
                                                        @PathVariable("month") Integer month,
                                                        @PathVariable("year") Integer year) {
        return ResponseEntity.ok().build();
    }
}
