package com.hackathon.fiap.timesheet.adapter.in.controller.response;

import com.hackathon.fiap.timesheet.application.core.contants.ReportType;
import com.hackathon.fiap.timesheet.application.core.reports.ReportData;
import com.hackathon.fiap.timesheet.application.core.reports.data.DefaultDotMirrorReportData;

import java.time.LocalDateTime;

public class DefaultDotMirrorReportDataWrapper extends ReportData<DefaultDotMirrorReportData> {
    protected DefaultDotMirrorReportDataWrapper(String reportName, ReportType reportType, LocalDateTime generationDate) {
        super(reportName, reportType, generationDate);
    }
    
    
}
