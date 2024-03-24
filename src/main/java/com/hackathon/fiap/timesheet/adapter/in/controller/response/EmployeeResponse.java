package com.hackathon.fiap.timesheet.adapter.in.controller.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.hackathon.fiap.timesheet.application.core.constant.EmployeeRole;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeResponse {
    private Long employeeId;
    private String name;
    private String email;
    private EmployeeRole role;
    private Boolean active;
}
