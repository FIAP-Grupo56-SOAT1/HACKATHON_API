package com.hackathon.fiap.timesheet.application.core.reports;

import com.hackathon.fiap.timesheet.application.core.contants.ReportType;
import com.hackathon.fiap.timesheet.application.core.ports.out.ReportDataOutputPort;

public abstract class ReportGenerator<T, J> {
    private String reportName;
    private ReportType reportType;
    private J reportOrigin;
    private ReportDataOutputPort<T, J> reportDataOutputPort;

    public ReportGenerator(String reportName, ReportType reportType, ReportDataOutputPort reportDataOutputPort) {
        this.reportName = reportName;
        this.reportType = reportType;
        this.reportDataOutputPort = reportDataOutputPort;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public ReportType getReportType() {
        return reportType;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType;
    }

    public void setReportOrigin(J reportOrigin) {
        this.reportOrigin = reportOrigin;
    }

    public ReportData<T> getReportData() {
        return reportDataOutputPort.getReportData(reportOrigin);
    }
}
