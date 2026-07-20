package com.shivam.expense_tracker.mapper;

import org.springframework.stereotype.Component;

import com.shivam.expense_tracker.dto.category.CategoryRequest;
import com.shivam.expense_tracker.dto.category.CategoryResponse;
import com.shivam.expense_tracker.entity.Category;

@Component
public class CategoryMapper {

    public Category toEntity(CategoryRequest request) {

        Category category = new Category();

        category.setName(request.getName());
        category.setColor(request.getColor());
        category.setIcon(request.getIcon());
        category.setDescription(request.getDescription());

        return category;
    }

    public CategoryResponse toResponse(Category category) {

        CategoryResponse response = new CategoryResponse();

        response.setId(category.getId());
        response.setName(category.getName());
        response.setColor(category.getColor());
        response.setIcon(category.getIcon());
        response.setDescription(category.getDescription());

        if (category.getUser() != null) {
            response.setUserId(category.getUser().getId());
        }

        return response;
    }
}