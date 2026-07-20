package com.shivam.expense_tracker.prompt;

import java.util.List;

import com.shivam.expense_tracker.entity.Budget;
import com.shivam.expense_tracker.entity.Category;
import com.shivam.expense_tracker.entity.Expense;
import com.shivam.expense_tracker.entity.User;

public class PromptBuilder {

    public String buildPrompt(
            User user,
            List<Expense> expenses,
            List<Category> categories,
            List<Budget> budgets,
            String question) {

        StringBuilder prompt = new StringBuilder();

        prompt.append("You are an AI Financial Assistant.\n\n");

        prompt.append("User Name: ")
              .append(user.getFullName())
              .append("\n\n");

        prompt.append("Expenses:\n");

        for (Expense expense : expenses) {
            prompt.append("- ")
                  .append(expense.getTitle())
                  .append(" | ₹")
                  .append(expense.getAmount())
                  .append(" | ")
                  .append(expense.getExpenseDate())
                  .append("\n");
        }

        prompt.append("\nCategories:\n");

        for (Category category : categories) {
            prompt.append("- ")
                  .append(category.getName())
                  .append("\n");
        }

        prompt.append("\nBudgets:\n");

        for (Budget budget : budgets) {
            prompt.append("- ")
                  .append(budget.getMonth())
                  .append("/")
                  .append(budget.getYear())
                  .append(" : ₹")
                  .append(budget.getBudgetAmount())
                  .append("\n");
        }

        prompt.append("\nUser Question:\n")
              .append(question);

        return prompt.toString();
    }
}
