package com.hackathon.fiap.timesheet.adapter.out.repository.mapper;

import com.hackathon.fiap.timesheet.adapter.out.repository.entity.EmployeeEntity;
import com.hackathon.fiap.timesheet.application.core.domain.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserEntityMapper.class})
public interface EmployeeEntityMapper {
    EmployeeEntity toEmployeeEntity(Employee employee);

    Employee toEmployee(EmployeeEntity employeeEntity);
}
