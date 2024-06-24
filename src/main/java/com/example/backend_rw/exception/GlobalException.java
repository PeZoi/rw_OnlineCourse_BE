package com.example.backend_rw.exception;

import com.example.backend_rw.entity.dto.ResponseDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {
    //    Khi không tìm thấy user
    @ExceptionHandler(value = {UsernameNotFoundException.class, BadCredentialsException.class})
    public ResponseEntity<ResponseDetail<Object>> handleFieldExists(Exception exception) {
        ResponseDetail<Object> detailResponse = ResponseDetail.builder().status(HttpStatus.BAD_REQUEST.value()).error(exception.getMessage()).message("Error!").build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(detailResponse);
    }

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ResponseDetail<Object>> handleCustomException(CustomException customException) {
        ResponseDetail<Object> detailResponse = ResponseDetail.builder().status(HttpStatus.BAD_REQUEST.value()).error(customException.getMessage()).message("Custom Exception").build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(detailResponse);
    }

    // Bắt các lỗi về validate
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDetail<Object>> handleValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();

        ResponseDetail<Object> res = new ResponseDetail<Object>();
        res.setStatus(HttpStatus.BAD_REQUEST.value());
        res.setError(ex.getBody().getDetail());

        // Sử dụng map chủ yếu để có 2 trường là key và value (lưu được field lôi và message lỗi)
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach((error) -> {
            String fieldName = error.getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        res.setMessage(errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }
}