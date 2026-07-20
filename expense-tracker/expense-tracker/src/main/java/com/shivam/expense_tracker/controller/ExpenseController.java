package com.shivam.expense_tracker.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.shivam.expense_tracker.dto.expense.ExpenseRequest;
import com.shivam.expense_tracker.dto.expense.ExpenseResponse;
import com.shivam.expense_tracker.service.ExpenseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/expenses")
@Validated
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    // Create Expense
    @PostMapping
    public ResponseEntity<ExpenseResponse> saveExpense(
            @Valid @RequestBody ExpenseRequest request) {

        ExpenseResponse response = expenseService.saveExpense(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    } 

    // Update Expense
    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponse> updateExpense(
            @PathVariable Long id,
            @Valid @RequestBody ExpenseRequest request) {

        ExpenseResponse response = expenseService.updateExpense(id, request);

        return ResponseEntity.ok(response);
    }

    // Get Expense By Id
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponse> getExpenseById(
            @PathVariable Long id) {

        ExpenseResponse response = expenseService.getExpenseById(id);

        return ResponseEntity.ok(response);
    }

    // Get All Expenses
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ExpenseResponse>> getAllExpenses(@PathVariable Long userId) {

        List<ExpenseResponse> expenses = expenseService.getAllExpenses(userId);

        return ResponseEntity.ok(expenses);
    }

    // Delete Expense
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(
            @PathVariable Long id) {

        expenseService.deleteExpense(id);

        return ResponseEntity.ok("Expense deleted successfully.");
    }

}