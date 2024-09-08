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
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {
    //    Khi không tìm thấy user
    @ExceptionHandler(value = {UsernameNotFoundException.class, BadCredentialsException.class})
    public ResponseEntity<ResponseDetail<Object>> handleGlobalException(Exception exception) {
        ResponseDetail<Object> detailResponse = ResponseDetail.builder().status(HttpStatus.BAD_REQUEST.value()).error("Error!").message(exception.getMessage()).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(detailResponse);
    }

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ResponseDetail<Object>> handleCustomException(CustomException customException) {
        ResponseDetail<Object> detailResponse = ResponseDetail.builder().status(customException.getHttpStatus().value()).error("Custom Exception").message(customException.getMessage()).build();
        return ResponseEntity.status(customException.getHttpStatus()).body(detailResponse);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ResponseDetail<Object>> handleNotFoundException(NotFoundException notFoundException) {
        ResponseDetail<Object> detailResponse = ResponseDetail.builder().status(HttpStatus.NOT_FOUND.value()).error("Not Found Exception").message(notFoundException.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detailResponse);
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

    // Bắt lỗi khi endpoint không hợp lệ
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ResponseDetail<Object>> handleNotResourceFoundException(NoResourceFoundException noResourceFoundException) {
        ResponseDetail<Object> detailResponse = ResponseDetail.builder().status(HttpStatus.NOT_FOUND.value()).error(
                noResourceFoundException.getMessage()).message("URL invalid!").build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detailResponse);
    }
}
