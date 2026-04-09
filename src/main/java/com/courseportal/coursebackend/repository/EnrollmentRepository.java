package com.courseportal.coursebackend.repository;



import com.courseportal.coursebackend.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    Optional<Enrollment> findByUserAndCourse(User user, Course course);
    long countByCourse(Course course);
    List<Enrollment> findByUser(User user);
}