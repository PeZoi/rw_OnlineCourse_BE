package com.example.backend_rw.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course_informations")
public class CourseInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String value;

    @Enumerated(EnumType.STRING)
    private InformationType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Courses course;

    public CourseInfo(String value, InformationType type, Courses course) {
        this.value = value;
        this.type = type;
        this.course = course;
    }
}
