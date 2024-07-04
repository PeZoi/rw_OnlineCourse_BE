package com.example.backend_rw.repository;

import com.example.backend_rw.entity.Certificate;
import com.example.backend_rw.entity.Courses;
import com.example.backend_rw.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Integer> {
    Certificate findByUserAndCourses(User user, Courses courses);
}
