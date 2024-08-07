package com.example.backend_rw.controller;

import com.example.backend_rw.entity.dto.qa.QARequest;
import com.example.backend_rw.service.QAService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid QARequest qaRequest){
        return new ResponseEntity<>(qaService.createQA(qaRequest), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer qaId,
                                    @RequestParam(value = "content") String content){
        return ResponseEntity.ok(qaService.updateQA(qaId, content));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer qaId){
        return ResponseEntity.ok(qaService.deleteQA(qaId));
    }
}
