package org.example.payment.service;

import org.example.payment.Dto.response.MonthlyReportResponse;

public interface ReportService {
    MonthlyReportResponse getMonthlyReport(int year, int month);
}