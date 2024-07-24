package com.example.backend_rw.service;

import com.example.backend_rw.entity.TextLesson;
import com.example.backend_rw.entity.dto.TextLessonDTO;

public interface TextLessonService {
    TextLesson create(TextLessonDTO textLessonDto);

    TextLesson update(TextLessonDTO textLessonDto);
}
