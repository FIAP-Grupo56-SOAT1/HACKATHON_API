package com.hackathon.fiap.timesheet.adapter.in.controller.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String userId;
    private Long employeeId;
    private Boolean active;
}
