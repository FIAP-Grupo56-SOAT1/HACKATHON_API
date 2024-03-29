package com.hackathon.fiap.timesheet.application.core.ports.out;

import com.hackathon.fiap.timesheet.adapter.out.repository.entity.UserEntity;
import com.hackathon.fiap.timesheet.application.core.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserOutputPort {
    User save(User user);

    User save(User user, String password);

    void setPassword(String userId, String password);

    void delete(String userId);

    Optional<User> get(String userId);

    Optional<User> getByEmployeeId(Long employeeId);

    Boolean exists(String userId);

    List<User> listUsers();

    Optional<UserEntity> findByUserName(String userName);
}
