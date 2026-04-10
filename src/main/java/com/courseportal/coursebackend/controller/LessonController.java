package com.courseportal.coursebackend.controller;



import com.courseportal.coursebackend.dto.LessonRequest;
import com.courseportal.coursebackend.model.Lesson;
import com.courseportal.coursebackend.service.LessonService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    private final LessonService service;

    public LessonController(LessonService service) {
        this.service = service;
    }

    // ✅ Complete lesson
    @PostMapping("/{lessonId}/complete")
    public String completeLesson(@PathVariable Long lessonId) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        service.completeLesson(email, lessonId);

        return "Lesson completed";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Lesson addLesson(@RequestBody LessonRequest request) {
        return service.addLesson(request);
    }

}