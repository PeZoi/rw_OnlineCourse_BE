package com.example.backend_rw.controller;

import com.example.backend_rw.entity.dto.course.CourseReturnMyLearning;
import com.example.backend_rw.service.LearningService;
import com.example.backend_rw.utils.annotation.ApiMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/learning")
public class LearningController {
    private final LearningService learningService;

    public LearningController(LearningService learningService) {
        this.learningService = learningService;
    }

    @GetMapping("/courses/{slug}")
    public ResponseEntity<?> getDetailInLearningPage(@PathVariable(value = "slug") String slug) {
        return ResponseEntity.ok(learningService.getCourseReturnLearningPage(slug));
    }

    @GetMapping("/my/course/list-all")
    public ResponseEntity<?> getListAllCourseMyLearning(@RequestParam(value = "email") String email) {
        List<CourseReturnMyLearning> listCourse = learningService.listAllCourseRegisteredByCustomer(email);
        if (listCourse.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(listCourse);
    }

    @GetMapping("/check/exist-course/{slug}")
    public ResponseEntity<?> checkExistRegisterCourse(@PathVariable(value = "slug") String slug, @RequestParam(value = "email") String email) {
        return ResponseEntity.ok(learningService.isRegisterInThisCourse(slug, email));
    }
}
