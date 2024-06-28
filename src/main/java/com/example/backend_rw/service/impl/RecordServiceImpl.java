package com.example.backend_rw.service.impl;

import com.example.backend_rw.entity.*;
import com.example.backend_rw.entity.Record;
import com.example.backend_rw.entity.dto.quiz.AnswerLearningRequest;
import com.example.backend_rw.entity.dto.quiz.QuizLearningRequest;
import com.example.backend_rw.entity.dto.record.RecordRequest;
import com.example.backend_rw.entity.dto.record.RecordResponse;
import com.example.backend_rw.entity.dto.record.RecordReturnInRank;
import com.example.backend_rw.exception.NotFoundException;
import com.example.backend_rw.repository.*;
import com.example.backend_rw.service.RecordService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;

@Transactional
@Service
public class RecordServiceImpl implements RecordService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RecordRepository recordRepository;
    private final ContestRepository contestRepository;
    private final QuizRepository quizRepository;
    private final AnswerQuizRepository answerQuizRepository;

    public RecordServiceImpl(ModelMapper modelMapper, UserRepository userRepository, RecordRepository recordRepository, ContestRepository contestRepository, QuizRepository quizRepository, AnswerQuizRepository answerQuizRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.recordRepository = recordRepository;
        this.contestRepository = contestRepository;
        this.quizRepository = quizRepository;
        this.answerQuizRepository = answerQuizRepository;
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

    @Override
    public RecordResponse saveRecord(RecordRequest recordRequest) {
        User user = userRepository.findById(recordRequest.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("User ID không tồn tại"));

        Contest contest = contestRepository.findById(recordRequest.getContestId())
                .orElseThrow(() -> new NotFoundException("Contest ID không tồn tại"));

        // Lưu thông tin vào trong record
        Record record = new Record();
        record.setUser(user);
        record.setContest(contest);
        record.setPeriod(recordRequest.getPeriod());
        record.setJoinedAt(Instant.now());

        float totalQuizzes = contest.getListQuizzes().size();
        float correctTotalQuizzes = 0;

        // Duyệt các câu hỏi
        for (QuizLearningRequest quizLearningRequest : recordRequest.getListQuizzes()){
            // Lấy id câu hỏi
            Integer quizId = quizLearningRequest.getId();
            Quiz quizInDB = quizRepository.findById(quizId)
                    .orElseThrow(() -> new NotFoundException("Quiz ID không tồn tại"));

            Set<Answer> answers = new HashSet<>();
            // Đáp án của câu hỏi là đục lỗ
            String contentPerforate = null;

            switch (quizInDB.getQuizType()){
                case ONE_CHOICE -> {
                    Integer answerId = quizLearningRequest.getListAnswers().get(0).getId();

                    Answer answerByIDInDB = answerQuizRepository.findById(answerId)
                            .orElseThrow(() -> new NotFoundException("Answer ID không tồn tại"));

                    // Kiểm tra xem câu trả lời của user chọn có đúng không
                    Answer answer = answerQuizRepository.checkAnswerInCorrect(answerId);
                    // Nếu câu trả lời là đúng thì tăng số câu hỏi đúng + 1
                    if(answer != null) {
                        ++correctTotalQuizzes;
                    }

                    // Thêm câu trả lời vào trong set
                    answers.add(answerByIDInDB);
                }
                case PERFORATE -> {
                        List<Answer> listAnswers = answerQuizRepository.listAllAnswerIsCorrect(quizId);
                        // Lấy câu trả lời của user
                        String contentAnswer = quizLearningRequest.getListAnswers().get(0).getContentPerforate();
                        for (Answer answer : listAnswers){
                            // Kiểm tra xem câu trả lời có đúng không
                            if(contentAnswer.equalsIgnoreCase(answer.getContent())){
                                // Tăng số câu trả lời đúng
                                ++correctTotalQuizzes;
                                break;
                            }
                        }

                        // Gán là đáp án của user
                        contentPerforate = contentAnswer;
                }
                case MULTIPLE_CHOICE -> {
                    List<Answer> listAnswers = answerQuizRepository.listAllAnswerIsCorrect(quizId);

                    // Số câu trả lời đúng của câu hỏi
                    float totalAnswerCorrectInList = listAnswers.size();
                    // Số câu trả lời đúng của user
                    float totalAnswerCorrectInThere = 0.0f;

                    for (AnswerLearningRequest answerLearningRequest : quizLearningRequest.getListAnswers()){
                        // Lấy ra id của câu trả lời
                        Integer answerId = answerLearningRequest.getId();
                        Answer answerByIDInDB = answerQuizRepository.findById(answerId)
                                .orElseThrow(() -> new NotFoundException("Answer ID không tồn tại"));

                        // Lấy ra kiểm tra đáp án trong db (nếu có thì là đúng và ngược lại)
                        Answer answer = answerQuizRepository.checkAnswerInCorrect(answerId);

                        // LOGIC: Giả sử bạn chọn 3 đáp nhưng trong đó chỉ có 2 đáp đúng thôi thì => câu hỏi đó bạn đúng 1 đáp án
                        if(answer != null){
                            ++totalAnswerCorrectInThere;
                        }else{
                            --totalAnswerCorrectInThere;
                        }

                        answers.add(answerByIDInDB);
                    }

                    // Nếu số câu hỏi đúng dưới 0 thì là 0
                    if(totalAnswerCorrectInThere < 0){
                        totalAnswerCorrectInThere = 0.0f;
                    }

                    // Tính toán số câu hỏi đúng
                    float percentMultipleChoiceQuiz = totalAnswerCorrectInThere / totalAnswerCorrectInList;
                    correctTotalQuizzes += percentMultipleChoiceQuiz;


                record.add(quizInDB, answers, contentPerforate);
                }
            }
//
//            if(quizInDB.getQuizType().toString().equals("ONE_CHOICE")){
//                Integer answerId = quizLearningRequest.getListAnswers().get(0).getId();
//
//                Answer answerByIDInDB = answerQuizRepository.findById(answerId)
//                        .orElseThrow(() -> new NotFoundException("Answer ID không tồn tại"));
//
//                // Kiểm tra xem câu trả lời của user chọn có đúng không
//                Answer answer = answerQuizRepository.checkAnswerInCorrect(answerId);
//                // Nếu câu trả lời là đúng thì tăng số câu hỏi đúng + 1
//                if(answer != null) {
//                    ++correctTotalQuizzes;
//                }
//
//                // Thêm câu trả lời vào trong set
//                answers.add(answerByIDInDB);
//
//            }
//            else if(quizInDB.getQuizType().toString().equals("PERFORATE")){
//                List<Answer> listAnswers = answerQuizRepository.listAllAnswerIsCorrect(quizId);
//                // Lấy câu trả lời của user
//                String contentAnswer = quizLearningRequest.getListAnswers().get(0).getContentPerforate();
//                for (Answer answer : listAnswers){
//                    // Kiểm tra xem câu trả lời có đúng không
//                    if(contentAnswer.equalsIgnoreCase(answer.getContent())){
//                        // Tăng số câu trả lời đúng
//                        ++correctTotalQuizzes;
//                        break;
//                    }
//                }
//
//                // Gán là đáp án của user
//                contentPerforate = contentAnswer;
//            }else{
//                List<Answer> listAnswers = answerQuizRepository.listAllAnswerIsCorrect(quizId);
//
//                // Số câu trả lời đúng của câu hỏi
//                float totalAnswerCorrectInList = listAnswers.size();
//                // Số câu trả lời đúng của user
//                float totalAnswerCorrectInThere = 0.0f;
//
//                for (AnswerLearningRequest answerLearningRequest : quizLearningRequest.getListAnswers()){
//                    // Lấy ra id của câu trả lời
//                    Integer answerId = answerLearningRequest.getId();
//                    Answer answerByIDInDB = answerQuizRepository.findById(answerId)
//                            .orElseThrow(() -> new NotFoundException("Answer ID không tồn tại"));
//
//                    // Lấy ra kiểm tra đáp án trong db (nếu có thì là đúng và ngược lại)
//                    Answer answer = answerQuizRepository.checkAnswerInCorrect(answerId);
//
//                    // LOGIC: Giả sử bạn chọn 3 đáp nhưng trong đó chỉ có 2 đáp đúng thôi thì => câu hỏi đó bạn đúng 1 đáp án
//                    if(answer != null){
//                        ++totalAnswerCorrectInThere;
//                    }else{
//                        --totalAnswerCorrectInThere;
//                    }
//
//                    answers.add(answerByIDInDB);
//                }
//
//                // Nếu số câu hỏi đúng dưới 0 thì là 0
//                if(totalAnswerCorrectInThere < 0){
//                    totalAnswerCorrectInThere = 0.0f;
//                }
//
//                // Tính toán số câu hỏi đúng
//                float percentMultipleChoiceQuiz = totalAnswerCorrectInThere / totalAnswerCorrectInList;
//                correctTotalQuizzes += percentMultipleChoiceQuiz;
//            }
//
//            record.add(quizInDB, answers, contentPerforate);
        }
        // Tính toán tổng điểm cho bài thi
        float grade = (correctTotalQuizzes * 10) / totalQuizzes;
        grade = (float) (Math.round(grade * 100.0) / 100.0);

        record.setGrade(grade);
        record.setTotalAnswerCorrect(correctTotalQuizzes);

        Record savedRecord = recordRepository.save(record);
        RecordResponse response = convertToRecordResponse(savedRecord);
        response.setTotalQuizzes(totalQuizzes);
        return response;
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
