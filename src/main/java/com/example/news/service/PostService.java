package com.example.news.service;

import com.example.news.model.Post;
import com.example.news.model.User;
import com.example.news.web.model.request.PostFilterRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    Post addPost(Post post, Long userId, Long categoryId);

    Post updatePost(Post post, Long id, Long categoryId);

    Page<Post> findAllByCategoryId(Long categoryId, Pageable pageable);

    Page<Post> findAllByAuthorId(Long userId, Pageable pageable);

    Post updateFields(Post oldEntity, Post newEntity);

    Page<Post> filterBy(PostFilterRequest filter);

    boolean existsByIdAndAuthorId(Long id, Long authorId);

    Post findById(Long postId);

    Post save (Post post);

    Post update (Long id, Post post);
    void deleteById(Long id);

    Page<Post> findAll(Pageable pageable);


}
