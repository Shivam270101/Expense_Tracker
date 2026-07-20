package com.shivam.expense_tracker.service;


import java.util.List;

import com.shivam.expense_tracker.dto.budget.BudgetRequest;
import com.shivam.expense_tracker.dto.budget.BudgetResponse;

public interface BudgetService {

    BudgetResponse saveBudget(BudgetRequest request);

    BudgetResponse updateBudget(Long id, BudgetRequest request);

    BudgetResponse getBudgetById(Long id);

    List<BudgetResponse> getAllBudgets(Long userId);

    void deleteBudget(Long id);
}
