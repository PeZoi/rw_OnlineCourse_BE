package com.example.backend_rw.controller;

import com.example.backend_rw.entity.dto.feedback.FeedbackRequest;
import com.example.backend_rw.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendFeedback(@RequestBody @Valid FeedbackRequest feedbackRequest){
        return new ResponseEntity<>(feedbackService.save(feedbackRequest), HttpStatus.CREATED);
    }
}
