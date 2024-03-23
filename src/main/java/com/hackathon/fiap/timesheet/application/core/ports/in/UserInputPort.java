package com.hackathon.fiap.timesheet.application.core.ports.in;

import com.hackathon.fiap.timesheet.adapter.out.repository.entity.UserEntity;
import com.hackathon.fiap.timesheet.application.core.domain.User;

import java.util.List;

public interface UserInputPort {
    User create(String userId, String password, Long employeeId);

    void updatePassword(String userId, String password);

    User updateStatus(String userId, Boolean active);

    void delete(String userId);

    User get(String userId);

    List<User> listUsers();

    UserEntity findByUserName(String userName);
}
