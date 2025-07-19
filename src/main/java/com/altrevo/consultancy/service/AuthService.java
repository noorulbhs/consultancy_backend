package com.altrevo.consultancy.service;

import com.altrevo.consultancy.dto.auth.LoginRequest;
import com.altrevo.consultancy.dto.auth.LoginResponse;
import com.altrevo.consultancy.dto.auth.RefreshTokenRequest;
import com.altrevo.consultancy.entity.User;
import com.altrevo.consultancy.repository.UserInMemoryRepository;
import com.altrevo.consultancy.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    
    private final AuthenticationManager authenticationManager;
    private final UserInMemoryRepository userRepository = new UserInMemoryRepository();
    private final JwtTokenProvider tokenProvider;
    
    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String jwt = tokenProvider.generateToken(authentication);
        String refreshToken = tokenProvider.generateRefreshToken(request.getEmail());
        
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        LoginResponse.UserInfo userInfo = LoginResponse.UserInfo.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole())
                .permissions(user.getPermissions())
                .avatarUrl(user.getAvatarUrl())
                .build();
        
        return LoginResponse.builder()
                .token(jwt)
                .refreshToken(refreshToken)
                .user(userInfo)
                .expiresIn(86400L) // 24 hours
                .build();
    }
    
    public LoginResponse refreshToken(RefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();
        
        if (tokenProvider.validateToken(refreshToken)) {
            String email = tokenProvider.getEmailFromToken(refreshToken);
            String newToken = tokenProvider.generateTokenFromEmail(email);
            
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            
            LoginResponse.UserInfo userInfo = LoginResponse.UserInfo.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .name(user.getName())
                    .role(user.getRole())
                    .permissions(user.getPermissions())
                    .avatarUrl(user.getAvatarUrl())
                    .build();
            
            return LoginResponse.builder()
                    .token(newToken)
                    .refreshToken(refreshToken)
                    .user(userInfo)
                    .expiresIn(86400L)
                    .build();
        } else {
            throw new RuntimeException("Invalid refresh token");
        }
    }
    
    public void logout(String refreshToken) {
        // In a real application, you would invalidate the refresh token
        // For now, we'll just log the logout
        log.info("User logged out with refresh token: {}", refreshToken.substring(0, 20) + "...");
    }
}
