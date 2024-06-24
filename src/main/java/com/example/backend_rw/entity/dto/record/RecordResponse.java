package com.example.backend_rw.entity.dto.record;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecordResponse {

    private Integer id;

    private String username;

    @JsonProperty("user_id")
    private Integer userId;

    @JsonProperty("title_contest")
    private String titleContest;

    @JsonProperty("contest_id")
    private Integer contestId;

    @JsonProperty("joined_at")
    private Instant joinedAt;

    private float grade;

    private int period;

    @JsonProperty("total_quizzes")
    private float totalQuizzes;

    @JsonProperty("total_quiz_is_correct")
    private float totalQuizIsCorrect;
}
