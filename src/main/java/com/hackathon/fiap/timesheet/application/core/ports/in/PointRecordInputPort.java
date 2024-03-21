package com.hackathon.fiap.timesheet.application.core.ports.in;

import com.hackathon.fiap.timesheet.application.core.contants.PointRecordType;
import com.hackathon.fiap.timesheet.application.core.domain.PointRecord;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface PointRecordInputPort {
    PointRecord recordPoint(Long employeeId, PointRecordType type);

    PointRecord manualRecordPoint(Long employeeId, LocalDate date, LocalTime time, PointRecordType type);

    PointRecord get(Long pointRecordId);

    List<PointRecord> list(Long employeeId);

    List<PointRecord> listByDate(Long employeeId, LocalDate date);
}
