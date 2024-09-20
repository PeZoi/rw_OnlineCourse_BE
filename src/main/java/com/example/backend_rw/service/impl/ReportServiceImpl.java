package com.example.backend_rw.service.impl;

import com.example.backend_rw.entity.Order;
import com.example.backend_rw.entity.dto.report.CountReportResponse;
import com.example.backend_rw.entity.dto.report.ReportRevenueResponse;
import com.example.backend_rw.repository.*;
import com.example.backend_rw.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CoursesRepository coursesRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private ContestRepository contestRepository;

    private String PERIOD_TYPE = "DAY";

    @Override
    public CountReportResponse count() {


        return CountReportResponse.builder().totalUsers((int) userRepository.count()).totalCategories((int) categoryRepository.count()).totalCourses((int) coursesRepository.count()).totalBlogs((int) blogRepository.count()).totalQuizzes((int) contestRepository.count()).totalOrders((int) orderRepository.count()).totalIncomes(orderRepository.sumInCome()).totalReviews((int) reviewRepository.count()).build();
    }

    @Override
    public List<ReportRevenueResponse> getRevenueReport(String period) {
        Instant dateNow = Instant.now();
        Instant dateBefore = getDateBefore(dateNow, period);

        List<Order> orders = orderRepository.findByOrderTimeBetween(dateBefore, dateNow);
        List<ReportRevenueResponse> responseList = createReportRevenueList(dateNow, dateBefore, orders);

        return responseList;
    }

    private List<ReportRevenueResponse> createReportRevenueList(Instant now, Instant before, List<Order> orders) {
        List<ReportRevenueResponse> responseList = new ArrayList<>();

        // Chuyển đổi Instant thành LocalDateTime
        LocalDateTime startDate = LocalDateTime.ofInstant(before, ZoneId.systemDefault());
        LocalDateTime endDate = LocalDateTime.ofInstant(now, ZoneId.systemDefault());



        // Lặp cho đến khi đạt đến endDate
        while (startDate.isBefore(endDate)) {
            List<Order> filteredOrders = new ArrayList<>();
            ReportRevenueResponse reportRevenueResponse = new ReportRevenueResponse();
            LocalDate currentDate = startDate.toLocalDate();
            YearMonth currentMonth = YearMonth.from(startDate);

            if (PERIOD_TYPE.equals("DAY")) {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                // Tính tổng thu nhập và số đơn hàng cho ngày này
                filteredOrders = orders.stream()
                        .filter(order -> LocalDate.ofInstant(order.getCreatedTime(), ZoneId.systemDefault()).equals(currentDate))
                        .toList();


                reportRevenueResponse.setIdentifier(startDate.format(dateFormat));

                startDate = startDate.plus(1, ChronoUnit.DAYS);
            } else if (PERIOD_TYPE.equals("MONTH")) {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM-yyyy");
                // Lọc các đơn hàng theo tháng
                filteredOrders = orders.stream()
                        .filter(order -> YearMonth.from(LocalDate.ofInstant(order.getCreatedTime(), ZoneId.systemDefault())).equals(currentMonth))
                        .toList();

                reportRevenueResponse.setIdentifier(startDate.format(dateFormat));

                startDate = startDate.plus(1, ChronoUnit.MONTHS).withDayOfMonth(1);
            }

            // Nếu có đơn hàng, cập nhật reportRevenueResponse
            if (!filteredOrders.isEmpty()) {
                int totalIncome = filteredOrders.stream()
                        .mapToInt(Order::getTotalPrice)
                        .sum();
                reportRevenueResponse.setTotalIncome(totalIncome);
                reportRevenueResponse.setOrderCount(filteredOrders.size());
            }

            responseList.add(reportRevenueResponse);
        }

        return responseList;
    }

    private Instant getDateBefore(Instant dateNow, String period) {

        switch (period) {
            case "last_14_days" -> {
                this.PERIOD_TYPE = "DAY";
                return dateNow.minus(Duration.ofDays(14));
            }
            case "last_year" -> {
                this.PERIOD_TYPE = "MONTH";
                return dateNow.minus(Duration.ofDays(365));
            }
            default -> {
                this.PERIOD_TYPE = "DAY";
                return dateNow.minus(Duration.ofDays(7));
            }
        }
    }

}
