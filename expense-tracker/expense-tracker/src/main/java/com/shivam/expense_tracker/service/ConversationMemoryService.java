package com.shivam.expense_tracker.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class ConversationMemoryService {

    private final Map<String, StringBuilder> conversations =
            new ConcurrentHashMap<>();

    // Save user question and AI answer
    public void addMessage(
            String email,
            String question,
            String answer) {

        StringBuilder history =
                conversations.computeIfAbsent(
                        email,
                        k -> new StringBuilder());

        history.append("\n");
        history.append("User : ");
        history.append(question);

        history.append("\n");

        history.append("Assistant : ");
        history.append(answer);

        history.append("\n");
    }

    // Return conversation history
    public String getConversation(String email) {

        return conversations
                .getOrDefault(email, new StringBuilder())
                .toString();
    }

    // Clear chat history
    public void clearConversation(String email) {

        conversations.remove(email);
    }
}