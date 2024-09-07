package com.example.backend_rw.controller;

import com.example.backend_rw.entity.Courses;
import com.example.backend_rw.entity.dto.track.InfoCourseRegistered;
import com.example.backend_rw.service.CertificateService;
import com.example.backend_rw.service.LessonService;
import com.example.backend_rw.service.TrackCourseService;
import com.example.backend_rw.utils.annotation.ApiMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/track-course")
public class TrackCourseController {
    private final TrackCourseService trackCourseService;
    private final LessonService lessonService;
    private final CertificateService certificateService;

    public TrackCourseController(TrackCourseService trackCourseService, LessonService lessonService, CertificateService certificateService) {
        this.trackCourseService = trackCourseService;
        this.lessonService = lessonService;
        this.certificateService = certificateService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<InfoCourseRegistered> getAll(@RequestParam(value = "email") String email, @RequestParam(value = "slug") String slug) {
        return ResponseEntity.ok(trackCourseService.listTrackCourse(email, slug));
    }

    @GetMapping("/get-lesson")
    public ResponseEntity<?> learningLesson(@RequestParam(value = "lesson") Integer lessonId) {
        return ResponseEntity.ok(trackCourseService.getLesson(lessonId));
    }

    @PostMapping("/confirm-done")
    public ResponseEntity<?> doneLesson(@RequestParam(value = "email") String email, @RequestParam(value = "lesson") Integer lessonId) {
        Integer lessonIdNext = trackCourseService.confirmLessonLearned(email, lessonId);
        Courses courses = lessonService.getCourse(lessonId);
        if (lessonIdNext != -1) {
            return ResponseEntity.ok("CONTINUE");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(certificateService.save(email, courses));
        }
    }
}
