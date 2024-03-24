package com.hackathon.fiap.timesheet.application.core.usecase;

import com.hackathon.fiap.timesheet.application.core.constant.PointRecordType;
import com.hackathon.fiap.timesheet.application.core.domain.Employee;
import com.hackathon.fiap.timesheet.application.core.domain.PointRecord;
import com.hackathon.fiap.timesheet.application.core.exptions.EmployeeNotFoundException;
import com.hackathon.fiap.timesheet.application.core.ports.in.DotMirrorReportInputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.EmployeeOutputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.PointRecordOutputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.SendDotMirrorEmailOutputPort;
import com.hackathon.fiap.timesheet.application.core.reports.DefaultDotMirrorReportData;
import com.hackathon.fiap.timesheet.application.core.valueobject.PointRecordReportData;
import com.hackathon.fiap.timesheet.application.core.valueobject.WorkedHours;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class DotMirrorReportUseCase implements DotMirrorReportInputPort {
    private final EmployeeOutputPort employeeOutputPort;
    private final PointRecordOutputPort pointRecordOutputPort;
    private final SendDotMirrorEmailOutputPort sendDotMirrorEmailOutputPort;

    public DotMirrorReportUseCase(EmployeeOutputPort employeeOutputPort,
                                  PointRecordOutputPort pointRecordOutputPort,
                                  SendDotMirrorEmailOutputPort sendDotMirrorEmailOutputPort) {
        this.employeeOutputPort = employeeOutputPort;
        this.pointRecordOutputPort = pointRecordOutputPort;
        this.sendDotMirrorEmailOutputPort = sendDotMirrorEmailOutputPort;
    }

    @Override
    public DefaultDotMirrorReportData generate(Long employeeId) {
        Employee employee = employeeOutputPort.get(employeeId).orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        YearMonth thisMonth = YearMonth.now();
        int lastMonth = thisMonth.minusMonths(1).getMonthValue();
        int year = thisMonth.getYear();
        LocalDate startDate = LocalDate.of(year, lastMonth, 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);

        DefaultDotMirrorReportData defaultDotMirrorReportData = new DefaultDotMirrorReportData();
        defaultDotMirrorReportData.setEmployeeId(employee.getEmployeeId());
        defaultDotMirrorReportData.setEmployeeName(employee.getName());
        defaultDotMirrorReportData.setMonth(lastMonth);
        defaultDotMirrorReportData.setYear(year);
        List<PointRecord> pointRecords =
                pointRecordOutputPort.findByEmployeeIdAndMonthAndYear(employee.getEmployeeId(), startDate, endDate);
        List<PointRecordReportData> pointRecordReportData = transformToReportData(pointRecords);
        defaultDotMirrorReportData.setPointRecords(pointRecordReportData);
        defaultDotMirrorReportData.setTotalWorkedTime(LocalTime.of(0, 0));
        if (!defaultDotMirrorReportData.getPointRecords().isEmpty()) {
            LocalTime totalWorkedTime = WorkedHours.calculateTotal(filterInAndOutPairs(pointRecords));
            defaultDotMirrorReportData.setTotalWorkedTime(totalWorkedTime);
        }
        return defaultDotMirrorReportData;
    }

    @Override
    public void generateAndSendByEmail(String email, Long employeeId) {
        DefaultDotMirrorReportData defaultDotMirrorReportData = generate(employeeId);
        sendDotMirrorEmailOutputPort.send(email, defaultDotMirrorReportData);
    }

    private List<PointRecord> filterInAndOutPairs(List<PointRecord> pointRecords) {
        List<PointRecord> filteredRecords = new ArrayList<>();
        int i = 0;
        while (i < pointRecords.size() - 1) {
            PointRecord currentRecord = pointRecords.get(i);
            PointRecord nextRecord = pointRecords.get(i + 1);
            if (currentRecord.getType() == PointRecordType.IN && nextRecord.getType() == PointRecordType.OUT) {
                filteredRecords.add(currentRecord);
                filteredRecords.add(nextRecord);
                i++;
            }
            i++;
        }
        return filteredRecords;
    }

    public List<PointRecordReportData> transformToReportData(List<PointRecord> pointRecords) {
        List<PointRecordReportData> reportDataList = new ArrayList<>();
        for (int i = 0; i < pointRecords.size(); i++) {
            PointRecord currentRecord = pointRecords.get(i);
            PointRecordReportData reportData = new PointRecordReportData();
            reportData.setPointRecordId(currentRecord.getPointRecordId());
            reportData.setDate(currentRecord.getDate());
            reportData.setTime(currentRecord.getTime());
            reportData.setType(currentRecord.getType());
            reportData.setValid(validateRecord(currentRecord, pointRecords));
            reportDataList.add(reportData);
        }
        return reportDataList;
    }

    private boolean validateRecord(PointRecord currentRecord, List<PointRecord> pointRecords) {
        int i = pointRecords.indexOf(currentRecord);
        if (i < pointRecords.size() - 1) {
            PointRecord nextRecord = pointRecords.get(i + 1);
            if (currentRecord.getType() == PointRecordType.IN && nextRecord.getType() == PointRecordType.OUT) {
                return true;
            } else
                return currentRecord.getType() == PointRecordType.OUT && pointRecords.get(i - 1).getType() == PointRecordType.IN;
        } else
            return currentRecord.getType() == PointRecordType.OUT && pointRecords.get(i - 1).getType() == PointRecordType.IN;
    }
}