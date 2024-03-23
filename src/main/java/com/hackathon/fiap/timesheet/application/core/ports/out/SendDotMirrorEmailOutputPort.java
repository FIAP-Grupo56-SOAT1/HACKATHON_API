package com.hackathon.fiap.timesheet.application.core.ports.out;


import com.hackathon.fiap.timesheet.application.core.reports.DefaultDotMirrorReportData;
import jakarta.mail.MessagingException;

public interface SendDotMirrorEmailOutputPort {
    void send(String email, DefaultDotMirrorReportData reportData);
}
