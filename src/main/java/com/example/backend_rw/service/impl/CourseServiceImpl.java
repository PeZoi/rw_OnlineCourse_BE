package com.example.backend_rw.service.impl;

import com.example.backend_rw.entity.Category;
import com.example.backend_rw.entity.Courses;
import com.example.backend_rw.entity.Review;
import com.example.backend_rw.entity.dto.course.CourseReturnHomePageResponse;
import com.example.backend_rw.exception.CustomException;
import com.example.backend_rw.repository.CategoryRepository;
import com.example.backend_rw.repository.CoursesRepository;
import com.example.backend_rw.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CourseServiceImpl implements CourseService {
    private final ModelMapper modelMapper;
    private final CoursesRepository coursesRepository;
    private final CategoryRepository categoryRepository;

    public CourseServiceImpl(ModelMapper modelMapper, CoursesRepository coursesRepository, CategoryRepository categoryRepository) {
        this.modelMapper = modelMapper;
        this.coursesRepository = coursesRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CourseReturnHomePageResponse> getCourseIntoHomePage(Integer categoryId) {
        List<Courses> listCourses = null;

        if (categoryId == null) {
            listCourses = coursesRepository.findAll();
        } else {
            // Kiểm tra nếu categoryId không tồn tại thì báo lỗi
            Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CustomException("Course ID: " + categoryId + " không tồn tại"));

            listCourses = coursesRepository.findAllByCategoryId(categoryId);
        }

        // Map qua từng phần tử của listCourses, formatted lại dữ liệu
        return listCourses.stream().map(courses -> {
            // Mapper dữ liêu course qua dữ liệu muốn trả về
            CourseReturnHomePageResponse response = modelMapper.map(courses, CourseReturnHomePageResponse.class);
            // Tính toán các số liệu
            int totalReview = courses.getListReviews().size();
            int totalRating = courses.getListReviews().stream().mapToInt(Review::getRating).sum();
            double averageRating = (double) totalRating / totalReview;
            averageRating = Math.round(averageRating * 10.0) / 10.0;
            response.setTotalReview(totalReview);
            response.setAverageReview(averageRating);
            return response;
        }).toList();
    }
}
