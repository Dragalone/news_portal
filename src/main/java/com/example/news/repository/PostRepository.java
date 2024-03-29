package com.example.news.repository;

import com.example.news.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {


    Page<Post> findAllByCategoryId(Long categoryId, Pageable pageable);

    Page<Post> findAllByAuthorId(Long userId, Pageable pageable);

    boolean existsByIdAndAuthorId(Long id, Long authorId);

}
