package com.shivam.expense_tracker.dto.report;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.shivam.expense_tracker.enums.PaymentMode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportExpenseDTO {

    private Long expenseId;

    private LocalDate expenseDate;

    private String title;

    private String description;

    private String categoryName;

    private PaymentMode paymentMode;

    private BigDecimal amount;

}