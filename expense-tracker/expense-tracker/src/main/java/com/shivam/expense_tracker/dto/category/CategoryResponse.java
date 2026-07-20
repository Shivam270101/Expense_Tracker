package com.shivam.expense_tracker.dto.category;

import lombok.Data;

@Data
public class CategoryResponse {

    private Long id;

    private String name;

    private String color;

    private String icon;

    private String description;

    private Long userId;
}