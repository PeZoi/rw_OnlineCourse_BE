package com.example.backend_rw.service;

import com.example.backend_rw.entity.dto.report.ContestReportResponse;
import com.example.backend_rw.entity.dto.report.CountReportResponse;
import com.example.backend_rw.entity.dto.report.RevenueReportResponse;

import java.util.List;

public interface ReportService {
    List<RevenueReportResponse> getRevenueReport(String period);

    List<RevenueReportResponse> getCategoryIncomeReport();

    List<RevenueReportResponse> getCourseIncomeReport(String period);

    List<ContestReportResponse> getContestReport();

    CountReportResponse count();
}
