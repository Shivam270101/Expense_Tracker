package com.shivam.expense_tracker.service;

import java.math.BigDecimal;
import java.util.List;

import com.shivam.expense_tracker.dto.dashboard.CategoryChartResponse;
import com.shivam.expense_tracker.dto.dashboard.DashboardResponse;
import com.shivam.expense_tracker.dto.dashboard.MonthlyTrendResponse;

public interface DashboardService {

    DashboardResponse getDashboardSummary(Long userId, Integer month, Integer year);

    DashboardResponse getDashboard(Long userId);

    List<CategoryChartResponse> getCategoryWiseExpense(Long userId, Integer month, Integer year);

    List<MonthlyTrendResponse> getMonthlyTrend(Long userId, Integer year);

    BigDecimal getMonthlyExpense(Long userId, Integer month, Integer year);
    
    BigDecimal getTotalExpenseByMonth(Long userId, Integer month, Integer year);
    

	DashboardResponse getDashboard();
   
}