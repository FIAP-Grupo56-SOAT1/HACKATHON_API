package com.hackathon.fiap.timesheet.adapter.out;

import com.hackathon.fiap.timesheet.adapter.out.repository.PointRecordRepository;
import com.hackathon.fiap.timesheet.adapter.out.repository.entity.PointRecordEntity;
import com.hackathon.fiap.timesheet.adapter.out.repository.mapper.PointRecordEntityMapper;
import com.hackathon.fiap.timesheet.application.core.domain.PointRecord;
import com.hackathon.fiap.timesheet.application.core.ports.out.PointRecordOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PointRecordAdapter implements PointRecordOutputPort {
    private final PointRecordRepository pointRecordRepository;
    private final PointRecordEntityMapper pointRecordEntityMapper;

    @Override
    public PointRecord save(PointRecord pointRecord) {
        PointRecordEntity pointRecordEntity = pointRecordEntityMapper.toPointRecordEntity(pointRecord);
        PointRecordEntity pointRecordSaved = pointRecordRepository.save(pointRecordEntity);
        return pointRecordEntityMapper.toPointRecord(pointRecordSaved);
    }

    @Override
    public void delete(Long pointRecordId) {
        pointRecordRepository.deleteById(pointRecordId);
    }

    @Override
    public Optional<PointRecord> get(Long pointRecordId) {
        return pointRecordRepository.findById(pointRecordId).map(pointRecordEntityMapper::toPointRecord);
    }

    @Override
    public List<PointRecord> list() {
        return pointRecordEntityMapper.toPointRecordList(pointRecordRepository.findAll());
    }

    @Override
    public List<PointRecord> listByDate(LocalDate date) {
        return pointRecordEntityMapper.toPointRecordList(pointRecordRepository.findByDate(date));
    }

    @Override
    public List<PointRecord> listByEmployeeId(Long employeeId) {
        return pointRecordEntityMapper.toPointRecordList(pointRecordRepository.findByEmployee_EmployeeId(employeeId));
    }

    @Override
    public List<PointRecord> listByDateAndEmployeeId(Long employeeId, LocalDate date) {
        return pointRecordEntityMapper.toPointRecordList(pointRecordRepository.findByEmployee_EmployeeIdAndDate(employeeId, date));
    }

    @Override
    public List<PointRecord> findByEmployeeIdAndMonthAndYear(Long employeeId, LocalDate startDate, LocalDate endDate) {
        return pointRecordRepository.findByEmployeeIdAndMonthAndYear(employeeId, startDate, endDate)
                .stream()
                .map(pointRecordEntityMapper::toPointRecord)
                .toList();
    }
}
