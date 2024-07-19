package com.example.backend_rw.service.impl;

import com.example.backend_rw.entity.*;
import com.example.backend_rw.entity.dto.chapter.ChapterReturnDetailResponse;
import com.example.backend_rw.entity.dto.course.CourseResponse;
import com.example.backend_rw.entity.dto.course.CourseReturnDetailPageResponse;
import com.example.backend_rw.entity.dto.course.CourseReturnHomePageResponse;
import com.example.backend_rw.entity.dto.course.CourseReturnSearch;
import com.example.backend_rw.entity.dto.lesson.LessonReturnDetailResponse;
import com.example.backend_rw.exception.NotFoundException;
import com.example.backend_rw.repository.CategoryRepository;
import com.example.backend_rw.repository.CoursesRepository;
import com.example.backend_rw.repository.LessonRepository;
import com.example.backend_rw.repository.VideoRepository;
import com.example.backend_rw.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class CourseServiceImpl implements CourseService {
    private final ModelMapper modelMapper;
    private final CoursesRepository coursesRepository;
    private final CategoryRepository categoryRepository;
    private final LessonRepository lessonRepository;
    private final VideoRepository videoRepository;

    public CourseServiceImpl(ModelMapper modelMapper, CoursesRepository coursesRepository, CategoryRepository categoryRepository, LessonRepository lessonRepository, VideoRepository videoRepository) {
        this.modelMapper = modelMapper;
        this.coursesRepository = coursesRepository;
        this.categoryRepository = categoryRepository;
        this.lessonRepository = lessonRepository;
        this.videoRepository = videoRepository;
    }

    @Override
    public List<CourseReturnHomePageResponse> getCourseIntoHomePage(Integer categoryId) {
        List<Courses> listCourses = null;

        if (categoryId == null) {
            listCourses = coursesRepository.findAll();
        } else {
            // Kiểm tra nếu categoryId không tồn tại thì báo lỗi
            Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("Course ID: " + categoryId + " không tồn tại"));

            listCourses = coursesRepository.findAllByCategoryId(categoryId);
        }

        // Map qua từng phần tử của listCourses, formatted lại dữ liệu
        return listCourses.stream().map(courses -> {
            // Mapper dữ liêu course qua dữ liệu muốn trả về
            CourseReturnHomePageResponse response = modelMapper.map(courses, CourseReturnHomePageResponse.class);
            // Tính toán các số liệu
            int totalReview = courses.getListReviews().size();
            int totalRating = courses.getListReviews().stream().mapToInt(Review::getRating).sum();
            double averageRating = (double) totalRating / totalReview;
            averageRating = Math.round(averageRating * 10.0) / 10.0;
            response.setTotalReview(totalReview);
            response.setAverageReview(averageRating);
            return response;
        }).toList();
    }

    @Override
    public CourseReturnDetailPageResponse getCourseDetail(String slug) {
        Courses course = coursesRepository.findBySlug(slug).orElseThrow(() -> new NotFoundException("Course slug không tồn tại"));

        CourseReturnDetailPageResponse response = modelMapper.map(course, CourseReturnDetailPageResponse.class);
        sortChapterAndLesson(response);
        return response;
    }

    @Override
    public List<CourseReturnSearch> listAllCourseByKeyword(String keyword) {
        List<Courses> listCourses = coursesRepository.search(keyword);

        return listCourses.stream().map(courses -> {
            CourseReturnSearch response = modelMapper.map(courses, CourseReturnSearch.class);
            int totalReview = courses.getListReviews().size();
            int totalRating = courses.getListReviews().stream().mapToInt(Review::getRating).sum();
            double averageRating = (double) totalRating / totalReview;
            averageRating = Math.round(averageRating * 10.0) / 10.0;
            response.setAverageReview(averageRating);
            return response;
        }).toList();
    }

    @Override
    public List<CourseResponse> getAll() {
        List<Courses> coursesList = coursesRepository.findAll();
        return coursesList.stream().map(this::convertToCourseResponse).toList();
    }

    @Override
    public String updateIsEnabled(Integer courseId, boolean isEnabled) {
        Courses courses = coursesRepository.findById(courseId).orElseThrow(() -> new NotFoundException("Courses ID không tồn tại"));

        coursesRepository.switchEnabled(courseId, isEnabled);

        return "SUCCESS";
    }

    @Override
    public String updateIsPublished(Integer courseId, boolean isPublished) {
        Courses courses = coursesRepository.findById(courseId).orElseThrow(() -> new NotFoundException("Courses ID không tồn tại"));

        coursesRepository.switchPublished(courseId, isPublished);
        return "SUCCESS";
    }

    @Override
    public String updateIsFinished(Integer courseId, boolean isFinished) {
        Courses courses = coursesRepository.findById(courseId).orElseThrow(() -> new NotFoundException("Courses ID không tồn tại"));

        coursesRepository.switchFinished(courseId, isFinished);
        return "SUCCESS";
    }

    private CourseResponse convertToCourseResponse(Courses course) {
        CourseResponse courseResponse = modelMapper.map(course, CourseResponse.class);
        int totalReview = course.getListReviews().size();
        int totalRating = course.getListReviews().stream().mapToInt(Review::getRating).sum();
        double averageRating = (double) totalRating / totalReview;
        averageRating = Math.round(averageRating * 10.0) / 10.0;
        courseResponse.setTotalReview(totalReview);
        courseResponse.setAverageReview(averageRating);
        courseResponse.setChapterList(null);
        courseResponse.setInfoList(null);
        return courseResponse;
    }

    // Hàm sắp xếp lại chapter và lesson theo order
    private void sortChapterAndLesson(CourseReturnDetailPageResponse response) {
        int totalLessonInCourse = 0;
        Duration duration = Duration.ZERO;

        // Lấy ra chapters của course (theo DTO)
        List<ChapterReturnDetailResponse> chapterList = response.getChapterList().stream().map(chapter -> modelMapper.map(chapter, ChapterReturnDetailResponse.class)).collect(Collectors.toList());
        // Set số lượng chapter
        response.setTotalChapter(chapterList.size());
        // sort lại chapter theo order
        chapterList.sort(Comparator.comparingInt(ChapterReturnDetailResponse::getOrders));
        int i = 1;
        for (ChapterReturnDetailResponse chapter : chapterList) {
            // Lây ra lessons của chapter đó
            List<LessonReturnDetailResponse> listLesson = chapter.getLessonList();
            // Sort lại lesson theo order
            listLesson.sort(Comparator.comparingInt(LessonReturnDetailResponse::getOrders));
            // Lặp lại lesson
            for (LessonReturnDetailResponse lesson : listLesson) {
                // Nếu mà type lesson đó là dạng video
                if (lesson.getLessonType().equals(LessonType.VIDEO)) {
                    Lesson lessonInDB = lessonRepository.findById(lesson.getId()).get();
                    Video video = videoRepository.findById(lessonInDB.getVideo().getId()).get();
                    // Gán thời lượng là = thời lượng video
                    lesson.setDuration(video.getDuration());

                    // Tính toán thời lượng video
                    duration = duration.plus(Duration.ofMinutes(video.getDuration().getMinute()).plusSeconds(video.getDuration().getSecond()));
                } else {
                    // Nếu type lesson không phải là dạng video thì mặc định sẽ gán duration là 1 phút
                    LocalTime time = LocalTime.of(0, 1, 0);
                    lesson.setDuration(time);
                    duration = duration.plus(Duration.ofMinutes(1));
                }
                lesson.setOrders(i);
                ++i;
            }
            // Gán tổng số bài học của 1 chapter
            totalLessonInCourse += listLesson.size();
            chapter.setTotalLesson(listLesson.size());
        }

        long hours = duration.toHours(); // Lấy tổng giờ
        long minutes = duration.toMinutes(); // Lấy tổng số phút
        long seconds = duration.minusMinutes(minutes).getSeconds(); // Lấy số giây còn lại sau khi lấy tổng số phút

        // Tạo LocalTime từ số phút và số giây
        LocalTime localTime = LocalTime.of((int) hours, (int) minutes, (int) seconds);
        response.setTotalTime(localTime);
        response.setTotalLesson(totalLessonInCourse);
    }
}
