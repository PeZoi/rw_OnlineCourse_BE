package com.example.backend_rw.controller;

import com.example.backend_rw.entity.dto.course.CourseResponse;
import com.example.backend_rw.entity.dto.course.CourseReturnHomePageResponse;
import com.example.backend_rw.entity.dto.course.CourseReturnSearch;
import com.example.backend_rw.entity.dto.course.CoursesRequest;
import com.example.backend_rw.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/home-page")
    public ResponseEntity<List<CourseReturnHomePageResponse>> getCourseReturnHomePage(@RequestParam(value = "categoryId", required = false) Integer categoryId) {
        List<CourseReturnHomePageResponse> listCourses = courseService.getCourseIntoHomePage(categoryId);
        if (listCourses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listCourses);
    }

    @GetMapping("/list-all")
    public ResponseEntity<List<CourseResponse>> listAllCourses(@RequestParam(value = "keyword", required = false) String keyword, @RequestParam(value = "categoryId", required = false) Integer categoryId) {
        List<CourseResponse> courseResponses = courseService.getAll();
        if (courseResponses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(courseResponses);
    }

    @GetMapping("/get-detail/{slug}")
    public ResponseEntity<?> getCourseDetailById(@PathVariable(value = "slug") String slug) {
        return ResponseEntity.ok(courseService.getCourseDetail(slug));
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(value = "keyword") String keyword) {
        List<CourseReturnSearch> listCourses = courseService.listAllCourseByKeyword(keyword);
        if (listCourses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listCourses);
    }

    @PostMapping("/switch-enabled")
    public ResponseEntity<?> updateIsEnabled(@RequestParam(value = "course") Integer courseId, @RequestParam(value = "enabled") boolean isEnabled) {
        return ResponseEntity.ok(courseService.updateIsEnabled(courseId, isEnabled));
    }

    @PostMapping("/switch-published")
    public ResponseEntity<?> updateIsPublished(@RequestParam(value = "course") Integer courseId, @RequestParam(value = "published") boolean isPublished) {
        return ResponseEntity.ok(courseService.updateIsPublished(courseId, isPublished));
    }

    @PostMapping("/switch-finished")
    public ResponseEntity<?> updateIsFinished(@RequestParam(value = "course") Integer courseId, @RequestParam(value = "finished") boolean isFinished) {
        return ResponseEntity.ok(courseService.updateIsFinished(courseId, isFinished));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCourse(@RequestPart(value = "course") @Valid CoursesRequest coursesRequest, @RequestParam(value = "img") MultipartFile img) {
        CourseResponse courseResponse = courseService.create(coursesRequest, img);
        URI uri = URI.create("/api/courses/create/" + courseResponse.getId());

        return ResponseEntity.created(uri).body(courseResponse);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable(value = "id") Integer courseId) {
        return ResponseEntity.ok(courseService.get(courseId));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CourseResponse> updateCourse(@PathVariable(value = "id") Integer courseId, @RequestPart(value = "course") @Valid CoursesRequest coursesRequest, @RequestParam(value = "img", required = false) MultipartFile img) {
        return ResponseEntity.ok(courseService.update(courseId, coursesRequest, img));
    }
}
