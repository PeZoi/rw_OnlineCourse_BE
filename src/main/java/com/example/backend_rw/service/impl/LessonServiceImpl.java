package com.example.backend_rw.service.impl;

import com.example.backend_rw.entity.*;
import com.example.backend_rw.entity.dto.lesson.LessonRequest;
import com.example.backend_rw.entity.dto.lesson.LessonResponse;
import com.example.backend_rw.entity.dto.quiz.QuizRequest;
import com.example.backend_rw.exception.CustomException;
import com.example.backend_rw.exception.NotFoundException;
import com.example.backend_rw.repository.ChapterRepository;
import com.example.backend_rw.repository.LessonRepository;
import com.example.backend_rw.repository.OrderRepository;
import com.example.backend_rw.repository.TrackCourseRepository;
import com.example.backend_rw.service.LessonService;
import com.example.backend_rw.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Transactional
@Service
public class LessonServiceImpl implements LessonService {
    private final ModelMapper modelMapper;
    private final LessonRepository lessonRepository;
    private final ChapterRepository chapterRepository;
    private final OrderRepository orderRepository;
    private final TrackCourseRepository trackCourseRepository;

    public LessonServiceImpl(ModelMapper modelMapper, LessonRepository lessonRepository, ChapterRepository chapterRepository, OrderRepository orderRepository, TrackCourseRepository trackCourseRepository) {
        this.modelMapper = modelMapper;
        this.lessonRepository = lessonRepository;
        this.chapterRepository = chapterRepository;
        this.orderRepository = orderRepository;
        this.trackCourseRepository = trackCourseRepository;
    }

    @Override
    public Courses getCourse(Integer lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new NotFoundException("Lesson ID không tồn tại"));
        return lesson.getChapter().getCourse();
    }

    @Override
    public LessonResponse create(LessonRequest lessonRequest, Video video, TextLesson textLesson, QuizRequest[] quizRequest) {
        // Kiểm tra xem có tồn tại chapter đó không
        Chapter chapter = chapterRepository.findById(lessonRequest.getChapterId()).orElseThrow(() -> new NotFoundException("Chapter ID không tồn tại"));

        if (lessonRepository.existsLessonByNameAndChapter(lessonRequest.getName(), chapter)) {
            throw new CustomException("Tên bài học đã từng tồn tại trong chương này!", HttpStatus.CONFLICT);
        }


        // Set các thông tin của lesson
        Lesson lesson = new Lesson();
        lesson.setName(lessonRequest.getName());
        lesson.setCreatedAt(Instant.now());
        lesson.setLessonType(LessonType.valueOf(lessonRequest.getLessonType()));
        lesson.setChapter(chapter);
        lesson.setVideo(video);
        lesson.setText(textLesson);
        lesson.setOrders(lessonRequest.getOrders());

        // Nếu lesson là bài quiz thì thực hiện chuyển đổi quizDTO thành entity để dễ xử lý
        if (lessonRequest.getLessonType().equals("QUIZ") && quizRequest != null) {
            for (QuizRequest quiz : quizRequest) {
                lesson.add(Utils.convertToQuizEntity(quiz));
            }
        }

        Lesson savedLesson = lessonRepository.save(lesson);

        Courses courses = savedLesson.getChapter().getCourse();
        // Chủ yếu để thêm track lesson cho người dùng nào mà đã mua khoá học này
        List<Order> listOrder = orderRepository.findAllByCourses(courses);
        if (!listOrder.isEmpty()) {
            for (Order order : listOrder) {
                TrackCourse trackCourse = new TrackCourse();
                trackCourse.setUser(order.getUser());
                trackCourse.setCourses(order.getCourses());
                trackCourse.setChapter(savedLesson.getChapter());
                trackCourse.setLesson(savedLesson);

                TrackCourse trackCourseCurrentLesson = trackCourseRepository.findTrackCoursesByCurrent(courses.getId(), order.getUser().getId());
                if (trackCourseCurrentLesson != null) {
                    int chapterCurrentLessIdOrder = trackCourseCurrentLesson.getChapter().getOrders();
                    int lessonCurrentLessIdOrder = trackCourseCurrentLesson.getLesson().getOrders();
                    if (chapterCurrentLessIdOrder > savedLesson.getChapter().getOrders()) {
                        trackCourse.setUnlock(true);
                    } else if (chapterCurrentLessIdOrder == savedLesson.getChapter().getOrders()) {
                        if (lessonCurrentLessIdOrder > savedLesson.getOrders()) {
                            trackCourse.setUnlock(true);
                        }
                    }
                } else {
                    trackCourse.setUnlock(true);
                    trackCourse.setCurrent(true);
                }
                trackCourseRepository.save(trackCourse);
            }
        }
        return convertToLessonResponse(savedLesson);
    }

    private LessonResponse convertToLessonResponse(Lesson lesson) {
        LessonResponse response = modelMapper.map(lesson, LessonResponse.class);
        response.setChapterId(lesson.getChapter().getId());

        return response;
    }
}
