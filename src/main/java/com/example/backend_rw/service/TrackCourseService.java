package com.example.backend_rw.service;

import com.example.backend_rw.entity.dto.lesson.LessonReturnLearningResponse;
import com.example.backend_rw.entity.dto.track.InfoCourseRegistered;

public interface TrackCourseService {
    InfoCourseRegistered listTrackCourse(String email, String slug);

    LessonReturnLearningResponse getLesson(Integer lessonId);

    Integer confirmLessonLearned(String email, Integer lessonIdPre);
}
