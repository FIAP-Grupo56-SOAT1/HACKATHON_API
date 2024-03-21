package com.hackathon.fiap.timesheet.application.core.ports.in;

import com.hackathon.fiap.timesheet.application.core.reports.ReportData;
import com.hackathon.fiap.timesheet.application.core.reports.ReportGenerator;

public interface DotMirrorReportInputPort<T, J> {
    ReportData<T> generate(Long employeeId, ReportGenerator<T, J> reportGenerator);
}
