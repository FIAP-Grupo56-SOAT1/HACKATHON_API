package com.hackathon.fiap.timesheet.adapter.in.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    private String userId;
    private Long employeeId;
}
