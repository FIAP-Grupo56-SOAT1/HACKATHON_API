package com.hackathon.fiap.timesheet.adapter.in.controller.mapper;

import com.hackathon.fiap.timesheet.adapter.in.controller.response.DefaultDotMirrorReportResponse;
import com.hackathon.fiap.timesheet.application.core.reports.DefaultDotMirrorReportData;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PointRecordReportDataMapper.class})
public interface DefaultDotMirrorReportMapper {
    DefaultDotMirrorReportResponse toResponse(DefaultDotMirrorReportData report);
}
