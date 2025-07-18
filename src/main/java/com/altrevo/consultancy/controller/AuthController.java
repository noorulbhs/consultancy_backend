package com.altrevo.consultancy.controller;

import com.altrevo.consultancy.dto.ApiResponse;
import com.altrevo.consultancy.dto.auth.LoginRequest;
import com.altrevo.consultancy.dto.auth.LoginResponse;
import com.altrevo.consultancy.dto.auth.RefreshTokenRequest;
import com.altrevo.consultancy.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Admin authentication endpoints")
public class AuthController {
    
    private final AuthService authService;
    
    @PostMapping("/login")
    @Operation(summary = "Admin login", description = "Authenticate admin user and get JWT token")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(ApiResponse.success("Login successful", response));
    }
    
    @PostMapping("/refresh")
    @Operation(summary = "Refresh token", description = "Refresh JWT token using refresh token")
    public ResponseEntity<ApiResponse<LoginResponse>> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        LoginResponse response = authService.refreshToken(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
    
    @PostMapping("/logout")
    @Operation(summary = "Logout", description = "Logout user and invalidate tokens")
    public ResponseEntity<ApiResponse<String>> logout(@Valid @RequestBody RefreshTokenRequest request) {
        authService.logout(request.getRefreshToken());
        return ResponseEntity.ok(ApiResponse.success("Logout successful", null));
    }
    
    @PostMapping("/test-password")
    @Operation(summary = "Test password encoding", description = "Test endpoint to check password encoding")
    public ResponseEntity<String> testPassword() {
        org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder encoder = 
            new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
        
        String rawPassword = "password123";
        String currentHash = "$2a$10$6K8V2VJfGCIV5VBJGgH8eOjLYxLLMoD7.5qV1qGYQOjJGxgOKLKMm";
        
        boolean matches = encoder.matches(rawPassword, currentHash);
        String newHash = encoder.encode(rawPassword);
        
        String result = String.format(
            "Raw password: %s\nCurrent hash: %s\nMatches: %s\nNew hash: %s",
            rawPassword, currentHash, matches, newHash
        );
        
        return ResponseEntity.ok(result);
    }
}
