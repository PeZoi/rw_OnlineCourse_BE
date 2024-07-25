package com.example.backend_rw.service;

import com.example.backend_rw.entity.dto.contest.ContestRequest;
import com.example.backend_rw.entity.dto.contest.ContestResponse;
import com.example.backend_rw.entity.dto.contest.ContestReturnInTest;

import java.util.List;

public interface ContestService {
    List<ContestResponse> listAll();

    ContestReturnInTest joinTest(Integer contestId);

    List<ContestResponse> search(String keyword);

    ContestResponse save(ContestRequest contestRequest);

    ContestResponse update(Integer contestId, ContestRequest contestRequest);

    String delete(Integer contestId);

    ContestResponse get(Integer contestId);
}
