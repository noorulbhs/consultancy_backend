package com.altrevo.consultancy.controller;

import com.altrevo.consultancy.dto.ApiResponse;
import com.altrevo.consultancy.entity.Testimonial;
import com.altrevo.consultancy.service.TestimonialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Tag(name = "Testimonials", description = "Testimonial management endpoints")
public class TestimonialController {
    
    private final TestimonialService testimonialService;
    
    @GetMapping("/public/testimonials")
    @Operation(summary = "Get all published testimonials", description = "Get all published testimonials (public access)")
    public ResponseEntity<ApiResponse<List<Testimonial>>> getPublicTestimonials(
            @RequestParam(required = false) Boolean featured,
            @RequestParam(required = false) Integer rating) {
        List<Testimonial> testimonials = testimonialService.getPublicTestimonials(featured, rating);
        return ResponseEntity.ok(ApiResponse.success(testimonials));
    }
    
    @GetMapping("/public/testimonials/{id}")
    @Operation(summary = "Get testimonial by ID", description = "Get detailed testimonial information (public access)")
    public ResponseEntity<ApiResponse<Testimonial>> getPublicTestimonial(@PathVariable Long id) {
        Optional<Testimonial> testimonial = testimonialService.getTestimonialById(id);
        if (testimonial.isPresent() && testimonial.get().getPublished()) {
            return ResponseEntity.ok(ApiResponse.success(testimonial.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/admin/testimonials")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('EDITOR')")
    @Operation(summary = "Get all testimonials", description = "Get all testimonials including unpublished (admin only)")
    public ResponseEntity<ApiResponse<List<Testimonial>>> getAllTestimonials(
            @RequestParam(required = false) Boolean published,
            @RequestParam(required = false) Boolean featured,
            @RequestParam(required = false) Integer rating) {
        List<Testimonial> testimonials = testimonialService.getAllTestimonials(published, featured, rating);
        return ResponseEntity.ok(ApiResponse.success(testimonials));
    }
    
    @GetMapping("/admin/testimonials/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('EDITOR')")
    @Operation(summary = "Get testimonial by ID", description = "Get detailed testimonial information (admin only)")
    public ResponseEntity<ApiResponse<Testimonial>> getTestimonial(@PathVariable Long id) {
        Optional<Testimonial> testimonial = testimonialService.getTestimonialById(id);
        if (testimonial.isPresent()) {
            return ResponseEntity.ok(ApiResponse.success(testimonial.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/admin/testimonials")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('EDITOR')")
    @Operation(summary = "Create testimonial", description = "Create new testimonial (admin only)")
    public ResponseEntity<ApiResponse<Testimonial>> createTestimonial(@RequestBody Testimonial testimonial) {
        Testimonial createdTestimonial = testimonialService.createTestimonial(testimonial);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Testimonial created successfully", createdTestimonial));
    }
    
    @PutMapping("/admin/testimonials/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('EDITOR')")
    @Operation(summary = "Update testimonial", description = "Update existing testimonial (admin only)")
    public ResponseEntity<ApiResponse<Testimonial>> updateTestimonial(@PathVariable Long id, @RequestBody Testimonial testimonial) {
        Testimonial updatedTestimonial = testimonialService.updateTestimonial(id, testimonial);
        return ResponseEntity.ok(ApiResponse.success("Testimonial updated successfully", updatedTestimonial));
    }
    
    @DeleteMapping("/admin/testimonials/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @Operation(summary = "Delete testimonial", description = "Delete testimonial (admin only)")
    public ResponseEntity<ApiResponse<String>> deleteTestimonial(@PathVariable Long id) {
        testimonialService.deleteTestimonial(id);
        return ResponseEntity.ok(ApiResponse.success("Testimonial deleted successfully", null));
    }
}
