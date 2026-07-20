package com.shivam.expense_tracker.service;

import java.util.List;

import com.shivam.expense_tracker.entity.Budget;
import com.shivam.expense_tracker.entity.Category;
import com.shivam.expense_tracker.entity.Expense;
import com.shivam.expense_tracker.entity.User;

public class PromptBuilder {

    private PromptBuilder() {
    }

    public static String buildFinancePrompt(
            User user,
            Budget budget,
            List<Expense> expenses,
            List<Category> categories,
            String question) {

        StringBuilder prompt = new StringBuilder();

        prompt.append("""
			You are an AI Financial Assistant integrated into an Expense Tracker application.
			
			Rules:
			- Answer finance questions using ONLY the data provided below.
			- Give saving suggestions whenever appropriate.
			- If the user asks about spending, budgets or categories, use the provided information.
			- Keep answers short and practical.
			
			=========================================
			USER INFORMATION
			=========================================
			""");

        prompt.append("User : ")
                .append(user.getFullName())
                .append("\n\n");

        if (budget != null) {

            prompt.append("""
				CURRENT BUDGET
				---------------------
				""");

            prompt.append("Budget Amount : ₹")
                    .append(budget.getBudgetAmount())
                    .append("\n");

            prompt.append("Spent Amount : ₹")
                    .append(budget.getSpentAmount())
                    .append("\n");

            prompt.append("Remaining Amount : ₹")
                    .append(budget.getRemainingAmount())
                    .append("\n\n");
        }

        prompt.append("""
			CATEGORIES
			---------------------
			""");

        for (Category category : categories) {

            prompt.append("- ")
                    .append(category.getName())
                    .append("\n");
        }

        prompt.append("\n");

        prompt.append("""
			EXPENSES
			---------------------
			""");

        if (expenses.isEmpty()) {

            prompt.append("No expenses found.\n");

        } else {

            for (Expense expense : expenses) {

                prompt.append("- ")
                        .append(expense.getTitle())
                        .append(" | ₹")
                        .append(expense.getAmount())
                        .append(" | ")
                        .append(expense.getCategory().getName())
                        .append("\n");
            }
        }

        prompt.append("""

			=========================================
			USER QUESTION
			=========================================
			
			""");

        prompt.append(question);

        return prompt.toString();
    }

    public static String buildGeneralPrompt(String question) {

        return """
			You are a helpful AI assistant.
			
			Answer the following question clearly.
			
			Question:
			
			"""
                + question;
    }

}