package com.courseportal.coursebackend.controller;

import com.courseportal.coursebackend.model.User;
import com.courseportal.coursebackend.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserRepository userRepo;

    public AdminController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    // ✅ Make user ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/make-admin/{userId}")
    public String makeAdmin(@PathVariable Long userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setRole("ADMIN");

        userRepo.save(user);

        return "User promoted to ADMIN";
    }
}