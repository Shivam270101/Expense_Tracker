package com.shivam.expense_tracker.dto.expense;


import java.math.BigDecimal;
import java.time.LocalDate;

import com.shivam.expense_tracker.enums.PaymentMode;

import lombok.Data;

@Data
public class ExpenseResponse {

    private Long id;

    private String title;

    private BigDecimal amount;

    private String description;

    private LocalDate expenseDate;

    private PaymentMode paymentMode;

    private String categoryName;

    private String userName;
}