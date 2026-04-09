package com.courseportal.coursebackend.service;

import com.courseportal.coursebackend.dto.CourseRequest;
import com.courseportal.coursebackend.model.Course;
import com.courseportal.coursebackend.repository.CourseRepository;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private CourseRepository courseRepository;

    @Autowired
    public void setCourseRepository(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }
    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new RuntimeException("Course not found with id: " + id);
        }
        courseRepository.deleteById(id);
    }
    // Create new course

//    public Course createCourse(Course course) {
//        return courseRepository.save(course);
//    }

    public Course createCourse(CourseRequest request) {

        if (request.getInstructorName() == null) {
            throw new RuntimeException("Instructor name is required");
        }

        Course course = new Course();

        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setInstructorName(request.getInstructorName()); // ✅ MUST ADD

        return courseRepository.save(course);
    }
}
