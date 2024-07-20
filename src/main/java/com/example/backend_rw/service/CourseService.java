package com.example.backend_rw.service;

import com.example.backend_rw.entity.dto.course.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseService {
    List<CourseReturnHomePageResponse> getCourseIntoHomePage(Integer categoryId);

    CourseReturnDetailPageResponse getCourseDetail(String slug);

    List<CourseReturnSearch> listAllCourseByKeyword(String keyword);

    List<CourseResponse> getAll();

    String updateIsEnabled(Integer courseId, boolean isEnabled);

    String updateIsPublished(Integer courseId, boolean isPublished);

    String updateIsFinished(Integer courseId, boolean isFinished);

    CourseResponse create(CoursesRequest coursesRequest, MultipartFile image);

    CourseResponse get(Integer courseId);
}
