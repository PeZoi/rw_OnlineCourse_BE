package com.example.backend_rw.service;

import com.example.backend_rw.entity.dto.course.CourseReturnLearningPageResponse;
import com.example.backend_rw.entity.dto.course.CourseReturnMyLearning;

import java.util.List;

public interface LearningService {
    List<CourseReturnMyLearning> listAllCourseRegisteredByCustomer(String email);
    boolean isRegisterInThisCourse(String slug, String email);
    CourseReturnLearningPageResponse getCourseReturnLearningPage(String slug);
}
