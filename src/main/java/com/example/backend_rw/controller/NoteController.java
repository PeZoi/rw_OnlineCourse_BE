package com.example.backend_rw.controller;

import com.example.backend_rw.entity.dto.note.NoteRequest;
import com.example.backend_rw.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid NoteRequest noteRequest){
        return new ResponseEntity<>(noteService.createNote(noteRequest), HttpStatus.CREATED);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> list(@RequestParam(value = "course") Integer courseId,
                                  @RequestParam(value = "user") Integer userId){
        return ResponseEntity.ok(noteService.getAll(userId, courseId));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer noteId,
                                    @RequestParam(value = "content") String content){
        return ResponseEntity.ok(noteService.updateNote(noteId, content));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer noteId){
        return ResponseEntity.ok(noteService.deleteNote(noteId));
    }
}
