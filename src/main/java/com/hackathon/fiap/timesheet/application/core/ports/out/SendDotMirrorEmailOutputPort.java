package com.hackathon.fiap.timesheet.application.core.ports.out;


import com.hackathon.fiap.timesheet.application.core.reports.DefaultDotMirrorReportData;

public interface SendDotMirrorEmailOutputPort {
    void send(String email, DefaultDotMirrorReportData reportData);
}
