package com.example.backend_rw.service.impl;

import com.example.backend_rw.entity.Feedback;
import com.example.backend_rw.entity.FeedbackStatus;
import com.example.backend_rw.entity.dto.feedback.FeedbackRequest;
import com.example.backend_rw.entity.dto.feedback.FeedbackResponse;
import com.example.backend_rw.repository.FeedbackRepository;
import com.example.backend_rw.service.FeedbackService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class FeedbackImpl implements FeedbackService {
    private final ModelMapper modelMapper;
    private final FeedbackRepository feedbackRepository;

    public FeedbackImpl(ModelMapper modelMapper, FeedbackRepository feedbackRepository) {
        this.modelMapper = modelMapper;
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public FeedbackResponse save(FeedbackRequest feedbackRequest) {
        Feedback feedback = modelMapper.map(feedbackRequest, Feedback.class);
        feedback.setStatus(FeedbackStatus.NEW);

        Feedback savedFeedback = feedbackRepository.save(feedback);

        return modelMapper.map(savedFeedback, FeedbackResponse.class);
    }
}
