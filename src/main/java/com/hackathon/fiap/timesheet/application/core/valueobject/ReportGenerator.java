package com.hackathon.fiap.timesheet.application.core.valueobject;

import com.hackathon.fiap.timesheet.application.core.contants.ReportType;
import com.hackathon.fiap.timesheet.application.core.ports.out.ReportDataOutputPort;

import java.util.List;

public abstract class ReportGenerator<T, J> {
    private String reportName;
    private ReportType reportType;
    private ReportDataOutputPort<J> reportDataOutputPort;

    protected ReportGenerator(String reportName, ReportType reportType, ReportDataOutputPort reportDataOutputPort) {
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

    public ReportData<J> getReportData() {
        return reportDataOutputPort.getReportData();
    }
}
