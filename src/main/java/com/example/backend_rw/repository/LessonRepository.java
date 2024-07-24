package com.example.backend_rw.repository;

import com.example.backend_rw.entity.Chapter;
import com.example.backend_rw.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    boolean existsLessonByNameAndChapter(String name, Chapter chapter);

    Lesson findLessonByNameAndChapter(String name, Chapter chapter);

}
