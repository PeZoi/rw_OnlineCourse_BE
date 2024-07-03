package com.example.backend_rw.service.impl;

import com.example.backend_rw.entity.*;
import com.example.backend_rw.entity.dto.order.OrderResponse;
import com.example.backend_rw.repository.CoursesRepository;
import com.example.backend_rw.repository.OrderRepository;
import com.example.backend_rw.repository.TrackCourseRepository;
import com.example.backend_rw.repository.UserRepository;
import com.example.backend_rw.service.OrderService;
import com.example.backend_rw.utils.Constant;
import com.example.backend_rw.utils.EmailUtil;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {
    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CoursesRepository coursesRepository;
    private final TrackCourseRepository trackCourseRepository;
    private final EmailUtil emailUtil;

    public OrderServiceImpl(ModelMapper modelMapper, OrderRepository orderRepository, UserRepository userRepository, CoursesRepository coursesRepository, TrackCourseRepository trackCourseRepository, EmailUtil emailUtil) {
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.coursesRepository = coursesRepository;
        this.trackCourseRepository = trackCourseRepository;
        this.emailUtil = emailUtil;
    }

    @Override
    public List<OrderResponse> getAllByUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User ID không tồn tại"));

        List<Order> listOrders = orderRepository.findAllByUser(user);
        return listOrders.stream().map(this::convertToOrderResponse).toList();
    }

    @Override
    public void createOrder(User user, Courses courses, int totalPrice) {

        // Thông tin đơn hàng
        Order order = new Order();
        order.setUser(user);
        order.setCreatedTime(Instant.now());
        order.setCourses(courses);
        order.setTotalPrice(totalPrice);

        Order savedOrder = orderRepository.save(order);
        emailUtil.sendEmailForOrder(Constant.SUBJECT_ORDER, Constant.CONTENT_ORDER, savedOrder);

        int totalStudent = courses.getStudentCount();
        courses.setStudentCount(totalStudent + 1);
        coursesRepository.save(courses);

        for (Chapter chapter : courses.getChapterList()) {
            for (Lesson lesson : chapter.getLessonList()) {
                TrackCourse trackCourse = new TrackCourse();
                trackCourse.setCourses(courses);
                trackCourse.setChapter(chapter);
                trackCourse.setLesson(lesson);
                trackCourse.setUser(user);
                trackCourse.setCompleted(false);
                if (chapter.getOrders() == 0 && lesson.getOrders() == 1) {
                    trackCourse.setUnlock(true);
                    trackCourse.setCurrent(true);
                }

                trackCourseRepository.save(trackCourse);
            }
        }
    }

    private OrderResponse convertToOrderResponse(Order order) {
        OrderResponse orderResponse = modelMapper.map(order, OrderResponse.class);
        orderResponse.setCourseName(order.getCourses().getTitle());
        orderResponse.setCustomerName(order.getUser().getFullName());
        return orderResponse;
    }
}
