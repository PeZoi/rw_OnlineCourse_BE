package com.example.backend_rw.service;

import com.example.backend_rw.entity.Video;
import com.example.backend_rw.entity.dto.VideoDTO;
import org.springframework.web.multipart.MultipartFile;

public interface VideoService {
    Video save(VideoDTO videoDto, MultipartFile videoFile);

    Video update(VideoDTO videoDto, MultipartFile videoFile);
}
