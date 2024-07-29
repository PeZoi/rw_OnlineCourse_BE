package com.example.backend_rw.controller;

import com.example.backend_rw.entity.dto.feedback.FeedbackRequest;
import com.example.backend_rw.entity.dto.feedback.SendEmail;
import com.example.backend_rw.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendFeedback(@RequestBody @Valid FeedbackRequest feedbackRequest) {
        return new ResponseEntity<>(feedbackService.save(feedbackRequest), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Integer feedbackId) {
        return ResponseEntity.ok(feedbackService.get(feedbackId));
    }

    @GetMapping("/list-all")
    public ResponseEntity<?> listALl() {
        return ResponseEntity.ok(feedbackService.listAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer feedbackId) {
        return ResponseEntity.ok(feedbackService.delete(feedbackId));
    }

    @PutMapping("/send-email")
    public ResponseEntity<?> sendEmail(@RequestBody @Valid SendEmail sendEmail) {
        return ResponseEntity.ok(feedbackService.sendMail(sendEmail));
    }
}
