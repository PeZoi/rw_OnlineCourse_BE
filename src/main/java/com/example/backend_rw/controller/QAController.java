package com.example.backend_rw.controller;

import com.example.backend_rw.service.QAService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/qa")
public class QAController {
    private final QAService qaService;

    public QAController(QAService qaService) {
        this.qaService = qaService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> listAll(@RequestParam(value = "lesson") Integer lessonId){
        return ResponseEntity.ok(qaService.listAll(lessonId));
    }
}
