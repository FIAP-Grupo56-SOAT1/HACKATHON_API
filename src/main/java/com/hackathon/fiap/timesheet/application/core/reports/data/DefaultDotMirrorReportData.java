package com.hackathon.fiap.timesheet.application.core.reports.data;

import com.hackathon.fiap.timesheet.application.core.contants.ReportType;
import com.hackathon.fiap.timesheet.application.core.domain.PointRecord;
import com.hackathon.fiap.timesheet.application.core.reports.ReportData;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class DefaultDotMirrorReportData extends ReportData<DefaultDotMirrorReportData> {
    private final Long employeeId;
    private final String employeeName;
    private final Integer month;
    private List<PointRecord> pointRecords;
    private LocalTime totalWorkedTime;

    public DefaultDotMirrorReportData(Long employeeId, String employeeName, Integer month) {
        super("DefaultDotMirror", ReportType.POINT_RECORD, LocalDateTime.now());
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.month = month;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public Integer getMonth() {
        return month;
    }

    public List<PointRecord> getPointRecords() {
        return pointRecords;
    }

    public void setPointRecords(List<PointRecord> pointRecords) {
        this.pointRecords = pointRecords;
    }

    public LocalTime getTotalWorkedTime() {
        return totalWorkedTime;
    }

    public void setTotalWorkedTime(LocalTime totalWorkedTime) {
        this.totalWorkedTime = totalWorkedTime;
    }
}
