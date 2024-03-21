package com.hackathon.fiap.timesheet.application.core.ports.out;

import com.hackathon.fiap.timesheet.application.core.reports.ReportData;

public interface SendReportEmailOutputPort {
    void sendEmail(String email, ReportData reportData);
}
