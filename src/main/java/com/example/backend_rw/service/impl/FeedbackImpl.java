package com.example.backend_rw.service.impl;

import com.example.backend_rw.entity.Feedback;
import com.example.backend_rw.entity.FeedbackStatus;
import com.example.backend_rw.entity.dto.feedback.FeedbackRequest;
import com.example.backend_rw.entity.dto.feedback.FeedbackResponse;
import com.example.backend_rw.entity.dto.feedback.SendEmail;
import com.example.backend_rw.exception.NotFoundException;
import com.example.backend_rw.repository.FeedbackRepository;
import com.example.backend_rw.service.FeedbackService;
import com.example.backend_rw.utils.EmailUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class FeedbackImpl implements FeedbackService {
    private final ModelMapper modelMapper;
    private final FeedbackRepository feedbackRepository;
    private final EmailUtil emailUtil;

    public FeedbackImpl(ModelMapper modelMapper, FeedbackRepository feedbackRepository, EmailUtil emailUtil) {
        this.modelMapper = modelMapper;
        this.feedbackRepository = feedbackRepository;
        this.emailUtil = emailUtil;
    }

    @Override
    public FeedbackResponse save(FeedbackRequest feedbackRequest) {
        Feedback feedback = modelMapper.map(feedbackRequest, Feedback.class);
        feedback.setStatus(FeedbackStatus.NEW);

        Feedback savedFeedback = feedbackRepository.save(feedback);

        return modelMapper.map(savedFeedback, FeedbackResponse.class);
    }

    @Override
    public FeedbackResponse get(Integer feedbackId) {
        Feedback feedbackInDB = feedbackRepository.findById(feedbackId).orElseThrow(() -> new NotFoundException("Feedback ID không tồn tại"));
        return modelMapper.map(feedbackInDB, FeedbackResponse.class);
    }

    @Override
    public List<FeedbackResponse> listAll() {
        List<Feedback> listFeedback = feedbackRepository.findAll();
        return listFeedback.stream().map(feedback -> modelMapper.map(feedback, FeedbackResponse.class)).toList();
    }

    @Override
    public String delete(Integer feedbackId) {
        Feedback feedbackInDB = feedbackRepository.findById(feedbackId).orElseThrow(() -> new NotFoundException("Feedback ID không tồn tại"));
        feedbackRepository.delete(feedbackInDB);
        return "Xoá thành công";
    }

    @Override
    public String sendMail(SendEmail sendEmail) {
        emailUtil.sendEmailForFeedback(sendEmail);

        Feedback feedbackInDB = feedbackRepository.findById(sendEmail.getFeedbackId()).orElseThrow(() -> new NotFoundException("Feedback ID không tồn tại"));

        feedbackInDB.setStatus(FeedbackStatus.SENT);
        feedbackRepository.save(feedbackInDB);
        return "Trả lời bằng email thành công!";
    }
}
