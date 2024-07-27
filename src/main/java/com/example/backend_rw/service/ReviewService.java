package com.example.backend_rw.service;

import com.example.backend_rw.entity.dto.review.ListReviewResponse;
import com.example.backend_rw.entity.dto.review.ReviewRequest;
import com.example.backend_rw.entity.dto.review.ReviewResponse;

public interface ReviewService {
    ListReviewResponse listAllByCourse(Integer courseId);

    String checkCustomerToReviewed(Integer userId, Integer courseId);

    ReviewResponse createReview(ReviewRequest reviewRequest);

    String deleteReview(Integer reviewId);

    ListReviewResponse listAll();
}
