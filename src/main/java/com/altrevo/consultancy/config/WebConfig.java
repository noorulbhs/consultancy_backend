package com.altrevo.consultancy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Value("${app.file.upload-dir:uploads}")
    private String uploadDir;

    @Value("${cors.allowed-origins}")
    private String[] corsAllowedOrigins;

    @Value("${cors.allowed-methods}")
    private String[] corsAllowedMethods;

    @Value("${cors.allowed-headers}")
    private String[] corsAllowedHeaders;

    @Value("${cors.allow-credentials}")
    private boolean corsAllowCredentials;
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Static file serving for uploads
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + uploadDir + "/");
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(corsAllowedOrigins)
                        .allowedMethods(corsAllowedMethods)
                        .allowedHeaders(corsAllowedHeaders)
                        .allowCredentials(corsAllowCredentials);
            }
        };
    }
}
