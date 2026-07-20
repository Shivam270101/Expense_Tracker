package com.shivam.expense_tracker.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.shivam.expense_tracker.dto.budget.BudgetRequest;
import com.shivam.expense_tracker.dto.budget.BudgetResponse;
import com.shivam.expense_tracker.entity.Budget;
import com.shivam.expense_tracker.entity.User;
import com.shivam.expense_tracker.exception.ResourceNotFoundException;
import com.shivam.expense_tracker.mapper.BudgetMapper;
import com.shivam.expense_tracker.repository.BudgetRepository;
import com.shivam.expense_tracker.repository.UserRepository;
import com.shivam.expense_tracker.service.BudgetService;
import com.shivam.expense_tracker.repository.ExpenseRepository;

@Service
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;
    private final BudgetMapper budgetMapper;
    private final ExpenseRepository expenseRepository;

    public BudgetServiceImpl(BudgetRepository budgetRepository,
                             UserRepository userRepository,
                             BudgetMapper budgetMapper,
                             ExpenseRepository expenseRepository) {

        this.budgetRepository = budgetRepository;
        this.userRepository = userRepository;
        this.budgetMapper = budgetMapper;
        this.expenseRepository = expenseRepository;
    }
 
    @Override
    public BudgetResponse saveBudget(BudgetRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Budget budget = budgetMapper.toEntity(request);

        budget.setUser(user);

        BigDecimal spent = expenseRepository.getTotalExpenseByMonth(
                user.getId(),
                request.getMonth(),
                request.getYear());

        if (spent == null) {
            spent = BigDecimal.ZERO;
        }

        budget.setSpentAmount(spent);

        budget.setRemainingAmount(
                request.getBudgetAmount().subtract(spent));

        Budget savedBudget = budgetRepository.save(budget);

        return budgetMapper.toResponse(savedBudget);
    }

    @Override
    public BudgetResponse updateBudget(Long id, BudgetRequest request) {

        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Budget not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        budget.setMonth(request.getMonth());
        budget.setYear(request.getYear());
        budget.setBudgetAmount(request.getBudgetAmount());
        budget.setUser(user);

        BigDecimal spent = expenseRepository.getTotalExpenseByMonth(
                user.getId(),
                request.getMonth(),
                request.getYear());

        if (spent == null) {
            spent = BigDecimal.ZERO;
        }

        budget.setSpentAmount(spent);

        budget.setRemainingAmount(
                request.getBudgetAmount().subtract(spent));

        Budget updatedBudget = budgetRepository.save(budget);

        return budgetMapper.toResponse(updatedBudget);
    }
    
    @Override
    public BudgetResponse getBudgetById(Long id) {

        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Budget not found"));

        return budgetMapper.toResponse(budget);
    }

    @Override
    public List<BudgetResponse> getAllBudgets(Long userId) {

        return budgetRepository.findByUserId(userId)
                .stream()
                .map(budgetMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBudget(Long id) {

        if (!budgetRepository.existsById(id)) {
            throw new ResourceNotFoundException("Budget not found");
        }

        budgetRepository.deleteById(id);
    }
}