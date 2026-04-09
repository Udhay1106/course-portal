package com.courseportal.coursebackend.repository;


import com.courseportal.coursebackend.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LessonProgressRepository extends JpaRepository<LessonProgress, Long> {

    Optional<LessonProgress> findByUserAndLesson(User user, Lesson lesson);

    long countByUserAndLesson_Module_Course(User user, Course course);
}