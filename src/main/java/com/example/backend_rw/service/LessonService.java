package com.example.backend_rw.service;

import com.example.backend_rw.entity.Courses;
import com.example.backend_rw.entity.TextLesson;
import com.example.backend_rw.entity.Video;
import com.example.backend_rw.entity.dto.lesson.LessonRequest;
import com.example.backend_rw.entity.dto.lesson.LessonResponse;
import com.example.backend_rw.entity.dto.quiz.QuizRequest;

public interface LessonService {
    Courses getCourse(Integer lessonId);

    LessonResponse create(LessonRequest lessonRequest, Video video, TextLesson textLesson, QuizRequest[] quizRequest);
}
