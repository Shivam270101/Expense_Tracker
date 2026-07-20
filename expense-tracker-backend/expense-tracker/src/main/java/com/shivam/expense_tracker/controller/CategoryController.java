package com.shivam.expense_tracker.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.shivam.expense_tracker.dto.category.CategoryRequest;
import com.shivam.expense_tracker.dto.category.CategoryResponse;
import com.shivam.expense_tracker.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
@Validated
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
 
    // Create Category
    @PostMapping
    public ResponseEntity<CategoryResponse> saveCategory(
            @Valid @RequestBody CategoryRequest request) {

        CategoryResponse response = categoryService.saveCategory(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Update Category
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequest request) {

        CategoryResponse response = categoryService.updateCategory(id, request);

        return ResponseEntity.ok(response);
    }

    // Get Category By Id
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(
            @PathVariable Long id) {

        CategoryResponse response = categoryService.getCategoryById(id);

        return ResponseEntity.ok(response);
    }

    // Get All Categories    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CategoryResponse>> getAllCategories(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                categoryService.getAllCategories(userId));
    }

    // Delete Category
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(
            @PathVariable Long id) {

        categoryService.deleteCategory(id);

        return ResponseEntity.ok("Category deleted successfully.");
    }

}