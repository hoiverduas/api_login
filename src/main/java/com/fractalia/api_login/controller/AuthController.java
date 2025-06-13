package com.fractalia.api_login.controller;

import com.fractalia.api_login.dto.AuthenticatedUserResponse;
import com.fractalia.api_login.dto.LoginRequest;
import com.fractalia.api_login.dto.LoginResponse;
import com.fractalia.api_login.service.impl.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/me")
    public ResponseEntity<AuthenticatedUserResponse> getUserMe(@RequestHeader("Authorization") String bearerToken) {
        return ResponseEntity.ok(authService.getAuthenticatedUser(bearerToken));
    }


    @GetMapping("/users")
    public ResponseEntity<Map<String, Object>> getAllUsers() {
        return ResponseEntity.ok(authService.getAllUsers());
    }


}
