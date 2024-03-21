package com.hackathon.fiap.timesheet.adapter.in.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserActivationRequest {
    @NotNull
    private Boolean active;
}
