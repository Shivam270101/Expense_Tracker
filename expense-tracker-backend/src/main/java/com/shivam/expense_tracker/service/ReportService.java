package com.shivam.expense_tracker.service;

import com.shivam.expense_tracker.dto.report.ReportRequest;
import com.shivam.expense_tracker.dto.report.ReportSummaryResponse;

public interface ReportService {

    ReportSummaryResponse generateReport(
            String email,
            ReportRequest request);

}
