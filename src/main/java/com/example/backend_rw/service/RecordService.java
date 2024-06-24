package com.example.backend_rw.service;

import com.example.backend_rw.entity.dto.record.RecordResponse;

import java.util.List;

public interface RecordService {
    List<RecordResponse> listAllRecord(Integer userId);
}
