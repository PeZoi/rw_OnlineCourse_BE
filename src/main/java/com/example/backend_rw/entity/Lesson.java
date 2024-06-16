package com.example.backend_rw.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lesson")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    private LessonType lessonType;

    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "video_id")
    private Video video;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "text_id")
    private TextLesson text;

    private int orders;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Quiz> quizList = new ArrayList<>();

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrackCourse> listTrackCourse = new ArrayList<>();

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Note> listNotes = new ArrayList<>();

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QA> listQAs = new ArrayList<>();

    public void add(Quiz quiz){
        this.quizList.add(new Quiz(quiz, this));
    }

    public void setQuizList(List<Quiz> quizList) {
        if(quizList != null && !quizList.isEmpty()){
            this.quizList.clear();
            this.quizList.addAll(quizList);
        }
    }
}
