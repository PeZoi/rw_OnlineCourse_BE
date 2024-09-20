package com.example.backend_rw.controller;

import com.example.backend_rw.entity.dto.report.CountReportResponse;
import com.example.backend_rw.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/report")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/count")
    public ResponseEntity<CountReportResponse> count(){
        return ResponseEntity.ok(reportService.count());
    }

    @GetMapping("/sales-income/{period}")
    public ResponseEntity<?> getRevenueReport(@PathVariable(value = "period") String period){
        return ResponseEntity.ok().body(reportService.getRevenueReport(period));
    }
}
