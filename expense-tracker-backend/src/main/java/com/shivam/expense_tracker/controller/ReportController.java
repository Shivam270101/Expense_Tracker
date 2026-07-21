package com.shivam.expense_tracker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shivam.expense_tracker.dto.report.ReportRequest;
import com.shivam.expense_tracker.dto.report.ReportSummaryResponse;
import com.shivam.expense_tracker.service.ReportService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping
    public ResponseEntity<ReportSummaryResponse> generateReport(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody ReportRequest request) {

        ReportSummaryResponse response =
                reportService.generateReport(
                        userDetails.getUsername(),
                        request);

        return ResponseEntity.ok(response);
    }
}