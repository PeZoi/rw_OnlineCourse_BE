package com.example.backend_rw.service.impl;

import com.example.backend_rw.entity.Courses;
import com.example.backend_rw.entity.Lesson;
import com.example.backend_rw.repository.LessonRepository;
import com.example.backend_rw.service.LessonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;

    public LessonServiceImpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public Courses getCourse(Integer lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).get();
        return lesson.getChapter().getCourse();
    }
}
