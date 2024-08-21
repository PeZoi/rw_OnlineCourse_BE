package com.example.backend_rw.controller;

import com.example.backend_rw.entity.dto.lesson.LessonRequestInQuiz;
import com.example.backend_rw.service.QuizService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/check-answer")
    public ResponseEntity<?> checkAnswerIsCorrect(@RequestBody @Valid LessonRequestInQuiz lessonRequestInQuiz){
        return ResponseEntity.ok(quizService.gradeOfQuiz(lessonRequestInQuiz));
    }
}