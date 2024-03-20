package com.hackathon.fiap.timesheet.adapter.out.repository;

import com.hackathon.fiap.timesheet.adapter.out.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
}
