package com.hackathon.fiap.timesheet.adapter.out;

import com.hackathon.fiap.timesheet.adapter.out.repository.UserRepository;
import com.hackathon.fiap.timesheet.adapter.out.repository.entity.UserEntity;
import com.hackathon.fiap.timesheet.adapter.out.repository.mapper.UserEntityMapper;
import com.hackathon.fiap.timesheet.application.core.domain.User;
import com.hackathon.fiap.timesheet.application.core.ports.out.UserOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserAdapter implements UserOutputPort {
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public User save(User user) {
        UserEntity userEntity = userEntityMapper.toUserEntity(user);
        UserEntity userSaved = userRepository.save(userEntity);
        return userEntityMapper.toUser(userSaved);
    }

    @Override
    public User save(User user, String password) {
        UserEntity userEntity = userEntityMapper.toUserEntity(user);
        userEntity.setPassword(password);
        UserEntity userSaved = userRepository.save(userEntity);
        return userEntityMapper.toUser(userSaved);
    }

    @Override
    public void setPassword(String userId, String password) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow();
        userEntity.setPassword(password);
        userRepository.save(userEntity);
    }

    @Override
    public void delete(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Optional<User> get(String userId) {
        return userRepository.findByUserIdAndActiveTrue(userId).map(userEntityMapper::toUser);
    }

    @Override
    public Optional<User> getByEmployeeId(Long employeeId) {
        return userRepository.findByEmployee_EmployeeId(employeeId).map(userEntityMapper::toUser);
    }

    @Override
    public Boolean exists(String userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public List<User> listUsers() {
        return userRepository.findAllByActiveTrue().stream().map(userEntityMapper::toUser).toList();
    }

    @Override
    public Optional<UserEntity> findByUserName(String userName) {
        return userRepository.findByUserIdAndActiveTrue(userName);
    }
}
