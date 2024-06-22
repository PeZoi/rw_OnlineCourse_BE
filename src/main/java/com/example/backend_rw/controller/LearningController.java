package com.example.backend_rw.controller;

import com.example.backend_rw.entity.dto.course.CourseReturnMyLearning;
import com.example.backend_rw.service.LearningService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/learning")
public class LearningController {
    private final LearningService learningService;

    public LearningController(LearningService learningService) {
        this.learningService = learningService;
    }

    @GetMapping("/my/course/list-all")
    public ResponseEntity<?> getListAllCourseMyLearning(@RequestParam(value = "email") String email){
        List<CourseReturnMyLearning> listCourse = learningService.listAllCourseRegisteredByCustomer(email);
        if(listCourse.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(listCourse);
    }
}
