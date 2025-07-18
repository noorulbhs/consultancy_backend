package com.altrevo.consultancy.dto.admin;

import com.altrevo.consultancy.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateUserRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @NotNull
    private Role role;

    private Boolean isActive = true;
    private Boolean isEmailVerified = false;
    private String avatarUrl;
    private String phone;
}
