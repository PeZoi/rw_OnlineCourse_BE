package com.example.backend_rw.service;

import com.example.backend_rw.entity.dto.contest.ContestResponse;

import java.util.List;

public interface ContestService {
    List<ContestResponse> listAll();
}
