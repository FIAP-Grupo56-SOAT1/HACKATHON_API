package com.hackathon.fiap.timesheet.adapter.out.repository;

import com.hackathon.fiap.timesheet.adapter.out.repository.entity.PointRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PointRecordRepository extends JpaRepository<PointRecordEntity, Long> {
    List<PointRecordEntity> findByDate(LocalDate date);

    @Query("SELECT p " +
            "FROM   PointRecord p " +
            "WHERE  p.employee.employeeId = :employeeId " +
            "AND    p.date BETWEEN :startDate AND :endDate " +
            "ORDER BY p.date ASC, p.time ASC")
    List<PointRecordEntity> findByEmployeeIdAndMonthAndYear(Long employeeId, LocalDate startDate, LocalDate endDate);

    List<PointRecordEntity> findByEmployee_EmployeeId(Long employeeId);

    List<PointRecordEntity> findByEmployee_EmployeeIdAndDate(Long employeeId, LocalDate date);
}
