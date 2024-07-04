package com.example.backend_rw.controller;

import com.example.backend_rw.entity.dto.track.InfoCourseRegistered;
import com.example.backend_rw.service.TrackCourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/track-course")
public class TrackCourseController {
    private final TrackCourseService trackCourseService;

    public TrackCourseController(TrackCourseService trackCourseService) {
        this.trackCourseService = trackCourseService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<InfoCourseRegistered> getAll(@RequestParam(value = "email") String email, @RequestParam(value = "slug") String slug) {
        return ResponseEntity.ok(trackCourseService.listTrackCourse(email, slug));
    }

    @GetMapping("/get-lesson")
    public ResponseEntity<?> learningLesson(@RequestParam(value = "lesson") Integer lessonId) {
        return ResponseEntity.ok(trackCourseService.getLesson(lessonId));
    }
}
