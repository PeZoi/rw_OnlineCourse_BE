package com.example.backend_rw.service;

import com.example.backend_rw.entity.dto.review.ListReviewResponse;

public interface ReviewService {
    ListReviewResponse listAllByCourse(Integer courseId);
    String checkCustomerToReviewed(Integer userId, Integer courseId);
}
