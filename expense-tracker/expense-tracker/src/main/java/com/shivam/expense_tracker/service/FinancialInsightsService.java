package com.shivam.expense_tracker.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shivam.expense_tracker.entity.Budget;
import com.shivam.expense_tracker.entity.Expense;

@Service
public class FinancialInsightsService {

    public String buildInsights(
            Budget budget,
            List<Expense> expenses) {

        StringBuilder summary = new StringBuilder();

        summary.append("\n========= FINANCIAL SUMMARY =========\n");

        if (budget != null) {

            summary.append("Budget Amount : ₹")
                    .append(budget.getBudgetAmount())
                    .append("\n");

            summary.append("Spent Amount : ₹")
                    .append(budget.getSpentAmount())
                    .append("\n");

            summary.append("Remaining Amount : ₹")
                    .append(budget.getRemainingAmount())
                    .append("\n");

            if (budget.getBudgetAmount().compareTo(BigDecimal.ZERO) > 0) {

                BigDecimal percentage =
                        budget.getSpentAmount()
                                .multiply(BigDecimal.valueOf(100))
                                .divide(
                                        budget.getBudgetAmount(),
                                        2,
                                        RoundingMode.HALF_UP);

                summary.append("Budget Used : ")
                        .append(percentage)
                        .append("%\n");
            }
        }

        summary.append("\n");

        summary.append("Total Transactions : ")
                .append(expenses.size())
                .append("\n");

        BigDecimal totalSpent =
                expenses.stream()

                        .map(Expense::getAmount)

                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        summary.append("Calculated Total Spending : ₹")
                .append(totalSpent)
                .append("\n");

        Expense highestExpense =
                expenses.stream()

                        .max(Comparator.comparing(Expense::getAmount))

                        .orElse(null);

        if (highestExpense != null) {

            summary.append("Highest Expense : ₹")
                    .append(highestExpense.getAmount())
                    .append("\n");

            summary.append("Highest Category : ")
                    .append(highestExpense.getCategory().getName())
                    .append("\n");
        }

        if (!expenses.isEmpty()) {

            int currentDay = LocalDate.now().getDayOfMonth();

            BigDecimal average =
                    totalSpent.divide(
                            BigDecimal.valueOf(currentDay),
                            2,
                            RoundingMode.HALF_UP);

            summary.append("Average Daily Spending : ₹")
                    .append(average)
                    .append("\n");
        }

        summary.append("\n====================================\n");

        return summary.toString();
    }
}