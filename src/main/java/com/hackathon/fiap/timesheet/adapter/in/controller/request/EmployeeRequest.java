package com.hackathon.fiap.timesheet.adapter.in.controller.request;

import com.hackathon.fiap.timesheet.application.core.contants.EmployeeRole;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
    @NotNull
    private String name;
    @NotNull
    private EmployeeRole role;
}
