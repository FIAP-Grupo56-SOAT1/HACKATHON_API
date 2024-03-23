package com.hackathon.fiap.timesheet.adapter.in.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
    @JsonFormat(pattern="HH:mm")
    private LocalTime time;
    private PointRecordType type;
}
