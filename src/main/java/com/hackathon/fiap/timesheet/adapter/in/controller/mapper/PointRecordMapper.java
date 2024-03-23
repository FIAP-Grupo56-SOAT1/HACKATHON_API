package com.hackathon.fiap.timesheet.adapter.in.controller.mapper;

import com.hackathon.fiap.timesheet.adapter.in.controller.response.PointRecordResponse;
import com.hackathon.fiap.timesheet.application.core.domain.PointRecord;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PointRecordMapper {
    PointRecordResponse toPointRecordResponse(PointRecord pointRecord);

    List<PointRecordResponse> toPointRecordResponse(List<PointRecord> pointRecords);
}
