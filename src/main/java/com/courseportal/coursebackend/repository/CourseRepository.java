package com.courseportal.coursebackend.repository;




import com.courseportal.coursebackend.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    // Custom Query Method
    List<Course> findByInstructorName(String instructorName);

    // Search by title (contains keyword)
    List<Course> findByTitleContaining(String keyword);

}