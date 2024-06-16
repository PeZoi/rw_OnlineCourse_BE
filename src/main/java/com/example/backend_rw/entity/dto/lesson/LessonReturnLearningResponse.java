package com.example.backend_rw.entity.dto.lesson;

import com.example.backend_rw.entity.LessonType;
import com.example.backend_rw.entity.dto.TextLessonDTO;
import com.example.backend_rw.entity.dto.VideoDTO;
import com.example.backend_rw.entity.dto.quiz.QuizReturnLearningPage;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.time.Instant;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LessonReturnLearningResponse {
    private Integer id;

    private String name;

    @JsonProperty("lesson_type")
    private LessonType lessonType;

    @JsonProperty("created_at")
    private Instant createdAt;

    @JsonProperty("chapter_id")
    private Integer chapterId;

    private VideoDTO video;

    private TextLessonDTO text;

    private List<QuizReturnLearningPage> quizList = new ArrayList<>();
}
