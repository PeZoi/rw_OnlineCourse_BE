package com.example.backend_rw.service;

import com.example.backend_rw.entity.dto.blog.BlogResponse;

import java.util.List;

public interface BlogService {
    List<BlogResponse> getAll();
}
