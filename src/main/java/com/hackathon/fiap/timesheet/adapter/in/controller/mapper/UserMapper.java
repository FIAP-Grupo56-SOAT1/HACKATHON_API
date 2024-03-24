package com.hackathon.fiap.timesheet.adapter.in.controller.mapper;

import com.hackathon.fiap.timesheet.adapter.in.controller.response.UserResponse;
import com.hackathon.fiap.timesheet.application.core.domain.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserResponse(User user);

    List<UserResponse> toUserResponseList(List<User> users);
}