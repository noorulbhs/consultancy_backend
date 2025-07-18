package com.altrevo.consultancy.controller;

import com.altrevo.consultancy.dto.ApiResponse;
import com.altrevo.consultancy.dto.FeatureToggleUpdateRequest;
import com.altrevo.consultancy.entity.FeatureToggle;
import com.altrevo.consultancy.service.FeatureToggleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class FeatureToggleController {
    
    private final FeatureToggleService featureToggleService;
    
    @GetMapping("/admin/features")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getAllFeatureToggles() {
        log.info("Fetching all feature toggles");
        
        List<FeatureToggle> features = featureToggleService.getAllFeatureToggles();
        
        Map<String, Object> data = new HashMap<>();
        data.put("features", features);
        
        return ResponseEntity.ok(ApiResponse.<Map<String, Object>>builder()
                .success(true)
                .data(data)
                .build());
    }
    
    @GetMapping("/public/features")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getEnabledFeatureToggles() {
        log.info("Fetching enabled feature toggles for public");
        
        List<FeatureToggle> features = featureToggleService.getEnabledFeatureToggles();
        
        Map<String, Object> data = new HashMap<>();
        data.put("features", features);
        
        return ResponseEntity.ok(ApiResponse.<Map<String, Object>>builder()
                .success(true)
                .data(data)
                .build());
    }
    
    @GetMapping("/public/features/section/{section}")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getEnabledFeatureTogglesBySection(
            @PathVariable String section) {
        log.info("Fetching enabled feature toggles for section: {}", section);
        
        List<FeatureToggle> features = featureToggleService.getEnabledFeatureTogglesBySection(section);
        
        Map<String, Object> data = new HashMap<>();
        data.put("features", features);
        data.put("section", section);
        
        return ResponseEntity.ok(ApiResponse.<Map<String, Object>>builder()
                .success(true)
                .data(data)
                .build());
    }
    
    @GetMapping("/admin/features/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<ApiResponse<FeatureToggle>> getFeatureToggleById(@PathVariable String id) {
        log.info("Fetching feature toggle by ID: {}", id);
        
        FeatureToggle feature = featureToggleService.getFeatureToggleById(id)
                .orElseThrow(() -> new RuntimeException("Feature toggle not found with ID: " + id));
        
        return ResponseEntity.ok(ApiResponse.<FeatureToggle>builder()
                .success(true)
                .data(feature)
                .build());
    }
    
    @PutMapping("/admin/features/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<ApiResponse<Map<String, Object>>> updateFeatureToggle(
            @PathVariable String id,
            @RequestBody Map<String, Boolean> request,
            Authentication authentication) {
        log.info("Updating feature toggle: {} by: {}", id, authentication.getName());
        
        Boolean enabled = request.get("enabled");
        if (enabled == null) {
            throw new RuntimeException("Enabled field is required");
        }
        
        FeatureToggle updatedFeature = featureToggleService.updateFeatureToggle(
                id, enabled, authentication.getName());
        
        Map<String, Object> data = new HashMap<>();
        data.put("id", updatedFeature.getId());
        data.put("enabled", updatedFeature.getEnabled());
        data.put("updatedAt", updatedFeature.getUpdatedAt());
        data.put("updatedBy", updatedFeature.getUpdatedBy());
        
        return ResponseEntity.ok(ApiResponse.<Map<String, Object>>builder()
                .success(true)
                .message("Feature toggle updated successfully")
                .data(data)
                .build());
    }
    
    @PostMapping("/admin/features")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<ApiResponse<FeatureToggle>> createFeatureToggle(
            @RequestBody FeatureToggle featureToggle,
            Authentication authentication) {
        log.info("Creating new feature toggle: {} by: {}", featureToggle.getId(), authentication.getName());
        
        FeatureToggle createdFeature = featureToggleService.createFeatureToggle(
                featureToggle, authentication.getName());
        
        return ResponseEntity.ok(ApiResponse.<FeatureToggle>builder()
                .success(true)
                .message("Feature toggle created successfully")
                .data(createdFeature)
                .build());
    }
    
    @DeleteMapping("/admin/features/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<ApiResponse<Map<String, Object>>> deleteFeatureToggle(@PathVariable String id) {
        log.info("Deleting feature toggle with ID: {}", id);
        
        featureToggleService.deleteFeatureToggle(id);
        
        Map<String, Object> data = new HashMap<>();
        data.put("id", id);
        
        return ResponseEntity.ok(ApiResponse.<Map<String, Object>>builder()
                .success(true)
                .message("Feature toggle deleted successfully")
                .data(data)
                .build());
    }
    
    @GetMapping("/admin/features/stats")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getFeatureToggleStats() {
        log.info("Fetching feature toggle statistics");
        
        Map<String, Object> stats = new HashMap<>();
        stats.put("total", featureToggleService.getTotalCount());
        stats.put("enabled", featureToggleService.getEnabledCount());
        stats.put("disabled", featureToggleService.getDisabledCount());
        
        return ResponseEntity.ok(ApiResponse.<Map<String, Object>>builder()
                .success(true)
                .data(stats)
                .build());
    }

    @PutMapping("admin/features/navbar-careers")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<ApiResponse<Map<String, Object>>> updateNavbarCareersFeature(@RequestBody FeatureToggleUpdateRequest request) {
        // Assuming you have a service method to update the feature toggle by id
        featureToggleService.updateFeatureEnabled("navbar-careers", request.getEnabled());
        Map<String, Object> data = new HashMap<>();
        data.put("id", "navbar-careers");
        data.put("enabled", request.getEnabled());
        return ResponseEntity.ok(ApiResponse.<Map<String, Object>>builder()
                .success(true)
                .message("Navbar Careers feature updated successfully")
                .data(data)
                .build());
    }
}
