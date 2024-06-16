package com.example.backend_rw.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VideoDTO {

    private Integer id;

    private String url;

    private LocalTime duration;

    private String description;
}
