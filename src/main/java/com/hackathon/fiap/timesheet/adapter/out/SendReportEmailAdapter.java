package com.hackathon.fiap.timesheet.adapter.out;

import com.hackathon.fiap.timesheet.application.core.ports.out.SendReportEmailOutputPort;
import com.hackathon.fiap.timesheet.application.core.reports.ReportData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendReportEmailAdapter implements SendReportEmailOutputPort {

    @Override
    public void sendEmail(String email, ReportData reportData) {

    }
}
