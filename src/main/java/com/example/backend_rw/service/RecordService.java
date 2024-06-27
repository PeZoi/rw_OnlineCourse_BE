package com.example.backend_rw.service;

import com.example.backend_rw.entity.dto.record.RecordResponse;
import com.example.backend_rw.entity.dto.record.RecordReturnInRank;

import java.util.List;

public interface RecordService {
    List<RecordResponse> listAllRecord(Integer userId);
    List<RecordReturnInRank> ranking(Integer contestId);
}
