package com.hackathon.fiap.timesheet.adapter.in.controller.mapper;

import com.hackathon.fiap.timesheet.adapter.in.controller.response.PointRecordReportDataResponse;
import com.hackathon.fiap.timesheet.application.core.valueobject.PointRecordReportData;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PointRecordReportDataMapper {
    PointRecordReportDataResponse toResponse(PointRecordReportData pointRecordReportData);
}
