package com.altrevo.consultancy.controller;

import com.altrevo.consultancy.entity.User;
import com.altrevo.consultancy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/debug")
@RequiredArgsConstructor
public class DebugController {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    
    @GetMapping("/test-password")
    public String testPassword() {
        String rawPassword = "hello";
        String hash = "$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.";
        boolean matches = passwordEncoder.matches(rawPassword, hash);
        return "Password 'hello' matches hash: " + matches;
    }
    
    @GetMapping("/admin-password")
    public String testAdminPassword() {
        User admin = userRepository.findByEmail("admin@altrevo.com").orElse(null);
        if (admin == null) {
            return "Admin user not found";
        }
        
        String rawPassword = "hello";
        boolean matches = passwordEncoder.matches(rawPassword, admin.getPassword());
        return "Admin exists: true, Password 'hello' matches: " + matches + ", Hash: " + admin.getPassword();
    }
}
