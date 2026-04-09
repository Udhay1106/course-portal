package com.courseportal.coursebackend.controller;



import com.courseportal.coursebackend.dto.CourseRequest;
import com.courseportal.coursebackend.dto.ModuleDTO;
import com.courseportal.coursebackend.model.Course;
import com.courseportal.coursebackend.service.CourseService;
import com.courseportal.coursebackend.service.EnrollmentService;
import com.courseportal.coursebackend.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin("*") // allow frontend calls
public class CourseController {

    private CourseService courseService;
    private EnrollmentService enrollmentService;
    private LessonService lessonService;
//    public CourseController(CourseService courseService) {
//        this.courseService = courseService;
//    }

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @Autowired
    public void setEnrollmentService(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }
    @Autowired
    public void setLessonService(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{courseId}/enrollments/count")
    public long getEnrollmentCount(@PathVariable Long courseId) {
        return enrollmentService.getEnrollmentCount(courseId);
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @GetMapping("/{courseId}/content")
    public List<ModuleDTO> getCourseContent(@PathVariable Long courseId) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return lessonService.getCourseContent(email, courseId);
    }

    @GetMapping("/{courseId}/progress")
    public int getProgress(@PathVariable Long courseId) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return lessonService.calculateProgress(email, courseId);
    }

    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return "Course deleted successfully with id: " + id;
    }

    @PostMapping
    public Course createCourse(@RequestBody CourseRequest course) {
        return courseService.createCourse(course);
    }
}