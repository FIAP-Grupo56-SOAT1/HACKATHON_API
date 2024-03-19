package com.hackathon.fiap.timesheet.application.core.interfaces;

import com.hackathon.fiap.timesheet.application.core.ports.out.ReportDataOutputPort;

public interface IReport {
    void generateReport(ReportDataOutputPort reportDataOutputPort);
}
