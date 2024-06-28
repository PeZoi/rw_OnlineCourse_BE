package com.example.backend_rw.service.impl;

import com.example.backend_rw.entity.Contest;
import com.example.backend_rw.entity.Quiz;
import com.example.backend_rw.entity.dto.contest.ContestResponse;
import com.example.backend_rw.entity.dto.contest.ContestReturnInTest;
import com.example.backend_rw.entity.dto.quiz.QuizReturnLearningPage;
import com.example.backend_rw.exception.NotFoundException;
import com.example.backend_rw.repository.ContestRepository;
import com.example.backend_rw.repository.RecordRepository;
import com.example.backend_rw.service.ContestService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Override
    public ContestReturnInTest joinTest(Integer contestId) {
        Contest contestInDB = contestRepository.findById(contestId)
                .orElseThrow(() -> new NotFoundException("Contest ID không tồn tại"));

        ContestReturnInTest responseContest = modelMapper.map(contestInDB, ContestReturnInTest.class);
        List<QuizReturnLearningPage> quizReturnLearningPages = convertToQuizResponse(contestInDB.getListQuizzes());
        responseContest.setListQuizzes(quizReturnLearningPages);
        responseContest.setNumberQuestion(quizReturnLearningPages.size());
        return responseContest;
    }

    private List<QuizReturnLearningPage> convertToQuizResponse(List<Quiz> quizzes) {
        List<QuizReturnLearningPage> listQuizzes = new ArrayList<>();
        int i = 0;
        for (Quiz quiz : quizzes) {
            QuizReturnLearningPage quizReturnLearningPage = modelMapper.map(quiz, QuizReturnLearningPage.class);
            if (quiz.getQuizType().toString().equals("PERFORATE")) {
                quizReturnLearningPage.setAnswerList(null);
            }
            quizReturnLearningPage.setOrder(++i);
            listQuizzes.add(quizReturnLearningPage);
        }
        return listQuizzes;
    }
}
