package com.courseportal.coursebackend.service;



import com.courseportal.coursebackend.model.Course;
import com.courseportal.coursebackend.model.UserProgress;
import com.courseportal.coursebackend.repository.CourseRepository;
import com.courseportal.coursebackend.repository.UserProgressRepository;
import org.springframework.stereotype.Service;

@Service
public class UserProgressService {

    private final UserProgressRepository progressRepository;
    private final CourseRepository courseRepository;

    public UserProgressService(UserProgressRepository progressRepository,
                               CourseRepository courseRepository) {
        this.progressRepository = progressRepository;
        this.courseRepository = courseRepository;
    }

    // Update or create progress
    public UserProgress updateProgress(Long courseId, int progress) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        UserProgress userProgress = progressRepository.findByCourse(course)
                .orElse(new UserProgress());

        userProgress.setCourse(course);
        userProgress.setProgressPercentage(progress);
        userProgress.setCompleted(progress == 100);

        return progressRepository.save(userProgress);
    }

    // Get progress by course
    public UserProgress getProgress(Long courseId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        return progressRepository.findByCourse(course)
                .orElse(null);
    }
}