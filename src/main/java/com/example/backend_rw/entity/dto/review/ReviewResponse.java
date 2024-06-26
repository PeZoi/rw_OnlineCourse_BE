package com.example.backend_rw.entity.dto.review;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse {

    private Integer id;

    private String comment;

    private int rating;

    @JsonProperty("time_formatted")
    private String timeFormatted;

    @JsonProperty("user_id")
    private Integer userId;

    private String username;

    @JsonProperty("photo_user")
    private String photoUser;

    @JsonProperty("course_id")
    private Integer courseId;

    @JsonProperty("title_course")
    private String titleCourse;
}
