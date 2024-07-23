package com.example.backend_rw.service.impl;

import com.example.backend_rw.entity.Video;
import com.example.backend_rw.entity.dto.VideoDTO;
import com.example.backend_rw.exception.CustomException;
import com.example.backend_rw.repository.VideoRepository;
import com.example.backend_rw.service.VideoService;
import com.example.backend_rw.utils.UploadFile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ws.schild.jave.EncoderException;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.info.MultimediaInfo;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalTime;

@Transactional
@Service
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;
    private final UploadFile uploadFile;

    public VideoServiceImpl(VideoRepository videoRepository, UploadFile uploadFile) {
        this.videoRepository = videoRepository;
        this.uploadFile = uploadFile;
    }

    @Override
    public Video save(VideoDTO videoDto, MultipartFile videoFile) {
        Video video = new Video();
        String url = uploadFile.uploadFileOnCloudinary(videoFile);
        video.setUrl(url);

        LocalTime duration = getDurationVideo(url);
        video.setDuration(duration);

        video.setDescription(videoDto.getDescription());

        return videoRepository.save(video);
    }

    private LocalTime getDurationVideo(String url) {
        try {
            URL url1 = new URL(url);
            MultimediaObject multimediaObject = new MultimediaObject(url1);
            MultimediaInfo multimediaInfo = multimediaObject.getInfo();

            long minutes = (multimediaInfo.getDuration() / 1000) / 60;
            long seconds = (multimediaInfo.getDuration() / 1000) % 60;

            return LocalTime.of(0, (int) minutes, (int) seconds);
        } catch (MalformedURLException | EncoderException e) {
            throw new CustomException("Link video không hợp lệ", HttpStatus.BAD_REQUEST);
        }
    }
}
