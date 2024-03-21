package com.hackathon.fiap.timesheet.application.core.ports.out;

import com.hackathon.fiap.timesheet.application.core.reports.ReportData;

public interface ReportDataOutputPort<T, J> {
    ReportData<T> getReportData(J reportOrigin);
}
