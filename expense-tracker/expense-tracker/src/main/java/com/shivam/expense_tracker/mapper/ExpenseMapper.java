package com.shivam.expense_tracker.mapper;

import org.springframework.stereotype.Component;

import com.shivam.expense_tracker.dto.expense.ExpenseRequest;
import com.shivam.expense_tracker.dto.expense.ExpenseResponse;
import com.shivam.expense_tracker.entity.Expense;

@Component
public class ExpenseMapper {

    public Expense toEntity(ExpenseRequest request) {

        Expense expense = new Expense();

        expense.setTitle(request.getTitle());
        expense.setAmount(request.getAmount());
        expense.setDescription(request.getDescription());
        expense.setExpenseDate(request.getExpenseDate());
        expense.setPaymentMode(request.getPaymentMode());

        return expense;
    }

    public ExpenseResponse toResponse(Expense expense) {

        ExpenseResponse response = new ExpenseResponse();

        response.setId(expense.getId());
        response.setTitle(expense.getTitle());
        response.setAmount(expense.getAmount());
        response.setDescription(expense.getDescription());
        response.setExpenseDate(expense.getExpenseDate());
        response.setPaymentMode(expense.getPaymentMode());

        if(expense.getCategory()!=null) {
            response.setCategoryName(
                    expense.getCategory().getName());
        }

        if(expense.getUser()!=null) {
            response.setUserName(
                    expense.getUser().getFullName());
        }

        return response;
    }

}
