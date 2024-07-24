package com.example.backend_rw.controller;

import com.example.backend_rw.entity.TextLesson;
import com.example.backend_rw.entity.Video;
import com.example.backend_rw.entity.dto.TextLessonDTO;
import com.example.backend_rw.entity.dto.VideoDTO;
import com.example.backend_rw.entity.dto.lesson.LessonRequest;
import com.example.backend_rw.entity.dto.lesson.LessonResponse;
import com.example.backend_rw.entity.dto.quiz.QuizRequest;
import com.example.backend_rw.service.LessonService;
import com.example.backend_rw.service.TextLessonService;
import com.example.backend_rw.service.VideoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    private final VideoService videoService;
    private final TextLessonService textLessonService;
    private final LessonService lessonService;

    public LessonController(VideoService videoService, TextLessonService textLessonService, LessonService lessonService) {
        this.videoService = videoService;
        this.textLessonService = textLessonService;
        this.lessonService = lessonService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createLesson(@RequestPart(value = "lesson") @Valid LessonRequest lessonRequest, @RequestParam(value = "video_upload", required = false) MultipartFile videoUpload, @RequestPart(value = "video", required = false) VideoDTO videoDto, @RequestPart(value = "text", required = false) @Valid TextLessonDTO textLessonDto, @RequestPart(value = "quizzes", required = false) @Valid QuizRequest[] quizRequest) {
        Video savedVideo = null;
        TextLesson savedText = null;
        switch (lessonRequest.getLessonType()) {
            case "VIDEO" -> savedVideo = videoService.save(videoDto, videoUpload);
            case "TEXT" -> savedText = textLessonService.create(textLessonDto);
        }

        return new ResponseEntity<>(lessonService.create(lessonRequest, savedVideo, savedText, quizRequest), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<LessonResponse> get(@PathVariable(value = "id") Integer lessonId) {
        return ResponseEntity.ok(lessonService.get(lessonId));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<LessonResponse> update(@PathVariable(value = "id") Integer lessonId, @RequestPart(value = "lesson") @Valid LessonRequest lessonRequest, @RequestParam(value = "video_upload", required = false) MultipartFile videoUpload, @RequestPart(value = "video", required = false) VideoDTO videoDto, @RequestPart(value = "text", required = false) @Valid TextLessonDTO textLessonDto, @RequestPart(value = "quizzes", required = false) @Valid QuizRequest[] quizRequest) {
        Video savedVideo = null;
        TextLesson savedText = null;
        switch (lessonRequest.getLessonType()) {
            case "VIDEO" -> savedVideo = videoService.update(videoDto, videoUpload);
            case "TEXT" -> savedText = textLessonService.update(textLessonDto);
        }
        return ResponseEntity.ok(lessonService.update(lessonId, lessonRequest, savedVideo, savedText, quizRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Integer lessonId) {
        return ResponseEntity.ok(lessonService.delete(lessonId));
    }
}
