package com.hackathon.fiap.timesheet.adapter.out.repository.mapper;

import com.hackathon.fiap.timesheet.adapter.out.repository.entity.EmployeeEntity;
import com.hackathon.fiap.timesheet.application.core.domain.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserEntityMapper.class})
public interface EmployeeEntityMapper {
    @Mapping(source = "employeeId", target = "employeeId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "role", target = "role")
    //@Mapping(source = "userId", target = "user.userId")
    @Mapping(source = "active", target = "active")
    EmployeeEntity toEmployeeEntity(Employee employee);

    @Mapping(source = "employeeId", target = "employeeId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "role", target = "role")
    //@Mapping(source = "user.userId", target = "userId")
    @Mapping(source = "active", target = "active")
    Employee toEmployee(EmployeeEntity employeeEntity);
}
