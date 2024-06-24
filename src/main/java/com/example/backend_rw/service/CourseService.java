package com.example.backend_rw.service;

import com.example.backend_rw.entity.dto.course.CourseReturnDetailPageResponse;
import com.example.backend_rw.entity.dto.course.CourseReturnHomePageResponse;

import java.util.List;

public interface CourseService {
    List<CourseReturnHomePageResponse> getCourseIntoHomePage(Integer categoryId);
    CourseReturnDetailPageResponse getCourseDetail(String slug);
}
