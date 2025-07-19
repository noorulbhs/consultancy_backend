package com.altrevo.consultancy.controller;

import com.altrevo.consultancy.dto.ApiResponse;
import com.altrevo.consultancy.dto.admin.CreateUserRequest;
import com.altrevo.consultancy.entity.User;
import com.altrevo.consultancy.service.AdminUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin/users")
public class AdminUserController {
    private final AdminUserService adminUserService;

    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(@Valid @RequestBody CreateUserRequest request) {
        ApiResponse<User> response = adminUserService.createUser(request);
        if (!response.getSuccess()) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
}
