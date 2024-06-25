package com.example.backend_rw.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.backend_rw.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Component
public class UploadFile {
    @Autowired
    private Cloudinary cloudinary;

    public String uploadFileOnCloudinary(MultipartFile file) {
        try {
            Map r = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
            return (String) r.get("secure_url");
        } catch (IOException e) {
            throw new CustomException("Tải file lên cloud không thành công!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void deleteImageInCloudinary(String url) {
        try {
            int lastSlashIndex = url.lastIndexOf('/');
            int lastDotIndex = url.lastIndexOf('.');
            String fileName = url.substring(lastSlashIndex + 1, lastDotIndex);
            if (!fileName.equals("ooozzfj7t7p1zokgonni")) {
                cloudinary.uploader().destroy(fileName, ObjectUtils.asMap("resource_type", "image"));
            }
        } catch (IOException e) {
            throw new CustomException("Xoá ảnh thất bại!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void deleteVideoInCloudinary(String url) {
        try {
            int lastSlashIndex = url.lastIndexOf('/');
            int lastDotIndex = url.lastIndexOf('.');
            String fileName = url.substring(lastSlashIndex + 1, lastDotIndex);
            cloudinary.uploader().destroy(fileName, ObjectUtils.asMap("resource_type", "video"));
        } catch (IOException e) {
            throw new CustomException("Xoá video thất bại!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
