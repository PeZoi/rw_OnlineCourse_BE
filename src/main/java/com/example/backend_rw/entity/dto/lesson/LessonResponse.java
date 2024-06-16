package com.example.backend_rw.entity.dto.lesson;

import com.example.backend_rw.entity.LessonType;
import com.example.backend_rw.entity.dto.TextLessonDTO;
import com.example.backend_rw.entity.dto.VideoDTO;
import com.example.backend_rw.entity.dto.quiz.QuizResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"id", "name", "lesson_type", "created_at", "chapter_id", "video"})
public class LessonResponse {

    private Integer id;

    private String name;

    @JsonProperty("lesson_type")
    private LessonType lessonType;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("chapter_id")
    private Integer chapterId;

    private VideoDTO video;

    private TextLessonDTO text;

    private List<QuizResponse> quizList = new ArrayList<>();

    private int orders;
}
