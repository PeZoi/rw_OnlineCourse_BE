package com.example.backend_rw.service.impl;

import com.example.backend_rw.entity.Courses;
import com.example.backend_rw.entity.Order;
import com.example.backend_rw.entity.TrackCourse;
import com.example.backend_rw.entity.User;
import com.example.backend_rw.entity.dto.course.CourseReturnMyLearning;
import com.example.backend_rw.exception.NotFoundException;
import com.example.backend_rw.repository.CoursesRepository;
import com.example.backend_rw.repository.OrderRepository;
import com.example.backend_rw.repository.TrackCourseRepository;
import com.example.backend_rw.repository.UserRepository;
import com.example.backend_rw.service.LearningService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class LearningServiceImpl implements LearningService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final TrackCourseRepository trackCourseRepository;
    private final CoursesRepository coursesRepository;
    private final OrderRepository orderRepository;

    public LearningServiceImpl(ModelMapper modelMapper, UserRepository userRepository, TrackCourseRepository trackCourseRepository, CoursesRepository coursesRepository, OrderRepository orderRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.trackCourseRepository = trackCourseRepository;
        this.coursesRepository = coursesRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<CourseReturnMyLearning> listAllCourseRegisteredByCustomer(String email) {
        // Lấy user
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Email không tồn tại"));
        // Lấy danh sách order của user
        List<Order> listOrders = user.getListOrders();

        // Sử dụng Java 8 Streams để xử lý
        return listOrders.stream().map(order -> {
            Courses courses = order.getCourses();
            List<TrackCourse> listTrackCourses = trackCourseRepository.findAllByCoursesAndUser(courses, user);

            // Tính tổng số bài học và bài học đã hoàn thành
            int totalLesson = listTrackCourses.size();
            int totalLessonLearned = listTrackCourses.stream().filter(TrackCourse::isCompleted).toList().size();

            // Tính toán % tiến trình
            int process = (totalLesson > 0) ? Math.round((float) totalLessonLearned * 100 / totalLesson) : 0;

            CourseReturnMyLearning myLearning = modelMapper.map(courses, CourseReturnMyLearning.class);
            myLearning.setProcess(process);
            return myLearning;
        }).toList();
    }

    @Override
    public boolean isRegisterInThisCourse(String slug, String email) {
        if(email != null){
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Email không tồn tại"));

            Courses courses = coursesRepository.findBySlug(slug)
                    .orElseThrow(() -> new NotFoundException("Course slug không tồn tại"));

            return orderRepository.existsOrderByCoursesAndUser(courses, user);

        }
        return false;
    }
}
