package com.shivam.expense_tracker.dto.expense;


import java.math.BigDecimal;
import java.time.LocalDate;

import com.shivam.expense_tracker.enums.PaymentMode;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ExpenseRequest {

    @NotBlank
    private String title;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal amount;

    private String description;

    @NotNull
    private LocalDate expenseDate;

    @NotNull
    private PaymentMode paymentMode;

    @NotNull
    private Long categoryId;

    @NotNull
    private Long userId;
}
