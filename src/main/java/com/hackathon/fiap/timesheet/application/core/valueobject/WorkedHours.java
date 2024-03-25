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

    public static String calculateTotal(List<PointRecord> pointRecords) {
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
        Duration total = Duration.ofMinutes(totalMinutes);
        return String.format("%d:%02d", total.toHours(), total.toMinutesPart());
    }
}
