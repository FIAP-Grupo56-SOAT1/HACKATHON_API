package com.hackathon.fiap.timesheet.application.core.reports;

import com.hackathon.fiap.timesheet.application.core.contants.ReportType;

import java.time.LocalDateTime;

public abstract class ReportData<T> {
    private String reportName;
    private LocalDateTime generationDate;
    private ReportType reportType;

    protected ReportData(String reportName, ReportType reportType, LocalDateTime generationDate) {
        this.reportName = reportName;
        this.reportType = reportType;
        this.generationDate = generationDate;
    }

    public String getReportName() {
        return reportName;
    }

    protected void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public LocalDateTime getGenerationDate() {
        return generationDate;
    }

    protected void setGenerationDate(LocalDateTime generationDate) {
        this.generationDate = generationDate;
    }

    protected ReportType getReportType() {
        return reportType;
    }

    protected void setReportType(ReportType reportType) {
        this.reportType = reportType;
    }

    public ReportData<T> getData() {
        return this;
    }
}
