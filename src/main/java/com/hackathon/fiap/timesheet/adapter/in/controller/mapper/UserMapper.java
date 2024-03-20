package com.hackathon.fiap.timesheet.adapter.in.controller.mapper;

import com.hackathon.fiap.timesheet.adapter.in.controller.request.UserRequest;
import com.hackathon.fiap.timesheet.adapter.in.controller.response.UserResponse;
import com.hackathon.fiap.timesheet.application.core.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "active", ignore = true)
    User toUser(UserRequest userRequest);

    UserResponse toUserResponse(User user);
}