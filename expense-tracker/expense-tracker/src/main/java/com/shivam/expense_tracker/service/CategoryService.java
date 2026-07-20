package com.shivam.expense_tracker.service;

import java.util.List;

import com.shivam.expense_tracker.dto.category.CategoryRequest;
import com.shivam.expense_tracker.dto.category.CategoryResponse;

public interface CategoryService {

    CategoryResponse saveCategory(CategoryRequest request);

    CategoryResponse updateCategory(Long id, CategoryRequest request);

    CategoryResponse getCategoryById(Long id);

    List<CategoryResponse> getAllCategories(Long userId);
    
    List<CategoryResponse> getAllCategories();

    void deleteCategory(Long id);
}
