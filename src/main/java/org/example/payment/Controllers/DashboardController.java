package org.example.payment.Controllers;

import lombok.RequiredArgsConstructor;
import org.example.payment.Dto.response.MonthlyReportResponse;
import org.example.payment.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final ReportService reportService;

    @GetMapping("/monthly")
    public ResponseEntity<MonthlyReportResponse> getMonthlyReport(@RequestParam int year,
                                                                  @RequestParam int month) {
        return ResponseEntity.ok(reportService.getMonthlyReport(year, month));
    }
}