package com.shivam.expense_tracker.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryRequest {

    @NotBlank(message = "Category name is required")
    private String name;

    private String color;

    private String icon;

    private String description;

    @NotNull(message = "User Id is required")
    private Long userId;
}