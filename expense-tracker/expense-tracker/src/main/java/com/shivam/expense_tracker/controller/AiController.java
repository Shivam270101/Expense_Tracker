package com.shivam.expense_tracker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shivam.expense_tracker.dto.ai.AiRequest;
import com.shivam.expense_tracker.dto.ai.AiResponse;
import com.shivam.expense_tracker.service.AiService;
import org.springframework.security.core.Authentication;
import com.shivam.expense_tracker.service.ConversationMemoryService;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final AiService aiService;
    private final ConversationMemoryService memoryService;

    public AiController(AiService aiService,
    		 ConversationMemoryService memoryService) {
        this.aiService = aiService;
        this.memoryService = memoryService;
    }

    @PostMapping("/chat")
    public ResponseEntity<AiResponse> askQuestion(
            @RequestBody AiRequest request,
            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                aiService.askQuestion(email, request));
    }
    
    @DeleteMapping("/conversation")
    public ResponseEntity<String> clearConversation(
            Authentication authentication) {

        String email = authentication.getName();

        memoryService.clearConversation(email);

        return ResponseEntity.ok("Conversation cleared successfully.");
    }
}