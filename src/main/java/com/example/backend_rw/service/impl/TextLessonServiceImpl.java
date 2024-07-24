package com.example.backend_rw.service.impl;

import com.example.backend_rw.entity.TextLesson;
import com.example.backend_rw.entity.dto.TextLessonDTO;
import com.example.backend_rw.exception.NotFoundException;
import com.example.backend_rw.repository.TextLessonRepository;
import com.example.backend_rw.service.TextLessonService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class TextLessonServiceImpl implements TextLessonService {

    private final ModelMapper modelMapper;
    private final TextLessonRepository textLessonRepository;

    public TextLessonServiceImpl(ModelMapper modelMapper, TextLessonRepository textLessonRepository) {
        this.modelMapper = modelMapper;
        this.textLessonRepository = textLessonRepository;
    }

    @Override
    public TextLesson create(TextLessonDTO textLessonDto) {
        TextLesson lesson = modelMapper.map(textLessonDto, TextLesson.class);
        return textLessonRepository.save(lesson);
    }

    @Override
    public TextLesson update(TextLessonDTO textLessonDto) {
        TextLesson textLessonInDB = textLessonRepository.findById(textLessonDto.getId()).orElseThrow(() -> new NotFoundException("Text lesson ID không hợp lệ"));

        textLessonInDB.setContent(textLessonDto.getContent());
        return textLessonRepository.save(textLessonInDB);
    }
}
