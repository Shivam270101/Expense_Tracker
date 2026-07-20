package com.shivam.expense_tracker.dto.dashboard;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryChartResponse {

    private String categoryName;

    private BigDecimal totalAmount;
}