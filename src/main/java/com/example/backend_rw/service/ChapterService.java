package com.example.backend_rw.service;

import com.example.backend_rw.entity.dto.chapter.ChapterDTO;

public interface ChapterService {
    ChapterDTO create(Integer courseId, ChapterDTO chapterDto);

    ChapterDTO update(Integer courseId, Integer chapterId, ChapterDTO chapterDto);

    String delete(Integer chapterId);
}
