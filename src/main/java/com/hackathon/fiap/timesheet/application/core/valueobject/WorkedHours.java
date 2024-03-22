package com.hackathon.fiap.timesheet.application.core.valueobject;

import com.hackathon.fiap.timesheet.application.core.contants.PointRecordType;
import com.hackathon.fiap.timesheet.application.core.domain.PointRecord;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class WorkedHours {
    public static LocalTime calculateTotal(List<PointRecord> pointRecords) {
        long totalMinutes = 0;
        LocalTime startTime = null;
        for (PointRecord record : pointRecords) {
            if (record.getType() == PointRecordType.IN) {
                startTime = record.getTime().truncatedTo(ChronoUnit.MINUTES);
            } else if (record.getType() == PointRecordType.OUT && startTime != null) {
                LocalTime endTime = record.getTime().truncatedTo(ChronoUnit.MINUTES);
                Duration duration = Duration.between(startTime, endTime);
                totalMinutes += duration.toMinutes();
                startTime = null;
            }
        }
        return LocalTime.of((int) (totalMinutes / 60), (int) (totalMinutes % 60));
    }
}
