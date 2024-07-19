package com.example.backend_rw.service;

import com.example.backend_rw.entity.dto.course.CourseResponse;
import com.example.backend_rw.entity.dto.course.CourseReturnDetailPageResponse;
import com.example.backend_rw.entity.dto.course.CourseReturnHomePageResponse;
import com.example.backend_rw.entity.dto.course.CourseReturnSearch;

import java.util.List;

public interface CourseService {
    List<CourseReturnHomePageResponse> getCourseIntoHomePage(Integer categoryId);

    CourseReturnDetailPageResponse getCourseDetail(String slug);

    List<CourseReturnSearch> listAllCourseByKeyword(String keyword);

    List<CourseResponse> getAll();

    String updateIsEnabled(Integer courseId, boolean isEnabled);

    String updateIsPublished(Integer courseId, boolean isPublished);

    String updateIsFinished(Integer courseId, boolean isFinished);
}
