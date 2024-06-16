package com.example.backend_rw.entity.dto.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseReturnMyLearning {

    private Integer id;

    private String title;

    private String slug;

    private String thumbnail;

    private int process;
}
