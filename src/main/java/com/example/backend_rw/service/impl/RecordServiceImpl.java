package com.example.backend_rw.service.impl;

import com.example.backend_rw.entity.Record;
import com.example.backend_rw.entity.User;
import com.example.backend_rw.entity.dto.record.RecordResponse;
import com.example.backend_rw.repository.RecordRepository;
import com.example.backend_rw.repository.UserRepository;
import com.example.backend_rw.service.RecordService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class RecordServiceImpl implements RecordService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RecordRepository recordRepository;

    public RecordServiceImpl(ModelMapper modelMapper, UserRepository userRepository, RecordRepository recordRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.recordRepository = recordRepository;
    }

    @Override
    public List<RecordResponse> listAllRecord(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User ID không hợp lệ"));

        List<Record> listRecords = recordRepository.findAllByUser(user);
        return listRecords.stream().map(this::convertToRecordResponse).toList();
    }

    private RecordResponse convertToRecordResponse(Record record) {
        RecordResponse recordResponse = modelMapper.map(record, RecordResponse.class);
        recordResponse.setUserId(record.getUser().getId());
        recordResponse.setUsername(record.getUser().getUsername());
        recordResponse.setContestId(record.getContest().getId());
        recordResponse.setTitleContest(record.getContest().getTitle());
        recordResponse.setTotalQuizzes(record.getContest().getListQuizzes().size());
        recordResponse.setTotalQuizIsCorrect(record.getTotalAnswerCorrect());

        return recordResponse;
    }
}
