package com.hackathon.fiap.timesheet.adapter.in.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DefaultDotMirrorReportResponse {
    private Long employeeId;
    private String employeeName;
    private Integer month;
    private Integer year;
    private List<PointRecordReportDataResponse> pointRecords;

    private String totalWorkedTime;
}
