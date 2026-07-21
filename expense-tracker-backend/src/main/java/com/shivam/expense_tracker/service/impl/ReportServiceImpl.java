package com.shivam.expense_tracker.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import com.shivam.expense_tracker.dto.report.ReportExpenseDTO;
import com.shivam.expense_tracker.dto.report.ReportRequest;
import com.shivam.expense_tracker.dto.report.ReportSummaryResponse;
import com.shivam.expense_tracker.entity.Budget;
import com.shivam.expense_tracker.entity.Expense;
import com.shivam.expense_tracker.entity.User;
import com.shivam.expense_tracker.exception.ResourceNotFoundException;
import com.shivam.expense_tracker.repository.BudgetRepository;
import com.shivam.expense_tracker.repository.ExpenseRepository;
import com.shivam.expense_tracker.repository.UserRepository;
import com.shivam.expense_tracker.service.ReportService;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {
	
	private final UserRepository userRepository;
	
	private final ExpenseRepository expenseRepository;
	
	private final BudgetRepository budgetRepository;
	
	public ReportServiceImpl(
			UserRepository userRepository,
			ExpenseRepository expenseRepository,
			BudgetRepository budgetRepository) {
		this.userRepository = userRepository;
		this.expenseRepository = expenseRepository;
		this.budgetRepository = budgetRepository;

	}
	@Override
	public ReportSummaryResponse generateReport(
			String email, 
			ReportRequest request) {
		
		 User user = userRepository.findByEmail(email)
		            .orElseThrow(() ->
		                    new ResourceNotFoundException("User not found"));

		    List<Expense> expenses;

		    boolean hasCategory =
		            request.getCategoryId() != null;

		    boolean hasPaymentMode =
		            request.getPaymentMode() != null;

		    if (hasCategory && hasPaymentMode) {

		        expenses = expenseRepository
		                .findByUserAndCategory_IdAndPaymentModeAndExpenseDateBetween(
		                        user,
		                        request.getCategoryId(),
		                        request.getPaymentMode(),
		                        request.getFromDate(),
		                        request.getToDate());

		    } else if (hasCategory) {

		        expenses = expenseRepository
		                .findByUserAndCategory_IdAndExpenseDateBetween(
		                        user,
		                        request.getCategoryId(),
		                        request.getFromDate(),
		                        request.getToDate());

		    } else if (hasPaymentMode) {

		        expenses = expenseRepository
		                .findByUserAndPaymentModeAndExpenseDateBetween(
		                        user,
		                        request.getPaymentMode(),
		                        request.getFromDate(),
		                        request.getToDate());

		    } else {

		        expenses = expenseRepository
		                .findByUserAndExpenseDateBetween(
		                        user,
		                        request.getFromDate(),
		                        request.getToDate());

		    }
		    
		 // ===============================
		 // Total Expense
		 // ===============================

		 BigDecimal totalExpense = expenses.stream()
		         .map(Expense::getAmount)
		         .reduce(BigDecimal.ZERO, BigDecimal::add);

		 // ===============================
		 // Total Budget
		 // ===============================

		 LocalDate fromDate = request.getFromDate();
		 LocalDate toDate = request.getToDate();

		 BigDecimal totalBudget = BigDecimal.ZERO;

		 List<Budget> budgets = budgetRepository.findByUser(user);

		 for (Budget budget : budgets) {

		     LocalDate budgetDate = LocalDate.of(
		             budget.getYear(),
		             budget.getMonth(),
		             1);

		     if ((budgetDate.isEqual(fromDate) || budgetDate.isAfter(fromDate))
		             && (budgetDate.isEqual(toDate) || budgetDate.isBefore(toDate))) {

		         totalBudget = totalBudget.add(budget.getBudgetAmount());
		     }
		 }

		 // ===============================
		 // Remaining Budget
		 // ===============================

		 BigDecimal remainingBudget = totalBudget.subtract(totalExpense);

		 if (remainingBudget.compareTo(BigDecimal.ZERO) < 0) {
		     remainingBudget = BigDecimal.ZERO;
		 }

		 // ===============================
		 // Total Transactions
		 // ===============================

		 Integer totalTransactions = expenses.size();

		 // ===============================
		 // Highest Expense Category
		 // ===============================

		 Map<String, BigDecimal> categoryTotals = new HashMap<>();

		 for (Expense expense : expenses) {

		     String category = expense.getCategory().getName();

		     categoryTotals.put(
		             category,
		             categoryTotals.getOrDefault(category, BigDecimal.ZERO)
		                     .add(expense.getAmount()));
		 }

		 String highestCategory = "N/A";
		 BigDecimal highestAmount = BigDecimal.ZERO;

		 if (!categoryTotals.isEmpty()) {

		     Map.Entry<String, BigDecimal> entry = categoryTotals.entrySet()
		             .stream()
		             .max(Map.Entry.comparingByValue())
		             .get();

		     highestCategory = entry.getKey();
		     highestAmount = entry.getValue();
		 }

		 // ===============================
		 // Expense DTO Mapping
		 // ===============================

		 List<ReportExpenseDTO> reportExpenses = expenses.stream()
		         .map(expense -> ReportExpenseDTO.builder()
		                 .expenseId(expense.getId())
		                 .expenseDate(expense.getExpenseDate())
		                 .title(expense.getTitle())
		                 .description(expense.getDescription())
		                 .categoryName(expense.getCategory().getName())
		                 .paymentMode(expense.getPaymentMode())
		                 .amount(expense.getAmount())
		                 .build())
		         .collect(Collectors.toList());

		 // ===============================
		 // Return Response
		 // ===============================

		 return ReportSummaryResponse.builder()
		         .totalBudget(totalBudget)
		         .totalExpense(totalExpense)
		         .remainingBudget(remainingBudget)
		         .totalTransactions(totalTransactions)
		         .highestExpenseCategory(highestCategory)
		         .highestCategoryAmount(highestAmount)
		         .expenses(reportExpenses)
		         .build();
		
	}

}
