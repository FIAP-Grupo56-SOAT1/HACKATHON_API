package com.hackathon.fiap.timesheet.application.core.usecase;

import com.hackathon.fiap.timesheet.application.core.contants.PointRecordType;
import com.hackathon.fiap.timesheet.application.core.domain.PointRecord;
import com.hackathon.fiap.timesheet.application.core.ports.in.PointRecordInputPort;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class PointRecordUseCase implements PointRecordInputPort {

    @Override
    public PointRecord recordPoint(Long employeeId, PointRecordType type) {
        return null;
    }

    @Override
    public PointRecord manualRecordPoint(Long employeeId, LocalDate date, LocalTime time, PointRecordType type) {
        return null;
    }

    @Override
    public PointRecord get(Long pointRecordId) {
        return null;
    }

    @Override
    public List<PointRecord> list(Long employeeId) {
        return null;
    }

    @Override
    public List<PointRecord> listByDate(Long employeeId, LocalDate date) {
        return null;
    }
}
