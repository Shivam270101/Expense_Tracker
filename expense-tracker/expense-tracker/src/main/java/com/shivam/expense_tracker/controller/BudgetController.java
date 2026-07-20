package com.shivam.expense_tracker.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.shivam.expense_tracker.dto.budget.BudgetRequest;
import com.shivam.expense_tracker.dto.budget.BudgetResponse;
import com.shivam.expense_tracker.service.BudgetService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/budgets")
@Validated
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    // Create Budget
    @PostMapping
    public ResponseEntity<BudgetResponse> saveBudget(
            @Valid @RequestBody BudgetRequest request) {

        BudgetResponse response = budgetService.saveBudget(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Update Budget
    @PutMapping("/{id}")
    public ResponseEntity<BudgetResponse> updateBudget(
            @PathVariable Long id,
            @Valid @RequestBody BudgetRequest request) {

        BudgetResponse response = budgetService.updateBudget(id, request);

        return ResponseEntity.ok(response);
    }

    // Get Budget By Id
    @GetMapping("/{id}")
    public ResponseEntity<BudgetResponse> getBudgetById(
            @PathVariable Long id) {

        BudgetResponse response = budgetService.getBudgetById(id);

        return ResponseEntity.ok(response);
    }

    // Get All Budgets
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BudgetResponse>> getAllBudgets(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                budgetService.getAllBudgets(userId));
    }

    // Delete Budget
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBudget(
            @PathVariable Long id) {

        budgetService.deleteBudget(id);

        return ResponseEntity.ok("Budget deleted successfully.");
    }

}