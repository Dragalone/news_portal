package com.example.news.service;

import com.example.news.model.Post;
import com.example.news.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User updateFields(User oldEntity, User newEntity);

    User findByUsername(String username);

    User findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User findById(Long userId);

    User save(User entity);

    Page<User> findAll(Pageable pageable);
    User update (Long id, User user);

    void deleteById(Long id);
}
