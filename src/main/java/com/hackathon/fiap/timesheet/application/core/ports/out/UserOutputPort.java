package com.hackathon.fiap.timesheet.application.core.ports.out;

import com.hackathon.fiap.timesheet.application.core.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserOutputPort {
    User save(User user);

    void delete(String userId);

    Optional<User> get(String userId);

    Boolean exists(String userId);

    List<User> listUsers();
}
