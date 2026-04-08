package com.courseportal.coursebackend.controller;




import com.courseportal.coursebackend.dto.AuthRequest;
import com.courseportal.coursebackend.dto.AuthResponse;
import com.courseportal.coursebackend.model.User;
import com.courseportal.coursebackend.security.JwtUtil;
import com.courseportal.coursebackend.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    // ✅ Signup
    @PostMapping("/signup")
    public String register(@RequestBody AuthRequest request) {
        authService.register(request);
        return "User registered successfully";
    }

    // ✅ Login
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        User user = authService.login(request);



        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponse(token);
    }
}