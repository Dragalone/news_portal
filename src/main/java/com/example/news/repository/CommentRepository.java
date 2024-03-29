package com.example.news.repository;

import com.example.news.model.Category;
import com.example.news.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Comment> findAllByPostId (Long id, Pageable pageable);

    boolean existsByIdAndUserId(Long id, Long userId);
}
