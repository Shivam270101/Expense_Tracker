package com.shivam.expense_tracker.dto.expense;


import java.time.LocalDate;

import com.shivam.expense_tracker.enums.PaymentMode;

import lombok.Data;

@Data
public class ExpenseFilterRequest {

    private LocalDate startDate;

    private LocalDate endDate;

    private Long categoryId;

    private PaymentMode paymentMode;
}