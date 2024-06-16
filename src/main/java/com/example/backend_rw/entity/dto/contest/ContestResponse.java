package com.example.backend_rw.entity.dto.contest;

import com.example.backend_rw.entity.dto.quiz.QuizResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class ContestResponse {

    private Integer id;

    private String title;

    private int period;

    @JsonProperty("created_at")
    private Date createdAt;

    private boolean enabled;

    @JsonProperty("number_question")
    private int numberQuestion;

    @JsonProperty("number_of_joined")
    private int numberOfJoined;

    private List<QuizResponse> listQuizzes = new ArrayList<>();
}
