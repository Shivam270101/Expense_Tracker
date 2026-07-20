package com.shivam.expense_tracker.dto.ai;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AiRequest {

    @NotBlank(message = "Prompt cannot be empty")
    private String question;

}