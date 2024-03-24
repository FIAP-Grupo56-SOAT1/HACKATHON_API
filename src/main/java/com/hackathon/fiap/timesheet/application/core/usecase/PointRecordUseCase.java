package com.hackathon.fiap.timesheet.application.core.usecase;

import com.hackathon.fiap.timesheet.application.core.constant.PointRecordType;
import com.hackathon.fiap.timesheet.application.core.domain.PointRecord;
import com.hackathon.fiap.timesheet.application.core.exptions.BusinessRuleException;
import com.hackathon.fiap.timesheet.application.core.exptions.EmployeeNotFoundException;
import com.hackathon.fiap.timesheet.application.core.ports.in.PointRecordInputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.EmployeeOutputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.PointRecordOutputPort;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class PointRecordUseCase implements PointRecordInputPort {
    private final PointRecordOutputPort pointRecordOutputPort;
    private final EmployeeOutputPort employeeOutputPort;

    public PointRecordUseCase(PointRecordOutputPort pointRecordOutputPort, EmployeeOutputPort employeeOutputPort) {
        this.pointRecordOutputPort = pointRecordOutputPort;
        this.employeeOutputPort = employeeOutputPort;
    }

    @Override
    public PointRecord recordPoint(Long employeeId, PointRecordType type) {
        validateEmployee(employeeId);
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        validatePointRecord(employeeId, date, time, type);
        PointRecord pointRecord = new PointRecord();
        pointRecord.setEmployeeId(employeeId);
        pointRecord.setDate(date);
        pointRecord.setTime(time);
        pointRecord.setType(type);
        return pointRecordOutputPort.save(pointRecord);
    }

    @Override
    public PointRecord manualRecordPoint(Long employeeId, LocalDate date, LocalTime time, PointRecordType type) {
        validateEmployee(employeeId);
        validatePointRecord(employeeId, date, time, type);
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        if (date.isAfter(today) || (date.isEqual(today) && time.isAfter(now)))
            throw new BusinessRuleException("You cannot register a point in the future");
        if (date.isBefore(today.minusMonths(3)))
            throw new BusinessRuleException("You cannot register a point older than 3 months");
        PointRecord pointRecord = new PointRecord();
        pointRecord.setEmployeeId(employeeId);
        pointRecord.setDate(date);
        pointRecord.setTime(time);
        pointRecord.setType(type);
        return pointRecordOutputPort.save(pointRecord);
    }

    @Override
    public PointRecord get(Long pointRecordId) {
        return pointRecordOutputPort.get(pointRecordId)
                .orElseThrow(() -> new EmployeeNotFoundException("Point record not found"));
    }

    @Override
    public List<PointRecord> list() {
        return pointRecordOutputPort.list();
    }

    @Override
    public List<PointRecord> listByDate(LocalDate date) {
        return pointRecordOutputPort.listByDate(date);
    }

    @Override
    public List<PointRecord> listByEmployeeId(Long employeeId) {
        return pointRecordOutputPort.listByEmployeeId(employeeId);
    }

    @Override
    public List<PointRecord> listByDateAndEmployeeId(Long employeeId, LocalDate date) {
        return pointRecordOutputPort.listByDateAndEmployeeId(employeeId, date);
    }

    private void validateEmployee(Long employeeId) {
        if (Boolean.FALSE.equals(employeeOutputPort.exists(employeeId)))
            throw new EmployeeNotFoundException("Employee not found");
    }

    private void validatePointRecord(Long employeeId, LocalDate date, LocalTime time, PointRecordType type) {
        Optional<PointRecord> pointRecord = pointRecordOutputPort.getLastPointRecord(employeeId, date);
        if (pointRecord.isPresent()) {
            PointRecordType lastType = pointRecord.get().getType();
            if (lastType.equals(type)) {
                PointRecordType nextRecordType = type.equals(PointRecordType.IN) ? PointRecordType.OUT : PointRecordType.IN;
                String message = String.format("You cannot perform a point registration %s without " +
                        "first performing a point registration of type %s", lastType, nextRecordType);
                throw new BusinessRuleException(message);
            }
            if (time.isBefore(pointRecord.get().getTime())) {
                Optional<PointRecord> firstPointRecord = pointRecordOutputPort.getFirstPointRecord(employeeId, date);
                if (firstPointRecord.isPresent() && time.isBefore(firstPointRecord.get().getTime()) && type.equals(PointRecordType.OUT)) {
                    String message = String.format("First point record of the day must be of type %s", PointRecordType.IN);
                    throw new BusinessRuleException(message);
                }
            }
        }
        if (pointRecord.isEmpty() && type.equals(PointRecordType.OUT)) {
            String message = String.format("First point record of the day must be of type %s", PointRecordType.IN);
            throw new BusinessRuleException(message);
        }
    }
}
