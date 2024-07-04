package com.example.backend_rw.repository;

import com.example.backend_rw.entity.Chapter;
import com.example.backend_rw.entity.Courses;
import com.example.backend_rw.entity.TrackCourse;
import com.example.backend_rw.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackCourseRepository extends JpaRepository<TrackCourse, Integer> {
    List<TrackCourse> findAllByCoursesAndUser(Courses courses, User user);
    List<TrackCourse> findTrackCourseByCoursesAndChapterAndUser(Courses courses, Chapter chapter, User user);
}
