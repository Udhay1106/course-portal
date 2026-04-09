package com.courseportal.coursebackend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "lesson_progress")
public class LessonProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Lesson lesson;

    private boolean completed;

    // getters & setters
}