package com.shivam.expense_tracker.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shivam.expense_tracker.dto.dashboard.CategoryChartResponse;
import com.shivam.expense_tracker.dto.dashboard.DashboardResponse;
import com.shivam.expense_tracker.dto.dashboard.MonthlyTrendResponse;
import com.shivam.expense_tracker.repository.BudgetRepository;
import com.shivam.expense_tracker.repository.CategoryRepository;
import com.shivam.expense_tracker.repository.ExpenseRepository;
import com.shivam.expense_tracker.repository.UserRepository;
import com.shivam.expense_tracker.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final BudgetRepository budgetRepository;
    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public DashboardServiceImpl(
            BudgetRepository budgetRepository,
            ExpenseRepository expenseRepository,
            CategoryRepository categoryRepository,
            UserRepository userRepository) {

        this.budgetRepository = budgetRepository;
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public DashboardResponse getDashboard() {

        BigDecimal totalBudget = budgetRepository.findAll()
                .stream()
                .map(b -> b.getBudgetAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalSpent = budgetRepository.findAll()
                .stream()
                .map(b -> b.getSpentAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal remaining = budgetRepository.findAll()
                .stream()
                .map(b -> b.getRemainingAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return DashboardResponse.builder()
                .totalBudget(totalBudget)
                .totalSpent(totalSpent)
                .remainingBudget(remaining)
                .totalExpenses(expenseRepository.count())
                .totalCategories(categoryRepository.count())
                .totalUsers(userRepository.count())
                .build();
    }

    @Override
    public DashboardResponse getDashboard(Long userId) {

        DashboardResponse response = new DashboardResponse();

        BigDecimal totalExpense = expenseRepository.getTotalExpense(userId);

        if (totalExpense == null)
            totalExpense = BigDecimal.ZERO;

        BigDecimal totalBudget = budgetRepository.getTotalBudget(userId);

        if (totalBudget == null)
            totalBudget = BigDecimal.ZERO;

        response.setTotalBudget(totalBudget);
        response.setTotalSpent(totalExpense);
        response.setRemainingBudget(totalBudget.subtract(totalExpense));

        response.setTotalCategories(categoryRepository.countByUserId(userId));
        response.setTotalExpenses(expenseRepository.countByUserId(userId));
        response.setTotalUsers(userRepository.count());

        return response;
    }

    @Override
    public List<CategoryChartResponse> getCategoryWiseExpense(Long userId, Integer month, Integer year) {
        return expenseRepository.getCategoryWiseExpense(userId, month, year);
    }

    @Override
    public List<MonthlyTrendResponse> getMonthlyTrend(Long userId, Integer year) {
        return expenseRepository.getMonthlyTrend(userId, year);
    }


	@Override
	public DashboardResponse getDashboardSummary(Long userId, Integer month, Integer year) {

		    DashboardResponse response = new DashboardResponse();

		    // Total expense for selected month
		    BigDecimal totalExpense = expenseRepository.getTotalExpenseByMonth(userId, month, year);

		    if (totalExpense == null) {
		        totalExpense = BigDecimal.ZERO;
		    }

		    // Total budget
		    BigDecimal totalBudget = budgetRepository.getTotalBudget(userId);

		    if (totalBudget == null) {
		        totalBudget = BigDecimal.ZERO;
		    }

		    response.setTotalBudget(totalBudget);
		    response.setTotalSpent(totalExpense);
		    response.setRemainingBudget(totalBudget.subtract(totalExpense));

		    response.setTotalExpenses(expenseRepository.countByUserId(userId));
		    response.setTotalCategories(categoryRepository.countByUserId(userId));
		    response.setTotalUsers(userRepository.count());

		    return response;
	}

	@Override
	public BigDecimal getMonthlyExpense(Long userId, Integer month, Integer year) {
		
		return expenseRepository.getMonthlyExpense(userId, month, year); 
	}

	@Override
	public BigDecimal getTotalExpenseByMonth(Long userId, Integer month, Integer year) {
		
		return expenseRepository.getTotalExpenseByMonth(userId, month, year);
	}



}