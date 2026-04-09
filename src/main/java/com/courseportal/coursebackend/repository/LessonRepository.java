package com.courseportal.coursebackend.repository;



import com.courseportal.coursebackend.model.Lesson;
import com.courseportal.coursebackend.model.Module;
import com.courseportal.coursebackend.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findByModule(Module module);

    long countByModule_Course(Course course);
}