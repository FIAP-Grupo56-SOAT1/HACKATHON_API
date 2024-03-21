package com.hackathon.fiap.timesheet.adapter.out.repository;

import com.hackathon.fiap.timesheet.adapter.out.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByEmployee_EmployeeId(Long employeeId);
    Optional<UserEntity> findByUserIdAndActiveTrue(String userId);
    List<UserEntity> findAllByActiveTrue();
}
