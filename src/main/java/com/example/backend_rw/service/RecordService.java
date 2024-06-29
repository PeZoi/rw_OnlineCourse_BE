package com.example.backend_rw.service;

import com.example.backend_rw.entity.dto.record.RecordRequest;
import com.example.backend_rw.entity.dto.record.RecordResponse;
import com.example.backend_rw.entity.dto.record.RecordReturnInRank;
import com.example.backend_rw.entity.dto.record.RecordReturnToReview;

import java.util.List;

public interface RecordService {
    List<RecordResponse> listAllRecord(Integer userId);
    List<RecordReturnInRank> ranking(Integer contestId);
    List<RecordResponse> listAllRecordByUserAndContest(Integer userId, Integer contestId);
    RecordResponse saveRecord(RecordRequest recordRequest);
    RecordReturnToReview review(Integer recordId);
}
