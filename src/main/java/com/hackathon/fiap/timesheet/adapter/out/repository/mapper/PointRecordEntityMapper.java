package com.hackathon.fiap.timesheet.adapter.out.repository.mapper;

import com.hackathon.fiap.timesheet.adapter.out.repository.entity.PointRecordEntity;
import com.hackathon.fiap.timesheet.application.core.domain.PointRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {EmployeeEntityMapper.class})
public interface PointRecordEntityMapper {
    PointRecordEntity toPointRecordEntity(PointRecord pointRecord);
    PointRecord toPointRecord(PointRecordEntity pointRecordEntity);
}
