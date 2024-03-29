package com.example.news.web.controller;

import com.example.news.mapper.CategoryMapper;
import com.example.news.model.Category;
import com.example.news.service.CategoryService;
import com.example.news.web.model.request.PaginationRequest;
import com.example.news.web.model.request.UpsertCategoryRequest;
import com.example.news.web.model.response.CategoryResponse;
import com.example.news.web.model.response.ModelListResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<ModelListResponse<CategoryResponse>> findAllCategories(@Valid PaginationRequest request) {
        Page<Category> categories = categoryService.findAll(request.pageRequest());

        return ResponseEntity.ok(
                ModelListResponse.<CategoryResponse>builder()
                        .totalCount(categories.getTotalElements())
                        .data(categories.stream().map(categoryMapper::categoryToResponse).toList())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryMapper.categoryToResponse(categoryService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody UpsertCategoryRequest request) {
        Category newCategory = categoryService.save(categoryMapper.upsertRequestToCategory(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryMapper.categoryToResponse(newCategory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@RequestBody UpsertCategoryRequest request,
                                                           @PathVariable Long id) {
        Category updatedCategory = categoryService.update(id, categoryMapper.upsertRequestToCategory(request));

        return ResponseEntity.ok(categoryMapper.categoryToResponse(updatedCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
