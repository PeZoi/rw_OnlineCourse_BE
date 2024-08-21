package com.example.backend_rw.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CertificateResponse {

    private Integer id;

    @JsonProperty("achieved_time")
    private Instant achievedTime;

    @JsonProperty("title_course")
    private String titleCourse;

    @JsonProperty("student_name")
    private String studentName;
}
