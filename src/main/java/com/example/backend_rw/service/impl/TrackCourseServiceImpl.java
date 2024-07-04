package com.example.backend_rw.service.impl;

import com.example.backend_rw.entity.*;
import com.example.backend_rw.entity.dto.lesson.LessonReturnLearningResponse;
import com.example.backend_rw.entity.dto.quiz.QuizReturnLearningPage;
import com.example.backend_rw.entity.dto.track.InfoCourseRegistered;
import com.example.backend_rw.entity.dto.track.TrackCourseResponse;
import com.example.backend_rw.exception.NotFoundException;
import com.example.backend_rw.repository.*;
import com.example.backend_rw.service.TrackCourseService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Transactional
@Service
public class TrackCourseServiceImpl implements TrackCourseService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final CoursesRepository coursesRepository;
    private final CertificateRepository certificateRepository;
    private final TrackCourseRepository trackCourseRepository;
    private final LessonRepository lessonRepository;

    public TrackCourseServiceImpl(ModelMapper modelMapper, UserRepository userRepository, CoursesRepository coursesRepository, CertificateRepository certificateRepository, TrackCourseRepository trackCourseRepository, LessonRepository lessonRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.coursesRepository = coursesRepository;
        this.certificateRepository = certificateRepository;
        this.trackCourseRepository = trackCourseRepository;
        this.lessonRepository = lessonRepository;
    }

    @Override
    public InfoCourseRegistered listTrackCourse(String email, String slug) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email không tồn tại"));

        Courses courses = coursesRepository.findBySlug(slug).orElseThrow(() -> new NotFoundException("Course slug không tồn tại"));

        Integer certificateId = null;
        Certificate certificate = certificateRepository.findByUserAndCourses(user, courses);
        if (certificate != null) {
            certificateId = certificate.getId();
        }

        int averageAchieved = 0;
        int totalLessonLearned = 0;
        int totalLesson = 0;

        List<TrackCourse> listTrackCourses = sortTrackCourse(courses, user);
        List<TrackCourseResponse> listTrackCoursesResponse = new ArrayList<>();
        for (TrackCourse trackCourse : listTrackCourses) {
            listTrackCoursesResponse.add(convertToTrackCourseResponse(trackCourse));
            if (trackCourse.isCompleted()) {
                ++totalLessonLearned;
            }
            ++totalLesson;
        }
        float percent = (float) (totalLessonLearned * 100) / totalLesson;
        averageAchieved = Math.round(percent);
        return new InfoCourseRegistered(listTrackCoursesResponse, averageAchieved, totalLessonLearned, certificateId);
    }

    @Override
    public LessonReturnLearningResponse getLesson(Integer lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new NotFoundException("Lesson ID không tồn tại"));

        LessonReturnLearningResponse lessonReturn = modelMapper.map(lesson, LessonReturnLearningResponse.class);
        if(lessonReturn.getLessonType().toString().equals("QUIZ")){
            int i = 0;
            for (QuizReturnLearningPage quiz : lessonReturn.getQuizList()){
                quiz.setOrder(++i);
                if (quiz.getQuizType().toString().equals("PERFORATE")){
                    quiz.setAnswerList(null);
                }
            }
        }
        return lessonReturn;
    }

    List<TrackCourse> sortTrackCourse(Courses courses, User user) {
        List<TrackCourse> listTrackCourses = new ArrayList<>();
        for (Chapter chapter : courses.getChapterList()) {
            List<TrackCourse> listTrackByChapter = trackCourseRepository.findTrackCourseByCoursesAndChapterAndUser(courses, chapter, user);
            listTrackByChapter.sort(Comparator.comparingInt(track -> track.getLesson().getOrders()));
            listTrackCourses.addAll(listTrackByChapter);
        }
        return listTrackCourses;
    }

    public TrackCourseResponse convertToTrackCourseResponse(TrackCourse trackCourse) {
        TrackCourseResponse response = modelMapper.map(trackCourse, TrackCourseResponse.class);
        response.setLessonId(trackCourse.getLesson().getId());
        return response;
    }
}
