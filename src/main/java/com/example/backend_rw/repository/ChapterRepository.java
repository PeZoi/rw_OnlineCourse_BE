package com.example.backend_rw.repository;

import com.example.backend_rw.entity.Chapter;
import com.example.backend_rw.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Integer> {
    Chapter findChapterByNameAndCourse(String name, Courses course);
}
