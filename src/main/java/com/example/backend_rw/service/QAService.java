package com.example.backend_rw.service;

import com.example.backend_rw.entity.dto.qa.QARequest;
import com.example.backend_rw.entity.dto.qa.QAResponse;

import java.util.List;

public interface QAService {
    List<QAResponse> listAll(Integer lessonId);
    List<QAResponse> listAllForAdmin();
    QAResponse createQA(QARequest qaRequest);
    QAResponse updateQA(Integer qaId, String content);
    String deleteQA(Integer qaId);
}
