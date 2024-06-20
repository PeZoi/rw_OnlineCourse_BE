package com.example.backend_rw.utils;

import com.example.backend_rw.entity.dto.DetailResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class FormatRestResponse implements ResponseBodyAdvice<Object> {
    private final ObjectMapper objectMapper;

    public FormatRestResponse(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        HttpServletResponse httpServletResponse = ((ServletServerHttpResponse) response).getServletResponse();
        int status = httpServletResponse.getStatus();

        DetailResponse<Object> detailResponse = new DetailResponse<>();
        detailResponse.setStatus(status);

        // Trường hợp thất bại (có lỗi gì đó)
        if (status >= 400) {
            return body;
        } else {
            // Trường hợp thành công
            detailResponse.setMessage("Successfully!");
            detailResponse.setData(body);
        }

        if (body instanceof String) {
            try {
                // Chuyển đổi đối tượng DetailResponse thành chuỗi JSON
                return objectMapper.writeValueAsString(detailResponse);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return detailResponse;
    }
}
