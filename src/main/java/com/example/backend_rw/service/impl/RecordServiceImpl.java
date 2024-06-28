package com.example.backend_rw.service.impl;

import com.example.backend_rw.entity.Contest;
import com.example.backend_rw.entity.Record;
import com.example.backend_rw.entity.User;
import com.example.backend_rw.entity.dto.record.RecordResponse;
import com.example.backend_rw.entity.dto.record.RecordReturnInRank;
import com.example.backend_rw.exception.NotFoundException;
import com.example.backend_rw.repository.ContestRepository;
import com.example.backend_rw.repository.RecordRepository;
import com.example.backend_rw.repository.UserRepository;
import com.example.backend_rw.service.RecordService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
public class RecordServiceImpl implements RecordService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RecordRepository recordRepository;
    private final ContestRepository contestRepository;

    public RecordServiceImpl(ModelMapper modelMapper, UserRepository userRepository, RecordRepository recordRepository, ContestRepository contestRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.recordRepository = recordRepository;
        this.contestRepository = contestRepository;
    }

    @Override
    public List<RecordResponse> listAllRecord(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User ID không hợp lệ"));

        List<Record> listRecords = recordRepository.findAllByUser(user);
        return listRecords.stream().map(this::convertToRecordResponse).toList();
    }

    @Override
    public List<RecordReturnInRank> ranking(Integer contestId) {
        Contest contest = contestRepository.findById(contestId)
                .orElseThrow(() -> new NotFoundException("Contest ID không tồn tại"));

        List<Record> listRecords = recordRepository.findAllByContest(contest);

        Map<Integer, Record> recordMap = new HashMap<>();
        for(Record record : listRecords){
            Integer userId = record.getUser().getId();
            // Nếu mà trong danh sách chưa chứa user
            if(!recordMap.containsKey(userId)){
                // Cho user vào danh sách
                recordMap.put(userId, record);
            }else{
                // Nếu trong danh sách có user rồi
                Record recordFromMap = recordMap.get(userId);
                // Kiểm tra thời gian làm bài
                // Nếu thời gian nào nhỏ hơn (tức là làm trước đó rồi) thì sẽ gán lại thông tin trong danh sách
                if(record.getJoinedAt().isBefore(recordFromMap.getJoinedAt())){
                    recordMap.put(userId, record);
                }
            }
        }

        List<Record> filteredRecords = new ArrayList<>(recordMap.values());
        // Sắp xếp lại theo thứ tự (ưu tiên: điểm số -> thời gian làm bài -> thời gian tham gia)
        filteredRecords.sort(Comparator.comparing(Record::getGrade).reversed()
                .thenComparing(Record::getPeriod)
                .thenComparing(Record::getJoinedAt));

        return filteredRecords.stream().map(this::convertToRank).toList();
    }

    @Override
    public List<RecordResponse> listAllRecordByUserAndContest(Integer userId, Integer contestId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User ID không tồn tại"));

        Contest contest = contestRepository.findById(contestId)
                .orElseThrow(() -> new NotFoundException("Contest ID không tồn tại"));

        List<Record> listRecords = recordRepository.findAllByUserAndContest(user, contest);
        return listRecords.stream().map(this::convertToRecordResponse).toList();
    }

    private RecordReturnInRank convertToRank(Record record){
        RecordReturnInRank rank = modelMapper.map(record, RecordReturnInRank.class);
        rank.setUsername(record.getUser().getUsername());
        rank.setAvatarUser(record.getUser().getPhoto());

        return rank;
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
