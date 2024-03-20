package com.hackathon.fiap.timesheet.adapter.out;

import com.hackathon.fiap.timesheet.application.core.domain.User;
import com.hackathon.fiap.timesheet.application.core.ports.out.UserOutputPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserAdapter implements UserOutputPort {

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public void delete(String userId) {

    }

    @Override
    public Optional<User> get(String userId) {
        return Optional.empty();
    }

    @Override
    public Boolean exists(String userId) {
        return null;
    }

    @Override
    public Optional<List<User>> listUsers() {
        return Optional.empty();
    }
}
