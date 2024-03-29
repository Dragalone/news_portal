package com.example.news.service;

import com.example.news.model.Comment;
import com.example.news.model.Post;
import com.example.news.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface CommentService {

    boolean existsByIdAndUserId(Long id, Long userId);

    Comment addComment(Comment comment, Long userId, Long postId);

    Comment updateFields(Comment oldEntity, Comment newEntity);

    Page<Comment> findAllByPostId(Long postId, Pageable pageable);

    Comment save(Comment comment);

    Page<Comment> findAll(Pageable pageable);

    Comment update (Long id, Comment comment);

    Comment findById(Long commentId);
    void deleteById(Long id);
}
