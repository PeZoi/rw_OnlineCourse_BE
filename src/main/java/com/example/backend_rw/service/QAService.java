package com.example.backend_rw.service;

import com.example.backend_rw.entity.dto.qa.QARequest;
import com.example.backend_rw.entity.dto.qa.QAResponse;

import java.util.List;

public interface QAService {
    List<QAResponse> listAll(Integer lessonId);
    QAResponse createQA(QARequest qaRequest);
}
