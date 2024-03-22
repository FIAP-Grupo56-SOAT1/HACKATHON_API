package com.hackathon.fiap.timesheet.adapter.in.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hackathon.fiap.timesheet.application.core.contants.PointRecordType;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PointRecordReportDataResponse {
    private Long pointRecordId;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
    @JsonFormat(pattern="HH:mm")
    private LocalTime time;
    private PointRecordType type;
    private Boolean valid;
}
