package com.hackathon.fiap.timesheet.application.core.ports.in;

import com.hackathon.fiap.timesheet.application.core.constant.PointRecordType;
import com.hackathon.fiap.timesheet.application.core.domain.PointRecord;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface PointRecordInputPort {
    PointRecord recordPoint(Long employeeId, PointRecordType type);

    PointRecord manualRecordPoint(Long employeeId, LocalDate date, LocalTime time, PointRecordType type);

    PointRecord get(Long pointRecordId);

    List<PointRecord> list();

    List<PointRecord> listByDate(LocalDate date);

    List<PointRecord> listByEmployeeId(Long employeeId);

    List<PointRecord> listByDateAndEmployeeId(Long employeeId, LocalDate date);
}
