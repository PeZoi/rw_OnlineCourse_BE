package com.example.backend_rw.service;

import com.example.backend_rw.entity.dto.feedback.FeedbackRequest;
import com.example.backend_rw.entity.dto.feedback.FeedbackResponse;

public interface FeedbackService {
    FeedbackResponse save(FeedbackRequest feedbackRequest);
}
