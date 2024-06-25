package com.example.backend_rw.controller;

import com.example.backend_rw.entity.dto.review.ListReviewResponse;
import com.example.backend_rw.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/get-all/course/{id}")
    public ResponseEntity<?> listByCourse(@PathVariable(value = "id") Integer courseId){
        ListReviewResponse listReviewResponse = reviewService.listAllByCourse(courseId);
        if(listReviewResponse.getListResponses().isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listReviewResponse);
    }

    @GetMapping("/check-reviewed/user/{user_id}/course/{course_id}")
    public ResponseEntity<?> checkReviewed(@PathVariable(value = "user_id") Integer userId,
                                           @PathVariable(value = "course_id") Integer courseId){
        return ResponseEntity.ok(reviewService.checkCustomerToReviewed(userId, courseId));
    }
}
