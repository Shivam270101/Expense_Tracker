package com.shivam.expense_tracker.dto.budget;


import java.math.BigDecimal;

import lombok.Data;

@Data
public class BudgetSummaryResponse {

    private BigDecimal totalBudget;

    private BigDecimal totalSpent;

    private BigDecimal totalRemaining;
}