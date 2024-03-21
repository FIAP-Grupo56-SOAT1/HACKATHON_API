package com.hackathon.fiap.timesheet.adapter.in.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordUpdRequest {
    @NotNull
    private String password;
}
