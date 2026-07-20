package com.shivam.expense_tracker.service;

import com.shivam.expense_tracker.dto.ai.AiRequest;
import com.shivam.expense_tracker.dto.ai.AiResponse;

public interface AiService {

    AiResponse askQuestion(String email, AiRequest request);

}