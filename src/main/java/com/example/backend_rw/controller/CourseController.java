package com.example.backend_rw.controller;

import com.example.backend_rw.entity.dto.course.CourseResponse;
import com.example.backend_rw.entity.dto.course.CourseReturnHomePageResponse;
import com.example.backend_rw.entity.dto.course.CourseReturnSearch;
import com.example.backend_rw.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/home-page")
    public ResponseEntity<List<CourseReturnHomePageResponse>> getCourseReturnHomePage(@RequestParam(value = "categoryId", required = false) Integer categoryId){
        List<CourseReturnHomePageResponse> listCourses = courseService.getCourseIntoHomePage(categoryId);
        if(listCourses.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listCourses);
    }

    @GetMapping("/list-all")
    public ResponseEntity<List<CourseResponse>> listAllCourses(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "categoryId", required = false) Integer categoryId
    ){
        List<CourseResponse> courseResponses = courseService.getAll();
        if(courseResponses.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(courseResponses);
    }

    @GetMapping("/get-detail/{slug}")
    public ResponseEntity<?> getCourseDetailById(@PathVariable(value = "slug") String slug){
        return ResponseEntity.ok(courseService.getCourseDetail(slug));
    }
    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(value = "keyword") String keyword){
        List<CourseReturnSearch> listCourses = courseService.listAllCourseByKeyword(keyword);
        if(listCourses.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listCourses);
    }
}
