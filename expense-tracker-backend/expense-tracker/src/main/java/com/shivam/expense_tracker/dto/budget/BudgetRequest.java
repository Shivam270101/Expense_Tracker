package com.shivam.expense_tracker.dto.budget;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BudgetRequest {

    @NotNull(message = "Month is required")
    private Integer month;

    @NotNull(message = "Year is required")
    private Integer year;

    @NotNull(message = "Budget Amount is required")
    private BigDecimal budgetAmount;

    @NotNull(message = "User Id is required")
    private Long userId;
}