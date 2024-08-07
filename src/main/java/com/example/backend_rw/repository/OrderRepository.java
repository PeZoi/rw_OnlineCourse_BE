package com.example.backend_rw.repository;

import com.example.backend_rw.entity.Courses;
import com.example.backend_rw.entity.Order;
import com.example.backend_rw.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByUser(User user);

    boolean existsOrderByCoursesAndUser(Courses courses, User user);

    List<Order> findAllByCourses(Courses courses);
}
