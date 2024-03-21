package com.hackathon.fiap.timesheet.adapter.out;

import com.hackathon.fiap.timesheet.adapter.out.repository.PointRecordRepository;
import com.hackathon.fiap.timesheet.adapter.out.repository.mapper.PointRecordEntityMapper;
import com.hackathon.fiap.timesheet.application.core.contants.PointRecordType;
import com.hackathon.fiap.timesheet.application.core.domain.Employee;
import com.hackathon.fiap.timesheet.application.core.domain.PointRecord;
import com.hackathon.fiap.timesheet.application.core.ports.out.ReportDataOutputPort;
import com.hackathon.fiap.timesheet.application.core.reports.ReportData;
import com.hackathon.fiap.timesheet.application.core.reports.data.DefaultDotMirrorReportData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DefaultDotMirrorReportDataAdapter implements ReportDataOutputPort<DefaultDotMirrorReportData, Employee> {
    private final PointRecordRepository pointRecordRepository;
    private final PointRecordEntityMapper pointRecordEntityMapper;

    @Override
    public ReportData<DefaultDotMirrorReportData> getReportData(Employee reportOrigin) {
        YearMonth thisMonth = YearMonth.now();
        int lastMonth = thisMonth.minusMonths(1).getMonthValue();
        int year = thisMonth.getYear();
        DefaultDotMirrorReportData defaultDotMirrorReportData = new DefaultDotMirrorReportData(reportOrigin.getEmployeeId(), reportOrigin.getName(), lastMonth);

        LocalDate startDate = LocalDate.of(year, lastMonth, 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);
        List<PointRecord> pointRecords =
                pointRecordRepository.findByEmployeeIdAndMonthAndYear(reportOrigin.getEmployeeId(), startDate, endDate)
                        .stream()
                        .map(pointRecordEntityMapper::toPointRecord)
                        .toList();

        defaultDotMirrorReportData.setPointRecords(pointRecords);
        defaultDotMirrorReportData.setTotalWorkedTime(LocalTime.of(0, 0));
        if (!defaultDotMirrorReportData.getPointRecords().isEmpty()) {
            LocalTime totalWorkedTime = calculateTotalWorkedHours(pointRecords);
            defaultDotMirrorReportData.setTotalWorkedTime(totalWorkedTime);
        }
        return defaultDotMirrorReportData;
    }

    private LocalTime calculateTotalWorkedHours(List<PointRecord> pointRecords) {
        pointRecords.sort(Comparator.comparing(PointRecord::getDate).thenComparing(PointRecord::getTime));

        long totalMinutes = 0;
        LocalTime startTime = null;

        for (PointRecord record : pointRecords) {
            if (record.getType() == PointRecordType.IN) {
                startTime = record.getTime();
            } else if (record.getType() == PointRecordType.OUT && startTime != null) {
                Duration duration = Duration.between(startTime, record.getTime());
                totalMinutes += duration.toMinutes();
                startTime = null;
            }
        }

        return LocalTime.of((int) (totalMinutes / 60), (int) (totalMinutes % 60));
    }
}
