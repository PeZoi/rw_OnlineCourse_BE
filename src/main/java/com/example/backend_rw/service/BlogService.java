package com.example.backend_rw.service;

import com.example.backend_rw.entity.dto.blog.BlogRequest;
import com.example.backend_rw.entity.dto.blog.BlogResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BlogService {
    BlogResponse save(BlogRequest blogRequest, MultipartFile img);
    List<BlogResponse> getAll();
    List<BlogResponse> getAllByUser(Integer userId);
    BlogResponse get(String slug);
    String view(Integer blogId);
    BlogResponse update(Integer blogId, BlogRequest blogRequest, MultipartFile img);
    String delete(Integer blogId);
    String checkAuthorOfBlog(Integer blogId, Integer userId);
    List<BlogResponse> search(String keyword);
}
