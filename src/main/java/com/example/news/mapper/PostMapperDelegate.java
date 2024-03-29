package com.example.news.mapper;

import com.example.news.model.Post;
import com.example.news.web.model.response.PostListResponse;
import com.example.news.web.model.response.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;



public abstract class PostMapperDelegate implements PostMapper {

    @Autowired
    private PostMapper delegate;
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public PostListResponse postToListResponse(Post post) {
        PostListResponse response = delegate.postToListResponse(post);
        response.setUsername(post.getAuthor().getUsername());
        response.setCommentsCount(post.getComments().size());

        return response;
    }
    @Override
    public PostResponse postToResponse(Post post) {
        PostResponse response = delegate.postToResponse(post);
        response.setUsername(post.getAuthor().getUsername());
        response.setCommentsCount(post.getComments().size());
        response.setComments(post.getComments().stream()
                .map(it -> commentMapper.commentToResponse(it))
                .toList());

        return response;
    }
}
