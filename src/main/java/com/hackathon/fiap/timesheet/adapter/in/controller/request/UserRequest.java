package com.hackathon.fiap.timesheet.adapter.in.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequest {
    private String userId;
    private String password;
    private Long employeeId;
}
