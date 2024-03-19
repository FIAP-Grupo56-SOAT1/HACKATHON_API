package com.hackathon.fiap.timesheet.application.core.valueobject;

import com.hackathon.fiap.timesheet.application.core.contants.ReportType;
import com.hackathon.fiap.timesheet.application.core.interfaces.IReport;
import com.hackathon.fiap.timesheet.application.core.ports.out.ReportDataOutputPort;

import java.time.LocalDateTime;

public class PointRecordReport implements IReport {
    private String reportName;
    private ReportType reportType;
    private LocalDateTime reportDate;
    private String reportContent;

    public PointRecordReport(String reportContent) {
        this.reportName = "Point Record Report";
        this.reportType = ReportType.POINT_RECORD;
        this.reportDate = LocalDateTime.now();
        this.reportContent = reportContent;
    }

    @Override
    public void generateReport(ReportDataOutputPort reportDataOutputPort) {
        reportContent = reportDataOutputPort.getReportContent();
    }
}
