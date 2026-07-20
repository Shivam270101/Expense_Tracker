package com.shivam.expense_tracker.service;

import java.util.List;

import com.shivam.expense_tracker.dto.expense.ExpenseRequest;
import com.shivam.expense_tracker.dto.expense.ExpenseResponse;


public interface ExpenseService {

    ExpenseResponse saveExpense(ExpenseRequest request);

    ExpenseResponse updateExpense(Long id, ExpenseRequest request);

    ExpenseResponse getExpenseById(Long id);

    List<ExpenseResponse> getAllExpenses(Long userId);

    void deleteExpense(Long id);

}