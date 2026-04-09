package com.courseportal.coursebackend.controller;

import com.courseportal.coursebackend.dto.ModuleRequest;
import com.courseportal.coursebackend.model.Module;
import com.courseportal.coursebackend.service.LessonService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/modules")
public class ModuleController {

    private final LessonService service;

    public ModuleController(LessonService service) {
        this.service = service;
    }

    @PostMapping
    public Module addModule(@RequestBody ModuleRequest request) {
        return service.addModule(request);
    }
}
