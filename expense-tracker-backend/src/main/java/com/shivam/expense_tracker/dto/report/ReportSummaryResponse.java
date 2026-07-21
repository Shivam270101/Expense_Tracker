package com.shivam.expense_tracker.dto.report;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportSummaryResponse {

    private BigDecimal totalBudget;

    private BigDecimal totalExpense;

    private BigDecimal remainingBudget;

    private Integer totalTransactions;

    private String highestExpenseCategory;

    private BigDecimal highestCategoryAmount;

    private List<ReportExpenseDTO> expenses;

}