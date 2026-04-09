package com.courseportal.coursebackend.controller;

import com.courseportal.coursebackend.dto.DashboardDTO;
import com.courseportal.coursebackend.service.LessonService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final LessonService lessonService;

    public UserController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping("/dashboard")
    public List<DashboardDTO> getDashboard() {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return lessonService.getDashboard(email);
    }
}