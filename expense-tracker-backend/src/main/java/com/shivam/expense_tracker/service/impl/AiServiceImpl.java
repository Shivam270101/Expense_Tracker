package com.shivam.expense_tracker.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.shivam.expense_tracker.dto.ai.AiRequest;
import com.shivam.expense_tracker.dto.ai.AiResponse;
import com.shivam.expense_tracker.entity.Budget;
import com.shivam.expense_tracker.entity.Category;
import com.shivam.expense_tracker.entity.Expense;
import com.shivam.expense_tracker.entity.User;
import com.shivam.expense_tracker.exception.ResourceNotFoundException;
import com.shivam.expense_tracker.repository.BudgetRepository;
import com.shivam.expense_tracker.repository.CategoryRepository;
import com.shivam.expense_tracker.repository.ExpenseRepository;
import com.shivam.expense_tracker.repository.UserRepository;
import com.shivam.expense_tracker.service.AiService;
import com.shivam.expense_tracker.service.ConversationMemoryService;
import com.shivam.expense_tracker.service.PromptBuilder;
import com.shivam.expense_tracker.service.FinancialInsightsService;

@Service
public class AiServiceImpl implements AiService {

    private final ChatClient chatClient;
    private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository;
    private final BudgetRepository budgetRepository;
    private final CategoryRepository categoryRepository;
    private final ConversationMemoryService memoryService;    
    private final FinancialInsightsService insightsService;

    public AiServiceImpl(
            ChatClient chatClient,
            UserRepository userRepository,
            ExpenseRepository expenseRepository,
            BudgetRepository budgetRepository,
            CategoryRepository categoryRepository,
            ConversationMemoryService memoryService,
            FinancialInsightsService insightsService) {

        this.chatClient = chatClient;
        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;
        this.budgetRepository = budgetRepository;
        this.categoryRepository = categoryRepository;
        this.memoryService  = memoryService;
        this.insightsService = insightsService;
    }

    @Override
    public AiResponse askQuestion(String email, AiRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        String question = request.getQuestion();

        String prompt;

        if (isFinanceQuestion(question)) {

            Integer month = LocalDate.now().getMonthValue();
            Integer year = LocalDate.now().getYear();

            List<Expense> expenses =
                    expenseRepository.findByUser(user);

            List<Category> categories =
                    categoryRepository.findByUser(user);

            Budget budget =
                    budgetRepository
                            .findByUserAndMonthAndYear(
                                    user,
                                    month,
                                    year)
                            .orElse(null);
            String insights =
                    insightsService.buildInsights(
                            budget,
                            expenses);

            String financePrompt =
                    PromptBuilder.buildFinancePrompt(
                            user,
                            budget,
                            expenses,
                            categories,
                            question);

            prompt = financePrompt + "\n\n" + insights;

        } else {

            prompt =
                    PromptBuilder.buildGeneralPrompt(question);
        }

        // Previous conversation
        String history =
                memoryService.getConversation(email);

        String finalPrompt = """

    Previous Conversation

    %s

    Current Question

    %s

    """
    .formatted(history, prompt);

        String answer = chatClient.prompt()

        		.system("""

        				You are FinMate,
        				an intelligent AI Financial Assistant.

        				You help users understand
        				their spending habits,
        				budgets and savings.

        				Rules:

        				1. Use financial data whenever supplied.

        				2. Never invent expenses.

        				3. If no financial data is supplied,
        				answer normally.

        				4. Give practical advice.

        				5. Explain numbers simply.

        				6. Be concise.

        				7. If user asks coding,
        				Java,
        				Spring Boot,
        				React,
        				SQL,
        				or interview questions,
        				answer normally.

        				8. Encourage saving habits.

        				""")
                .user(finalPrompt)
                .call()
                .content();

        // Save conversation
        memoryService.addMessage(
                email,
                question,
                answer);

        return AiResponse.builder()
                .response(answer)
                .build();
    }
    
    private boolean isFinanceQuestion(String question) {

        if (question == null || question.isBlank()) {
            return false;
        }

        question = question.toLowerCase();

        return question.contains("expense")
                || question.contains("expenses")
                || question.contains("budget")
                || question.contains("spent")
                || question.contains("spending")
                || question.contains("remaining")
                || question.contains("saving")
                || question.contains("save")
                || question.contains("category")
                || question.contains("categories")
                || question.contains("money")
                || question.contains("finance")
                || question.contains("income")
                || question.contains("salary")
                || question.contains("transaction");
    }

}