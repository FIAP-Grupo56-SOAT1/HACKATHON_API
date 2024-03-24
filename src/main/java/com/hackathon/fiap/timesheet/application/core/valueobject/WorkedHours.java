package com.hackathon.fiap.timesheet.application.core.valueobject;

import com.hackathon.fiap.timesheet.application.core.constant.PointRecordType;
import com.hackathon.fiap.timesheet.application.core.domain.PointRecord;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class WorkedHours {
    private WorkedHours() {
    }

    public static LocalTime calculateTotal(List<PointRecord> pointRecords) {
        long totalMinutes = 0;
        LocalTime startTime = null;
        for (PointRecord pointRecord : pointRecords) {
            if (pointRecord.getType() == PointRecordType.IN) {
                startTime = pointRecord.getTime().truncatedTo(ChronoUnit.MINUTES);
            } else if (pointRecord.getType() == PointRecordType.OUT && startTime != null) {
                LocalTime endTime = pointRecord.getTime().truncatedTo(ChronoUnit.MINUTES);
                Duration duration = Duration.between(startTime, endTime);
                totalMinutes += duration.toMinutes();
                startTime = null;
            }
        }
        return LocalTime.of((int) (totalMinutes / 60), (int) (totalMinutes % 60));
    }
}
