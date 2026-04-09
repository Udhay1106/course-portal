package com.courseportal.coursebackend.service;



import com.courseportal.coursebackend.model.*;
import com.courseportal.coursebackend.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepo;
    private final CourseRepository courseRepo;
    private final UserRepository userRepo;

    public EnrollmentService(EnrollmentRepository enrollmentRepo,
                             CourseRepository courseRepo,
                             UserRepository userRepo) {
        this.enrollmentRepo = enrollmentRepo;
        this.courseRepo = courseRepo;
        this.userRepo = userRepo;
    }

    public Enrollment enroll(String email, Long courseId) {

        User user = userRepo.findByEmail(email).orElseThrow();
        Course course = courseRepo.findById(courseId).orElseThrow();

        if (enrollmentRepo.findByUserAndCourse(user, course).isPresent()) {
            throw new RuntimeException("Already enrolled");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setUser(user);
        enrollment.setCourse(course);
        enrollment.setProgress(0);
        enrollment.setCompleted(false);

        return enrollmentRepo.save(enrollment);
    }

    public Enrollment updateProgress(String email, Long courseId, int progress) {

        User user = userRepo.findByEmail(email).orElseThrow();
        Course course = courseRepo.findById(courseId).orElseThrow();

        Enrollment enrollment = enrollmentRepo
                .findByUserAndCourse(user, course)
                .orElseThrow(() -> new RuntimeException("Not enrolled"));

        enrollment.setProgress(progress);
        enrollment.setCompleted(progress == 100);

        return enrollmentRepo.save(enrollment);
    }
    public List<Enrollment> getUserEnrollments(String email) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return enrollmentRepo.findByUser(user);
    }
    public long getEnrollmentCount(Long courseId) {

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        return enrollmentRepo.countByCourse(course);
    }
}