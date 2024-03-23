package com.hackathon.fiap.timesheet.application.core.ports.out;

import com.hackathon.fiap.timesheet.adapter.out.repository.entity.UserEntity;

public interface GenerateTokenOutputPort {
    String generateTokenJwt(UserEntity user);
}
