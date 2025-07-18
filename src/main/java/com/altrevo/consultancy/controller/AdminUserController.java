package com.altrevo.consultancy.controller;

import com.altrevo.consultancy.dto.ApiResponse;
import com.altrevo.consultancy.dto.admin.CreateUserRequest;
import com.altrevo.consultancy.entity.User;
import com.altrevo.consultancy.enums.Role;
import com.altrevo.consultancy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class AdminUserController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(@Valid @RequestBody CreateUserRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(ApiResponse.error("User already exists"));
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setIsActive(request.getIsActive());
        user.setIsEmailVerified(request.getIsEmailVerified());
        user.setAvatarUrl(request.getAvatarUrl());
        user.setPhone(request.getPhone());
        userRepository.save(user);
        return ResponseEntity.ok(ApiResponse.success("User created", user));
    }
}
