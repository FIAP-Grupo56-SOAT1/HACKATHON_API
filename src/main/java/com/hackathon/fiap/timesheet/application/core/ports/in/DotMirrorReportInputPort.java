package com.hackathon.fiap.timesheet.application.core.ports.in;

import com.hackathon.fiap.timesheet.application.core.reports.DefaultDotMirrorReportData;

public interface DotMirrorReportInputPort {
    DefaultDotMirrorReportData generate(Long employeeId);
    void generateAndSendByEmail(String email, Long employeeId);
}
