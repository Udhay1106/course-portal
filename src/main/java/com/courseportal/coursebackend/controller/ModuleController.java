package com.courseportal.coursebackend.controller;

import com.courseportal.coursebackend.dto.ModuleRequest;
import com.courseportal.coursebackend.model.Module;
import com.courseportal.coursebackend.service.LessonService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/modules")
public class ModuleController {

    private final LessonService service;

    public ModuleController(LessonService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Module addModule(@RequestBody ModuleRequest request) {
        return service.addModule(request);
    }

}
