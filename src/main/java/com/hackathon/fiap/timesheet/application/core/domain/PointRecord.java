package com.hackathon.fiap.timesheet.application.core.domain;

import com.hackathon.fiap.timesheet.application.core.constant.PointRecordType;

import java.time.LocalDate;
import java.time.LocalTime;

public class PointRecord {
    private Long pointRecordId;
    private Long employeeId;
    private LocalDate date;
    private LocalTime time;
    private PointRecordType type;

    public PointRecord() {
    }

    public PointRecord(Long pointRecordId, Long employeeId, LocalDate date, LocalTime time, PointRecordType type) {
        this.pointRecordId = pointRecordId;
        this.employeeId = employeeId;
        this.date = date;
        this.time = time;
        this.type = type;
    }

    public Long getPointRecordId() {
        return pointRecordId;
    }

    public void setPointRecordId(Long pointRecordId) {
        this.pointRecordId = pointRecordId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public PointRecordType getType() {
        return type;
    }

    public void setType(PointRecordType type) {
        this.type = type;
    }
}
