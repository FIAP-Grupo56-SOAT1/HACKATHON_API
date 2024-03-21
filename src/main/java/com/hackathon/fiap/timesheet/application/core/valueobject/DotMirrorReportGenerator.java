package com.hackathon.fiap.timesheet.application.core.valueobject;

import com.hackathon.fiap.timesheet.application.core.contants.ReportType;
import com.hackathon.fiap.timesheet.application.core.ports.out.ReportDataOutputPort;
import com.hackathon.fiap.timesheet.application.core.reports.ReportGenerator;

public class DotMirrorReportGenerator extends ReportGenerator {
    protected DotMirrorReportGenerator(String reportName, ReportType reportType, ReportDataOutputPort reportDataOutputPort) {
        super(reportName, reportType, reportDataOutputPort);
    }
}
