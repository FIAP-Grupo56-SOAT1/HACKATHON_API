package com.hackathon.fiap.timesheet.adapter.in.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PointRecordTotalResponse {
    private List<PointRecordResponse> pointRecordResponseList;
    private String totalWorkedHours;
}
