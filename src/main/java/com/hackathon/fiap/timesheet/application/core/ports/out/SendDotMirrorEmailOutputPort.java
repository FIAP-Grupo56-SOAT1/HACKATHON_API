package com.hackathon.fiap.timesheet.application.core.ports.out;


import com.hackathon.fiap.timesheet.application.core.reports.DefaultDotMirrorReportData;
import org.springframework.scheduling.annotation.Async;

public interface SendDotMirrorEmailOutputPort {
    @Async
    void send(String email, DefaultDotMirrorReportData reportData);
}
