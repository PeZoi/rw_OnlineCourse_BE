package com.example.backend_rw.repository;

import com.example.backend_rw.entity.TextLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextLessonRepository extends JpaRepository<TextLesson, Integer> {

}
