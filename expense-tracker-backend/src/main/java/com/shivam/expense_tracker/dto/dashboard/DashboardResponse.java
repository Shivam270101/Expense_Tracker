package com.shivam.expense_tracker.dto.dashboard;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardResponse {

    private BigDecimal totalExpense;

    private BigDecimal totalBudget;
    
    private BigDecimal totalSpent;

    private BigDecimal remainingBudget;

    private Long totalCategories;

    private Long totalExpenses;

    private Long totalUsers;
}