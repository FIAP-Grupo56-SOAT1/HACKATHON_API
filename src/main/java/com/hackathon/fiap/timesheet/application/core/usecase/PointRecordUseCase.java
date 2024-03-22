package com.hackathon.fiap.timesheet.application.core.usecase;

import com.hackathon.fiap.timesheet.application.core.contants.PointRecordType;
import com.hackathon.fiap.timesheet.application.core.domain.PointRecord;
import com.hackathon.fiap.timesheet.application.core.exptions.EmployeeNotFoundException;
import com.hackathon.fiap.timesheet.application.core.ports.in.PointRecordInputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.EmployeeOutputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.PointRecordOutputPort;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
        PointRecord pointRecord = new PointRecord();
        pointRecord.setEmployeeId(employeeId);
        pointRecord.setDate(LocalDate.now());
        pointRecord.setTime(LocalTime.now());
        pointRecord.setType(type);
        return pointRecordOutputPort.save(pointRecord);
    }

    @Override
    public PointRecord manualRecordPoint(Long employeeId, LocalDate date, LocalTime time, PointRecordType type) {
        validateEmployee(employeeId);
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
}
