package com.example.backend_rw.service.impl;

import com.example.backend_rw.entity.Certificate;
import com.example.backend_rw.entity.Courses;
import com.example.backend_rw.entity.User;
import com.example.backend_rw.entity.dto.CertificateResponse;
import com.example.backend_rw.exception.CustomException;
import com.example.backend_rw.repository.CertificateRepository;
import com.example.backend_rw.repository.UserRepository;
import com.example.backend_rw.service.CertificateService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Transactional
@Service
public class CertificateServiceImpl implements CertificateService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final CertificateRepository certificateRepository;

    public CertificateServiceImpl(ModelMapper modelMapper, UserRepository userRepository, CertificateRepository certificateRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.certificateRepository = certificateRepository;
    }

    @Override
    public CertificateResponse save(String email, Courses courses) {
        User user = userRepository.findByEmail(email).get();
        Certificate certificate = null;
        Certificate savedCertificate;
        if (courses.isFinished()) {
            certificate = new Certificate();
            certificate.setCourses(courses);
            certificate.setAchievedTime(Instant.now());
            certificate.setUser(user);

            savedCertificate = certificateRepository.save(certificate);
        } else {
            throw new CustomException("Khóa học nay chưa kết thúc. Vui lòng chờ cập nhật thêm bài học mới", HttpStatus.NO_CONTENT);
        }
        CertificateResponse response = modelMapper.map(savedCertificate, CertificateResponse.class);
        response.setStudentName(savedCertificate.getUser().getFullName());
        response.setTitleCourse(savedCertificate.getCourses().getTitle());
        return response;
    }
}
