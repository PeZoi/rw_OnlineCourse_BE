package com.example.backend_rw.exception;

import com.example.backend_rw.entity.dto.DetailResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    //    Khi 1 field nào đó không tồn tại
    @ExceptionHandler(value = FieldExistException.class)
    public ResponseEntity<DetailResponse<Object>> handleFieldExists(FieldExistException fieldExistException) {
        DetailResponse<Object> detailResponse = DetailResponse.builder().status(HttpStatus.BAD_REQUEST.value()).error(fieldExistException.getMessage()).message("FieldExistException").build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(detailResponse);
    }

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<DetailResponse<Object>> handleCustomException(CustomException customException) {
        DetailResponse<Object> detailResponse = DetailResponse.builder().status(HttpStatus.BAD_REQUEST.value()).error(customException.getMessage()).message("CustomException").build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(detailResponse);
    }
}
