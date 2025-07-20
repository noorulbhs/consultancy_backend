package com.altrevo.consultancy.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "password123";
        String encodedPassword = encoder.encode(rawPassword);
//        System.out.println("Raw password: " + rawPassword);
//        System.out.println("Encoded password: " + encodedPassword);
        
        // Test if the current hash matches
        String currentHash = "$2a$10$6K8V2VJfGCIV5VBJGgH8eOjLYxLLMoD7.5qV1qGYQOjJGxgOKLKMm";
        boolean matches = encoder.matches(rawPassword, currentHash);
//        System.out.println("Current hash matches: " + matches);
        
        // Generate new hash
        String newHash = encoder.encode(rawPassword);
//        System.out.println("New hash: " + newHash);
        boolean newMatches = encoder.matches(rawPassword, newHash);
//        System.out.println("New hash matches: " + newMatches);
    }
}
