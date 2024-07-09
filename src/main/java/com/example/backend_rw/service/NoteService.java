package com.example.backend_rw.service;

import com.example.backend_rw.entity.dto.note.NoteRequest;
import com.example.backend_rw.entity.dto.note.NoteResponse;

import java.util.List;

public interface NoteService {
    NoteResponse createNote(NoteRequest noteRequest);
    List<NoteResponse> getAll(Integer userId, Integer courseId);
    NoteResponse updateNote(Integer noteId, String content);
    String deleteNote(Integer noteId);
}
