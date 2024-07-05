package com.example.backend_rw.service;

import com.example.backend_rw.entity.Courses;

public interface LessonService {
    Courses getCourse(Integer lessonId);
}
