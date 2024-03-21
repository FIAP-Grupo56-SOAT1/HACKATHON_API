package com.hackathon.fiap.timesheet.application.core.reports.impl;

import com.hackathon.fiap.timesheet.application.core.contants.ReportType;
import com.hackathon.fiap.timesheet.application.core.domain.Employee;
import com.hackathon.fiap.timesheet.application.core.ports.out.ReportDataOutputPort;
import com.hackathon.fiap.timesheet.application.core.reports.ReportGenerator;
import com.hackathon.fiap.timesheet.application.core.reports.data.DefaultDotMirrorReportData;

public class DefaultDotMirrorReport extends ReportGenerator<DefaultDotMirrorReportData, Employee> {
    public DefaultDotMirrorReport(ReportDataOutputPort<DefaultDotMirrorReportData, Employee> reportDataOutputPort) {
        super("DefaultDotMirror",ReportType.POINT_RECORD, reportDataOutputPort);
    }
}
