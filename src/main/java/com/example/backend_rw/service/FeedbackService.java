package com.example.backend_rw.service;

import com.example.backend_rw.entity.dto.feedback.FeedbackRequest;
import com.example.backend_rw.entity.dto.feedback.FeedbackResponse;
import com.example.backend_rw.entity.dto.feedback.SendEmail;

import java.util.List;

public interface FeedbackService {
    FeedbackResponse save(FeedbackRequest feedbackRequest);

    FeedbackResponse get(Integer feedbackId);

    List<FeedbackResponse> listAll();

    String delete(Integer feedbackId);

    String sendMail(SendEmail sendEmail);
}
