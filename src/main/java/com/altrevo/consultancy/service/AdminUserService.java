package com.altrevo.consultancy.service;

import com.altrevo.consultancy.dto.ApiResponse;
import com.altrevo.consultancy.dto.admin.CreateUserRequest;
import com.altrevo.consultancy.entity.User;
import com.altrevo.consultancy.repository.UserInMemoryRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserService {
    private final UserInMemoryRepository userRepository = new UserInMemoryRepository();
    private final PasswordEncoder passwordEncoder;

    public AdminUserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public ApiResponse<User> createUser(CreateUserRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ApiResponse.error("User already exists");
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
        return ApiResponse.success("User created", user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

