package com.courseportal.coursebackend.repository;



import com.courseportal.coursebackend.model.Course;
import com.courseportal.coursebackend.model.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserProgressRepository extends JpaRepository<UserProgress, Long> {

    // Get progress by course
    Optional<UserProgress> findByCourse(Course course);

    // Get all progress entries for a course
    List<UserProgress> findAllByCourse(Course course);
}