package com.shivam.expense_tracker.mapper;

import org.springframework.stereotype.Component;

import com.shivam.expense_tracker.dto.budget.BudgetRequest;
import com.shivam.expense_tracker.dto.budget.BudgetResponse;
import com.shivam.expense_tracker.entity.Budget;

@Component
public class BudgetMapper {

    /**
     * Convert BudgetRequest DTO to Budget Entity
     */
    public Budget toEntity(BudgetRequest request) {

        Budget budget = new Budget();

        budget.setMonth(request.getMonth());
        budget.setYear(request.getYear());
        budget.setBudgetAmount(request.getBudgetAmount());

        return budget;
    }

    /**
     * Convert Budget Entity to BudgetResponse DTO
     */
    public BudgetResponse toResponse(Budget budget) {

        BudgetResponse response = new BudgetResponse();

        response.setId(budget.getId());
        response.setMonth(budget.getMonth());
        response.setYear(budget.getYear());
        response.setBudgetAmount(budget.getBudgetAmount());
        response.setSpentAmount(budget.getSpentAmount());
        response.setRemainingAmount(budget.getRemainingAmount());

        return response; 
    }

}