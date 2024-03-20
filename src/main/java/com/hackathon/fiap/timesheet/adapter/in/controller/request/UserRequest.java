package com.hackathon.fiap.timesheet.adapter.in.controller.request;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String userId;
    private String password;
    private Long employeeId;
}
