package com.hackathon.fiap.timesheet.adapter.in.controller.response;

import com.hackathon.fiap.timesheet.application.core.contants.PointRecordType;
import lombok.*;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PointRecordTotalResponse {
    private List<PointRecordResponse> pointRecordResponseList;
    private LocalTime totalWorkedHours;

    public PointRecordTotalResponse(List<PointRecordResponse> pointRecordResponseList) {
        this.pointRecordResponseList = pointRecordResponseList;
        this.totalWorkedHours = calculateTotalWorkedHours();
    }

    public void setPointRecordResponseList(List<PointRecordResponse> pointRecordResponseList) {
        this.pointRecordResponseList = pointRecordResponseList;
        this.totalWorkedHours = calculateTotalWorkedHours();
    }

    private LocalTime calculateTotalWorkedHours() {
        pointRecordResponseList.sort(Comparator.comparing(PointRecordResponse::getDate).thenComparing(PointRecordResponse::getTime));

        long totalMinutes = 0;
        LocalTime startTime = null;

        for (PointRecordResponse record : pointRecordResponseList) {
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
