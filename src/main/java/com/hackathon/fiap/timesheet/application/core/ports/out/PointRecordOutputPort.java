package com.hackathon.fiap.timesheet.application.core.ports.out;

import com.hackathon.fiap.timesheet.application.core.domain.PointRecord;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PointRecordOutputPort {
    PointRecord save(PointRecord pointRecord);

    void delete(Long pointRecordId);

    Optional<PointRecord> get(Long pointRecordId);

    List<PointRecord> list();

    List<PointRecord> listByDate(LocalDate date);

    List<PointRecord> listByEmployeeId(Long employeeId);

    List<PointRecord> listByDateAndEmployeeId(Long employeeId, LocalDate date);

    List<PointRecord> findByEmployeeIdAndMonthAndYear(Long employeeId, LocalDate startDate, LocalDate endDate);
}
