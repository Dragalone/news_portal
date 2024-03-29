package com.example.news.mapper;

import com.example.news.model.Category;
import com.example.news.web.model.request.UpsertCategoryRequest;
import com.example.news.web.model.response.CategoryResponse;
import org.mapstruct.*;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    Category upsertRequestToCategory(UpsertCategoryRequest request);

    CategoryResponse categoryToResponse(Category category);

}

