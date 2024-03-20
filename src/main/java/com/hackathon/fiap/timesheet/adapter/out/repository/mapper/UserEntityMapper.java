package com.hackathon.fiap.timesheet.adapter.out.repository.mapper;

import com.hackathon.fiap.timesheet.adapter.out.repository.entity.UserEntity;
import com.hackathon.fiap.timesheet.application.core.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {EmployeeEntityMapper.class})
public interface UserEntityMapper {
    UserEntity toUserEntity(User user);
    User toUser(UserEntity userEntity);
}
