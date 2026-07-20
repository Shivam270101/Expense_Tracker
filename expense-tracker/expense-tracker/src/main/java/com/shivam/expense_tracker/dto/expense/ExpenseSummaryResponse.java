package com.shivam.expense_tracker.dto.expense;


import java.math.BigDecimal;

import lombok.Data;

@Data
public class ExpenseSummaryResponse {

    private BigDecimal totalExpense;

    private BigDecimal highestExpense;

    private BigDecimal averageExpense;

    private Integer totalTransactions;
}