package com.example.backend_rw.service;

import com.example.backend_rw.entity.dto.report.CountReportResponse;
import com.example.backend_rw.entity.dto.report.ReportRevenueResponse;

import java.util.List;

public interface ReportService {
    List<ReportRevenueResponse> getRevenueReport(String period);

    CountReportResponse count();
}
