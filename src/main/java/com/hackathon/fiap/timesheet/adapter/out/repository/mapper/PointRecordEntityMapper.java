package com.hackathon.fiap.timesheet.adapter.out.repository.mapper;

import com.hackathon.fiap.timesheet.adapter.out.repository.entity.PointRecordEntity;
import com.hackathon.fiap.timesheet.application.core.domain.PointRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {EmployeeEntityMapper.class})
public interface PointRecordEntityMapper {
    @Mapping(source = "employeeId", target = "employee.employeeId")
    PointRecordEntity toPointRecordEntity(PointRecord pointRecord);

    @Mapping(source = "employee.employeeId", target = "employeeId")
    PointRecord toPointRecord(PointRecordEntity pointRecordEntity);

    List<PointRecord> toPointRecordList(List<PointRecordEntity> pointRecordEntityList);
}
