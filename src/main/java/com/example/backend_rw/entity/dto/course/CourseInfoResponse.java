package com.example.backend_rw.entity.dto.course;

import com.example.backend_rw.entity.InformationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseInfoResponse {
    private Integer id;
    private String value;
    private InformationType type;
}
