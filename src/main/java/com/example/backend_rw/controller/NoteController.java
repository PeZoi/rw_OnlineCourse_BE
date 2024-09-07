package com.example.backend_rw.controller;

import com.example.backend_rw.entity.dto.note.NoteRequest;
import com.example.backend_rw.service.NoteService;
import com.example.backend_rw.utils.annotation.ApiMessage;
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
    @ApiMessage("Create a note")
    public ResponseEntity<?> create(@RequestBody @Valid NoteRequest noteRequest){
        return new ResponseEntity<>(noteService.createNote(noteRequest), HttpStatus.CREATED);
    }

    @GetMapping("/get-all")
    @ApiMessage("List all notes")
    public ResponseEntity<?> list(@RequestParam(value = "course") Integer courseId,
                                  @RequestParam(value = "user") Integer userId){
        return ResponseEntity.ok(noteService.getAll(userId, courseId));
    }

    @PutMapping("/update/{id}")
    @ApiMessage("Update the note")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer noteId,
                                    @RequestParam(value = "content") String content){
        return ResponseEntity.ok(noteService.updateNote(noteId, content));
    }

    @DeleteMapping("/delete/{id}")
    @ApiMessage("Delete the note")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer noteId){
        return ResponseEntity.ok(noteService.deleteNote(noteId));
    }
}
