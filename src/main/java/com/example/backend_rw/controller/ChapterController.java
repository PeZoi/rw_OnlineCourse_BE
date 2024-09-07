package com.example.backend_rw.controller;

import com.example.backend_rw.entity.dto.chapter.ChapterDTO;
import com.example.backend_rw.service.ChapterService;
import com.example.backend_rw.utils.annotation.ApiMessage;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/chapter")
public class ChapterController {

    private final ChapterService chapterService;

    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @PostMapping("/create/{courseId}")
    @ApiMessage("Create chapter")
    public ResponseEntity<ChapterDTO> addChapter(@RequestBody @Valid ChapterDTO chapterDto, @PathVariable(value = "courseId") Integer courseId) {
        ChapterDTO response = chapterService.create(courseId, chapterDto);

        URI uri = URI.create("/api/chapters/" + response.getId());
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/update/{chapterId}/{courseId}")
    @ApiMessage("Update chapter")
    public ResponseEntity<ChapterDTO> updateChapter(@PathVariable(value = "courseId") Integer courseId, @PathVariable(value = "chapterId") Integer chapterId, @RequestBody @Valid ChapterDTO chapterDto) {
        return ResponseEntity.ok(chapterService.update(courseId, chapterId, chapterDto));
    }

    @DeleteMapping("/delete/{chapterId}")
    @ApiMessage("Delete chapter")
    public ResponseEntity<String> deleteChapter(@PathVariable(value = "chapterId") Integer chapterId) {
        return ResponseEntity.ok(chapterService.delete(chapterId));
    }
}
