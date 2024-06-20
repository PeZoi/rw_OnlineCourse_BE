package com.example.backend_rw.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailResponse<T> {
    private int status;
    private String error;
    private Object message;
    private T data;
}
