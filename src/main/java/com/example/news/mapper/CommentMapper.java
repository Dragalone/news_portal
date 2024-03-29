package com.example.news.mapper;

import com.example.news.model.Comment;
import com.example.news.web.model.request.UpsertCommentRequest;
import com.example.news.web.model.response.CommentResponse;
import org.mapstruct.*;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

    Comment upsertRequestToComment(UpsertCommentRequest request);

    @Mapping(source = "user.username", target = "username")
    CommentResponse commentToResponse(Comment comment);

}

