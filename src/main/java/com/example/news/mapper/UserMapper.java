package com.example.news.mapper;

import com.example.news.model.User;
import com.example.news.web.model.request.UpsertUserRequest;
import com.example.news.web.model.response.UserResponse;
import org.mapstruct.*;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User upsertRequestToUser(UpsertUserRequest request);

    UserResponse userToResponse(User user);

}

