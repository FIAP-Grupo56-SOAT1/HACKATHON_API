package com.hackathon.fiap.timesheet.adapter.out.repository;

import com.hackathon.fiap.timesheet.adapter.out.repository.entity.PointRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRecordEntityRepository extends JpaRepository<PointRecordEntity, Long> {
}
