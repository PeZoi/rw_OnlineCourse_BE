package com.example.backend_rw.repository;

import com.example.backend_rw.entity.Courses;
import com.example.backend_rw.entity.Review;
import com.example.backend_rw.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByCourses(Courses courses);
    boolean existsReviewByUserAndCourses(User user, Courses courses);
}
