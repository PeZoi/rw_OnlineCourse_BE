package com.example.backend_rw.service.impl;

import com.example.backend_rw.entity.Courses;
import com.example.backend_rw.entity.Review;
import com.example.backend_rw.entity.User;
import com.example.backend_rw.entity.dto.review.ListReviewResponse;
import com.example.backend_rw.entity.dto.review.ReviewResponse;
import com.example.backend_rw.exception.CustomException;
import com.example.backend_rw.exception.NotFoundException;
import com.example.backend_rw.repository.CoursesRepository;
import com.example.backend_rw.repository.OrderRepository;
import com.example.backend_rw.repository.ReviewRepository;
import com.example.backend_rw.repository.UserRepository;
import com.example.backend_rw.service.ReviewService;
import com.example.backend_rw.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Transactional
@Service
public class ReviewServiceImpl implements ReviewService {
    private final ModelMapper modelMapper;
    private final CoursesRepository coursesRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public ReviewServiceImpl(ModelMapper modelMapper, CoursesRepository coursesRepository, ReviewRepository reviewRepository, UserRepository userRepository, OrderRepository orderRepository) {
        this.modelMapper = modelMapper;
        this.coursesRepository = coursesRepository;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public ListReviewResponse listAllByCourse(Integer courseId) {
        Courses courses = coursesRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course ID không tồn tại"));
        List<Review> listReview = reviewRepository.findByCourses(courses);
        return convertToListReviewResponse(listReview);
    }

    @Override
    public String checkCustomerToReviewed(Integer userId, Integer courseId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User ID không tồn tại"));

        Courses courses = coursesRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course ID không tồn tại"));

        // Kiểm tra user xem đã mua khoá học này chưa
        if(orderRepository.existsOrderByCoursesAndUser(courses, user)){
            // Kiểm tra xem user đã đánh giá khoá học này chưa
            if(reviewRepository.existsReviewByUserAndCourses(user, courses)){
                throw new CustomException("Bạn đã đánh giá khóa học này trước đó!", HttpStatus.CONFLICT);
            }
            return "Bạn vui lòng đánh giá khóa học này!";
        }
        throw new CustomException("Bạn đã đánh giá khóa học này trước đó!", HttpStatus.FORBIDDEN);
    }

    // Convert danh sách review thành DTO
    private ListReviewResponse convertToListReviewResponse(List<Review> listReviews) {
        ListReviewResponse listReviewResponse = new ListReviewResponse();
        listReviewResponse.setListResponses(
                listReviews.stream().map(this::convertToReviewResponse).toList()
        );
        int totalReview = listReviews.size();
        listReviewResponse.setTotalReview(totalReview);
        int totalRating = listReviews.stream().mapToInt(Review::getRating).sum();
        double averageReview = (double) totalRating / totalReview;
        averageReview = Math.round(averageReview * 10.0) / 10.0;
        listReviewResponse.setAverageReview(averageReview);
        return listReviewResponse;
    }

    // Convert review thành DTO
    private ReviewResponse convertToReviewResponse(Review savedReview) {
        ReviewResponse response = modelMapper.map(savedReview, ReviewResponse.class);
        response.setTimeFormatted(Utils.formatDuration(Duration.between(savedReview.getReviewTime(), Instant.now())));
        response.setUserId(savedReview.getUser().getId());
        response.setUsername(savedReview.getUser().getUsername());
        response.setPhotoUser(savedReview.getUser().getPhoto());
        response.setCourseId(savedReview.getCourses().getId());
        response.setTitleCourse(savedReview.getCourses().getTitle());
        return response;
    }
}
