package com.hackathon.fiap.timesheet.adapter.in.controller.response;


import com.hackathon.fiap.timesheet.application.core.contants.EmployeeRole;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    private Long employeeId;
    private String name;
    private EmployeeRole role;
    private String userId;
    private Boolean active;
}