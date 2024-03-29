package com.example.news.mapper;

import com.example.news.model.Post;
import com.example.news.web.model.request.UpsertPostRequest;
import com.example.news.web.model.response.PostListResponse;
import com.example.news.web.model.response.PostResponse;
import org.mapstruct.DecoratedWith;
import org.mapstruct.*;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@DecoratedWith(PostMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    Post upsertRequestToPost(UpsertPostRequest request);
    @Mapping(source = "author.username", target = "username")
    PostListResponse postToListResponse(Post post);
    @Mapping(source = "author.username", target = "username")
    PostResponse postToResponse(Post post);

}


