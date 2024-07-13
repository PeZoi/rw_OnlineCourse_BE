package com.example.backend_rw.repository;

import com.example.backend_rw.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Integer> {
    List<Courses> findAllByCategoryId(int categoryId);
    Optional<Courses> findBySlug(String slug);
    @Query("select c from Courses c where c.isEnabled = true and concat(c.title, ' ', c.category.name) like %?1% ")
    List<Courses> search(String keyword);
}
