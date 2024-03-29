package com.example.news.service;

import com.example.news.model.Category;
import com.example.news.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    Category findByName(String name);

    Category findById(Long categoryId);

    Page<Category> findAll(Pageable pageable);
    Category update (Long id, Category category);

    void deleteById(Long id);

    Category save(Category сategory);
}
