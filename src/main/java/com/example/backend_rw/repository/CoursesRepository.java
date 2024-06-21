package com.example.backend_rw.repository;

import com.example.backend_rw.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Integer> {
    List<Courses> findAllByCategoryId(int categoryId);
}
