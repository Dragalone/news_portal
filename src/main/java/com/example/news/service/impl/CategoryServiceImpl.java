package com.example.news.service.impl;

import com.example.news.exception.EntityNotFoundException;
import com.example.news.model.Category;
import com.example.news.repository.CategoryRepository;

import com.example.news.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("Category with name {0} not found!", name)
                ));
    }

    @Override
    public Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("Category with ID {0} not found!", categoryId)
                ));
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category update(Long id, Category category) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category save(Category сategory) {
        return categoryRepository.save(сategory);
    }


}
