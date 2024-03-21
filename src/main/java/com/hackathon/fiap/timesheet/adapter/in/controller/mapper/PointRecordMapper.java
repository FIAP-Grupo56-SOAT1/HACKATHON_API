package com.hackathon.fiap.timesheet.adapter.in.controller.mapper;

import com.hackathon.fiap.timesheet.adapter.in.controller.request.PointRecordRequest;
import com.hackathon.fiap.timesheet.adapter.in.controller.response.PointRecordResponse;
import com.hackathon.fiap.timesheet.application.core.domain.PointRecord;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PointRecordMapper {
    PointRecordResponse toPointRecordResponse(PointRecord pointRecord);

    PointRecord toPointRecord(PointRecordRequest pointRecordRequest);

    List<PointRecordResponse> toPointRecordResponse(List<PointRecord> pointRecords);
}
