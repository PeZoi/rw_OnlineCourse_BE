package com.example.backend_rw.controller;

import com.example.backend_rw.entity.dto.review.ListReviewResponse;
import com.example.backend_rw.entity.dto.review.ReviewRequest;
import com.example.backend_rw.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid ReviewRequest reviewRequest){
        return ResponseEntity.ok(reviewService.createReview(reviewRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer reviewId){
        return ResponseEntity.ok(reviewService.deleteReview(reviewId));
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
