package com.hackathon.fiap.timesheet.adapter.in.controller.mapper;

import com.hackathon.fiap.timesheet.adapter.in.controller.request.EmployeeRequest;
import com.hackathon.fiap.timesheet.adapter.in.controller.response.EmployeeResponse;
import com.hackathon.fiap.timesheet.application.core.domain.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeResponse toEmployeeResponse(Employee employee);

    @Mapping(target = "employeeId", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "active", ignore = true)
    Employee toEmployee(EmployeeRequest employeeRequest);

    List<EmployeeResponse> toEmployeesResponse(List<Employee> employees);
}
