package com.example.backend_rw.service;

import com.example.backend_rw.entity.dto.lesson.LessonRequestInQuiz;

public interface QuizService {
    float gradeOfQuiz(LessonRequestInQuiz lessonRequestInQuiz);
}
