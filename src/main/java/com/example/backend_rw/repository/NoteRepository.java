package com.example.backend_rw.repository;

import com.example.backend_rw.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
    @Query("select n from Note n where n.lesson.chapter.course.id =?1 and n.user.id =?2")
    List<Note> listNoteByCoursesAndUser(Integer courseId, Integer userId);
}
