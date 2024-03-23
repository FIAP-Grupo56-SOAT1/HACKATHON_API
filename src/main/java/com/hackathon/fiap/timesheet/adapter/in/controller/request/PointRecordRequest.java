package com.hackathon.fiap.timesheet.adapter.in.controller.request;

import com.hackathon.fiap.timesheet.application.core.contants.PointRecordType;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PointRecordRequest {
    private Long employeeId;
    private LocalDate date;
    private LocalTime time;
    private PointRecordType type;
}
