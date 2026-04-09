package com.courseportal.coursebackend.controller;



import com.courseportal.coursebackend.dto.EnrollmentRequest;
import com.courseportal.coursebackend.model.Course;
import com.courseportal.coursebackend.model.Enrollment;
import com.courseportal.coursebackend.service.EnrollmentService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService service;

    public EnrollmentController(EnrollmentService service) {
        this.service = service;
    }

    @PostMapping("/{courseId}")
    public Enrollment enroll(@PathVariable Long courseId) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return service.enroll(email, courseId);
    }

    @PostMapping("/{courseId}/progress")
    public Enrollment updateProgress(
            @PathVariable Long courseId,
            @RequestParam int progress) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return service.updateProgress(email, courseId, progress);
    }
    @GetMapping("/my-courses")
    public List<Enrollment> getMyEnrollments() {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return service.getUserEnrollments(email);
    }
    @PostMapping
    public Enrollment enroll(@RequestBody EnrollmentRequest request) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return service.enroll(email, request.getCourseId());
    }

}
