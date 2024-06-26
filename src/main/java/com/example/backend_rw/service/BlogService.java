package com.example.backend_rw.service;

import com.example.backend_rw.entity.dto.blog.BlogResponse;

import java.util.List;

public interface BlogService {
    List<BlogResponse> getAll();
    List<BlogResponse> getAllByUser(Integer userId);
    BlogResponse get(String slug);
    String view(Integer blogId);
}
