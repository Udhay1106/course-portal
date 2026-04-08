package com.courseportal.coursebackend.controller;


import com.courseportal.coursebackend.model.UserProgress;
import com.courseportal.coursebackend.service.UserProgressService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/progress")
@CrossOrigin("*")
public class UserProgressController {

    private final UserProgressService service;

    public UserProgressController(UserProgressService service) {
        this.service = service;
    }


    @PostMapping("/{courseId}")
    public UserProgress updateProgress(
            @PathVariable Long courseId,
            @RequestParam int progress) {

        return service.updateProgress(courseId, progress);
    }


    @GetMapping("/{courseId}")
    public UserProgress getProgress(@PathVariable Long courseId) {
        return service.getProgress(courseId);
    }
}