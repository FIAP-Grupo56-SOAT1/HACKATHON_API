package com.hackathon.fiap.timesheet.application.core.valueobject;

import com.hackathon.fiap.timesheet.application.core.contants.PointRecordType;

import java.time.LocalDate;
import java.time.LocalTime;

public class PointRecordReportData {
    private Long pointRecordId;
    private LocalDate date;
    private LocalTime time;
    private PointRecordType type;
    private Boolean valid;

    public PointRecordReportData() {
    }

    public PointRecordReportData(Long pointRecordId, LocalDate date, LocalTime time, PointRecordType type, Boolean valid) {
        this.pointRecordId = pointRecordId;
        this.date = date;
        this.time = time;
        this.type = type;
        this.valid = valid;
    }

    public Long getPointRecordId() {
        return pointRecordId;
    }

    public void setPointRecordId(Long pointRecordId) {
        this.pointRecordId = pointRecordId;
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

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }
}
