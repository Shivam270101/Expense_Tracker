package com.shivam.expense_tracker.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shivam.expense_tracker.dto.dashboard.CategoryChartResponse;
import com.shivam.expense_tracker.dto.dashboard.DashboardResponse;
import com.shivam.expense_tracker.dto.dashboard.MonthlyTrendResponse;
import com.shivam.expense_tracker.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public ResponseEntity<DashboardResponse> getDashboardSummary() {
        return ResponseEntity.ok(dashboardService.getDashboard());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<DashboardResponse> getDashboard(
            @PathVariable Long userId,
            @RequestParam  Integer month,
            @RequestParam  Integer year) {

        return ResponseEntity.ok(
                dashboardService.getDashboard(userId));
    }

    // Pie Chart
    @GetMapping("/category/{userId}")
    public ResponseEntity<List<CategoryChartResponse>> getCategoryWiseExpense(
            @PathVariable Long userId,
            @RequestParam Integer month,
            @RequestParam Integer year){

        return ResponseEntity.ok(
                dashboardService.getCategoryWiseExpense(userId, month, year));
    }

    // Line Chart
    @GetMapping("/monthly/{userId}")
    public ResponseEntity<List<MonthlyTrendResponse>> getMonthlyTrend(
            @PathVariable Long userId,
            @RequestParam  Integer year) {

        return ResponseEntity.ok(
                dashboardService.getMonthlyTrend(userId, year));
    }

    @GetMapping("/monthly-expense/{userId}")
    public ResponseEntity<BigDecimal> getMonthlyExpense(
            @PathVariable Long userId,
            @RequestParam Integer month,
            @RequestParam Integer year) {

        return ResponseEntity.ok(
                dashboardService.getMonthlyExpense(userId, month, year));
    }
    

}