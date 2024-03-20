package com.hackathon.fiap.timesheet.adapter.in.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @Email
    @NotNull
    private String userId;
    @NotNull
    private String password;
    @NotNull
    private Long employeeId;
}
