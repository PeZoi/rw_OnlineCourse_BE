package com.example.backend_rw.service.impl;

import com.example.backend_rw.entity.Lesson;
import com.example.backend_rw.entity.QA;
import com.example.backend_rw.entity.dto.qa.QAResponse;
import com.example.backend_rw.exception.NotFoundException;
import com.example.backend_rw.repository.LessonRepository;
import com.example.backend_rw.repository.QARepository;
import com.example.backend_rw.service.QAService;
import com.example.backend_rw.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;

@Service
public class QAServiceImpl implements QAService {
    private final ModelMapper modelMapper;
    private final LessonRepository lessonRepository;
    private final QARepository qaRepository;

    public QAServiceImpl(ModelMapper modelMapper, LessonRepository lessonRepository, QARepository qaRepository) {
        this.modelMapper = modelMapper;
        this.lessonRepository = lessonRepository;
        this.qaRepository = qaRepository;
    }

    @Override
    public List<QAResponse> listAll(Integer lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new NotFoundException("Lesson ID không tồn tại"));
        List<QA> listQAs = qaRepository.findAllByLesson(lesson.getId());

        return listQAs.stream()
                .sorted(Comparator.comparing(QA::getCreatedAt).reversed())
                .map(this::convertToQAResponse)
                .toList();
    }

    private QAResponse convertToQAResponse(QA qa){
        QAResponse response = modelMapper.map(qa, QAResponse.class);
        response.setLessonId(qa.getLesson().getId());
        response.setUserId(qa.getUser().getId());
        response.setUsername(qa.getUser().getUsername());
        response.setPhotoUser(qa.getUser().getPhoto());
        response.setCreatedAtFormatted(Utils.formatDuration(Duration.between(qa.getCreatedAt(), Instant.now())));
        if(qa.getParent() != null){
            response.setParentId(qa.getParent().getId());
        }

        List<QAResponse> childrenResponse = qa.getChildren().stream()
                .sorted(Comparator.comparing(QA::getCreatedAt).reversed())
                .map(this::convertToQAResponse)
                .toList();
        response.setChildren(childrenResponse);
        return response;
    }
}
