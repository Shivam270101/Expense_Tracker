package com.shivam.expense_tracker.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.shivam.expense_tracker.dto.expense.ExpenseRequest;
import com.shivam.expense_tracker.dto.expense.ExpenseResponse;
import com.shivam.expense_tracker.entity.Budget;
import com.shivam.expense_tracker.entity.Category;
import com.shivam.expense_tracker.entity.Expense;
import com.shivam.expense_tracker.entity.User;
import com.shivam.expense_tracker.exception.ResourceNotFoundException;
import com.shivam.expense_tracker.mapper.ExpenseMapper;
import com.shivam.expense_tracker.repository.BudgetRepository;
import com.shivam.expense_tracker.repository.CategoryRepository;
import com.shivam.expense_tracker.repository.ExpenseRepository;
import com.shivam.expense_tracker.repository.UserRepository;
import com.shivam.expense_tracker.service.ExpenseService;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ExpenseMapper expenseMapper;
    private final BudgetRepository budgetRepository;

    public ExpenseServiceImpl(
            ExpenseRepository expenseRepository,
            CategoryRepository categoryRepository,
            UserRepository userRepository,
            ExpenseMapper expenseMapper,
            BudgetRepository budgetRepository) {

        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.expenseMapper = expenseMapper;
        this.budgetRepository = budgetRepository;
    }

    @Override
    public ExpenseResponse saveExpense(ExpenseRequest request) {

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Expense expense = expenseMapper.toEntity(request);

        expense.setCategory(category);
        expense.setUser(user);

        Expense savedExpense = expenseRepository.save(expense);

        Integer month = savedExpense.getExpenseDate().getMonthValue();
        Integer year = savedExpense.getExpenseDate().getYear();

        //to update budget
        Budget budget = budgetRepository.findByUserAndMonthAndYear(user, month, year)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Budget not found for selected month and year"));
        
        BigDecimal spent = budget.getSpentAmount() == null
                ? BigDecimal.ZERO
                : budget.getSpentAmount();

        spent = spent.add(savedExpense.getAmount());

        budget.setSpentAmount(spent);

        budget.setRemainingAmount(
                budget.getBudgetAmount().subtract(spent));

        budgetRepository.save(budget);
        
        
        
        
        
        
        
        return expenseMapper.toResponse(savedExpense);
    }

    @Override
    public ExpenseResponse updateExpense(Long id, ExpenseRequest request) {

        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Expense not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));
        
     // Find budget for old expense
        Integer oldMonth = existingExpense.getExpenseDate().getMonthValue();
        Integer oldYear = existingExpense.getExpenseDate().getYear();

        Budget budget = budgetRepository
                .findByUserAndMonthAndYear(user, oldMonth, oldYear)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Budget not found"));

        BigDecimal spent = budget.getSpentAmount();

        // Remove old amount
        spent = spent.subtract(existingExpense.getAmount());


        existingExpense.setTitle(request.getTitle());
        existingExpense.setAmount(request.getAmount());
        existingExpense.setDescription(request.getDescription());
        existingExpense.setExpenseDate(request.getExpenseDate());
        existingExpense.setPaymentMode(request.getPaymentMode());
        existingExpense.setCategory(category);
        existingExpense.setUser(user);
        
        // Add new amount
        spent = spent.add(existingExpense.getAmount());

        budget.setSpentAmount(spent);
        budget.setRemainingAmount(
                budget.getBudgetAmount().subtract(spent));

        budgetRepository.save(budget);

        Expense updatedExpense = expenseRepository.save(existingExpense);

        return expenseMapper.toResponse(updatedExpense);
    }

    @Override
    public ExpenseResponse getExpenseById(Long id) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Expense not found"));

        return expenseMapper.toResponse(expense);
    }

    @Override
    public List<ExpenseResponse> getAllExpenses(Long userId) {

    	return expenseRepository.findByUserId(userId)
    	        .stream()
    	        .map(expenseMapper::toResponse)
    	        .collect(Collectors.toList());
    }

    @Override
    public void deleteExpense(Long id) {

    	Expense expense = expenseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Expense not found"));

        Integer month = expense.getExpenseDate().getMonthValue();
        Integer year = expense.getExpenseDate().getYear();

        Budget budget = budgetRepository
                .findByUserAndMonthAndYear(
                        expense.getUser(),
                        month,
                        year)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Budget not found"));

        BigDecimal spent = budget.getSpentAmount();

        spent = spent.subtract(expense.getAmount());

        budget.setSpentAmount(spent);

        budget.setRemainingAmount(
                budget.getBudgetAmount().subtract(spent));

        budgetRepository.save(budget);

        expenseRepository.delete(expense);
    }
}