package com.hackathon.fiap.timesheet.application.core.ports.out;

import com.hackathon.fiap.timesheet.application.core.domain.User;
import jakarta.servlet.http.HttpServletRequest;

public interface GetUserByTokenOutputPort {
    User GetUserByToken(HttpServletRequest request);
}
