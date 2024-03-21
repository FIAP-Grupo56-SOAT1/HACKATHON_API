package com.hackathon.fiap.timesheet.adapter.in.controller;

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

    @PostMapping("/generate/{employeeId}/dot-mirror")
    @Operation(summary = "Gerar relatório espelho de ponto", description = "Gera o relatório espelho de ponto de um funcionário")
    public ResponseEntity<Void> generateDotMirrorReport(@PathVariable("employeeId") Long employeeId) {
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
