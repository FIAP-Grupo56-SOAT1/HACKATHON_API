package com.hackathon.fiap.timesheet.application.core.ports.out;

import com.hackathon.fiap.timesheet.application.core.valueobject.ReportData;

public interface ReportDataOutputPort<T> {
    ReportData<T> getReportData();
}
