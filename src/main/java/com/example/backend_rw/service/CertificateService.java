package com.example.backend_rw.service;

import com.example.backend_rw.entity.Courses;
import com.example.backend_rw.entity.dto.CertificateResponse;

public interface CertificateService {
    CertificateResponse save(String email, Courses courses);
}
