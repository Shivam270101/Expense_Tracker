package com.shivam.expense_tracker.dto.report;
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
public class ReportRequest {
	
	private LocalDate fromDate;

    private LocalDate toDate;

    private Long categoryId;

    private PaymentMode paymentMode;

}
