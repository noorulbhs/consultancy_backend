package com.altrevo.consultancy.config;

import com.altrevo.consultancy.security.JwtAuthenticationEntryPoint;
import com.altrevo.consultancy.security.JwtAuthenticationFilter;
import com.altrevo.consultancy.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // Public endpoints - no authentication required
                .requestMatchers(HttpMethod.GET, "/public/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/public/enquiries").permitAll()
                .requestMatchers(HttpMethod.POST, "/admin/users").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/admin/users").permitAll()
                
                // Authentication endpoints
                .requestMatchers(HttpMethod.POST, "/admin/auth/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/admin/auth/refresh").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/admin/auth/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/admin/auth/refresh").permitAll()
                
                // Debug endpoints (temporary)
                .requestMatchers("/debug/**").permitAll()
                
                // Static resources
                .requestMatchers("/files/**").permitAll()
                .requestMatchers("/uploads/**").permitAll()
                
                // API Documentation
                .requestMatchers("/api-docs/**").permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/swagger-ui.html").permitAll()
                .requestMatchers("/v3/api-docs/**").permitAll()
                
                // Health check
                .requestMatchers("/actuator/health").permitAll()
                
                // Admin endpoints - authentication required
                .requestMatchers("/admin/**").authenticated()
                .requestMatchers("/dashboard/**").authenticated()
                .requestMatchers("/upload/**").authenticated()
                
                // Legacy endpoints for backward compatibility
                .requestMatchers(HttpMethod.GET, "/blogs/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/team/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/careers/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/pages/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/features/**").permitAll()
                
                // All other requests require authentication
                .anyRequest().authenticated()
            );
        
        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList(
            "http://localhost:3000",
            "http://localhost:3001",
            "http://localhost:4200",
            "https://altrevo.com",
            "https://*.altrevo.com"
        ));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
