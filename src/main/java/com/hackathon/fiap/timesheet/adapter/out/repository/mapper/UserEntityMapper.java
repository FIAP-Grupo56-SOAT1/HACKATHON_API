package com.hackathon.fiap.timesheet.adapter.out.repository.mapper;

import com.hackathon.fiap.timesheet.adapter.out.repository.entity.UserEntity;
import com.hackathon.fiap.timesheet.application.core.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {EmployeeEntityMapper.class})
public interface UserEntityMapper {
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "employeeId", target = "employee.employeeId")
    @Mapping(source = "active", target = "active")
    UserEntity toUserEntity(User user);

    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "employee.employeeId", target = "employeeId")
    @Mapping(source = "active", target = "active")
    User toUser(UserEntity userEntity);
}
