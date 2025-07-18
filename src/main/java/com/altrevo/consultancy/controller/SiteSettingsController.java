package com.altrevo.consultancy.controller;

import com.altrevo.consultancy.dto.ApiResponse;
import com.altrevo.consultancy.entity.SiteSettings;
import com.altrevo.consultancy.service.SiteSettingsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Site Settings", description = "Site settings management endpoints")
public class SiteSettingsController {
    
    private final SiteSettingsService siteSettingsService;
    
    @GetMapping({"/public/settings","/admin/settings"})
    @Operation(summary = "Get site settings", description = "Get current site settings (public access)")
    public ResponseEntity<ApiResponse<SiteSettings>> getPublicSettings() {
        SiteSettings settings = siteSettingsService.getSettings();
        ResponseEntity<ApiResponse<SiteSettings>> respone =  ResponseEntity.ok(ApiResponse.success(settings));
        return respone;
    }
    
    @PutMapping({"/admin/settings", "/settings"})
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @Operation(summary = "Update site settings", description = "Update site settings (admin only)")
    public ResponseEntity<ApiResponse<SiteSettings>> updateSettings(@RequestBody SiteSettings settings) {
        SiteSettings updatedSettings = siteSettingsService.updateSettings(settings);
        return ResponseEntity.ok(ApiResponse.success("Settings updated successfully", updatedSettings));
    }
}
