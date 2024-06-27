package com.example.backend_rw.service.impl;

import com.example.backend_rw.entity.Contest;
import com.example.backend_rw.entity.dto.contest.ContestResponse;
import com.example.backend_rw.repository.ContestRepository;
import com.example.backend_rw.repository.RecordRepository;
import com.example.backend_rw.service.ContestService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ContestServiceImpl implements ContestService {
    private final ModelMapper modelMapper;
    private final ContestRepository contestRepository;
    private final RecordRepository recordRepository;

    public ContestServiceImpl(ModelMapper modelMapper, ContestRepository contestRepository, RecordRepository recordRepository) {
        this.modelMapper = modelMapper;
        this.contestRepository = contestRepository;
        this.recordRepository = recordRepository;
    }

    @Override
    public List<ContestResponse> listAll() {
        List<Contest> listContests = contestRepository.findAll();
        return listContests.stream().map(
                contest -> {
                    ContestResponse response = modelMapper.map(contest, ContestResponse.class);
                    response.setNumberQuestion(contest.getListQuizzes().size());
                    response.setListQuizzes(null);
                    int numberOfJoined = recordRepository.countAllByContest(contest);
                    response.setNumberOfJoined(numberOfJoined);
                    return response;
                }).toList();
    }
}
