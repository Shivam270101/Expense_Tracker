package com.shivam.expense_tracker.dto.budget;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class BudgetResponse {

    private Long id;

    private Integer month;

    private Integer year;

    private BigDecimal budgetAmount;

    private BigDecimal spentAmount;

    private BigDecimal remainingAmount;
}