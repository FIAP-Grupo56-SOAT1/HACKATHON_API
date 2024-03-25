package com.hackathon.fiap.timesheet.application.core.reports;

import com.hackathon.fiap.timesheet.application.core.valueobject.PointRecordReportData;

import java.util.List;

public class DefaultDotMirrorReportData {
    private Long employeeId;
    private String employeeName;
    private Integer month;
    private Integer year;
    private List<PointRecordReportData> pointRecords;
    private String totalWorkedTime;

    public DefaultDotMirrorReportData() {
    }

    public DefaultDotMirrorReportData(Long employeeId, String employeeName, Integer month, Integer year, List<PointRecordReportData> pointRecords, String totalWorkedTime) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.month = month;
        this.year = year;
        this.pointRecords = pointRecords;
        this.totalWorkedTime = totalWorkedTime;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<PointRecordReportData> getPointRecords() {
        return pointRecords;
    }

    public void setPointRecords(List<PointRecordReportData> pointRecords) {
        this.pointRecords = pointRecords;
    }

    public String getTotalWorkedTime() {
        return totalWorkedTime;
    }

    public void setTotalWorkedTime(String totalWorkedTime) {
        this.totalWorkedTime = totalWorkedTime;
    }
}
