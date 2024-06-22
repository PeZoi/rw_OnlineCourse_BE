package com.example.backend_rw.service.impl;

import com.example.backend_rw.entity.Blog;
import com.example.backend_rw.entity.dto.blog.BlogResponse;
import com.example.backend_rw.repository.BlogRepository;
import com.example.backend_rw.service.BlogService;
import com.example.backend_rw.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Transactional
@Service
public class BlogServiceImpl implements BlogService {
    private final ModelMapper modelMapper;
    private final BlogRepository blogRepository;

    public BlogServiceImpl(ModelMapper modelMapper, BlogRepository blogRepository) {
        this.modelMapper = modelMapper;
        this.blogRepository = blogRepository;
    }

    @Override
    public List<BlogResponse> getAll() {
        return blogRepository.findAll().stream().map(this::convertToResponse).toList();
    }

    private BlogResponse convertToResponse(Blog savedBlog) {
        BlogResponse response = modelMapper.map(savedBlog, BlogResponse.class);
        Instant now = Instant.now();
        response.setCreatedAtFormat(Utils.formatDuration(Duration.between(savedBlog.getCreatedAt(), now)));
        response.setUsername(savedBlog.getUser().getUsername());
        response.setAvatarUser(savedBlog.getUser().getPhoto());
        response.setCreatedAt(savedBlog.getCreatedAt().toString());

        return response;
    }
}
