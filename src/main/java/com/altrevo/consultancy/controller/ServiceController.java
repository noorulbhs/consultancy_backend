package com.altrevo.consultancy.controller;

import com.altrevo.consultancy.dto.ApiResponse;
import com.altrevo.consultancy.entity.Service;
import com.altrevo.consultancy.enums.ServiceStatus;
import com.altrevo.consultancy.service.ServiceService;
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
@Tag(name = "Services", description = "Service management endpoints")
public class ServiceController {
    
    private final ServiceService serviceService;
    
    @GetMapping("/public/services")
    @Operation(summary = "Get all active services", description = "Get all published services (public access)")
    public ResponseEntity<ApiResponse<List<Service>>> getPublicServices(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Boolean featured) {
        List<Service> serviceList = serviceService.getPublicServices(category, featured);
        return ResponseEntity.ok(ApiResponse.success(serviceList));
    }
    
    @GetMapping("/public/services/{id}")
    @Operation(summary = "Get service by ID", description = "Get detailed service information (public access)")
    public ResponseEntity<ApiResponse<Service>> getPublicService(@PathVariable Long id) {
        Optional<Service> service = serviceService.getServiceById(id);
        if (service.isPresent() && service.get().getStatus() == ServiceStatus.ACTIVE) {
            return ResponseEntity.ok(ApiResponse.success(service.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/admin/services")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('EDITOR')")
    @Operation(summary = "Get all services", description = "Get all services including drafts (admin only)")
    public ResponseEntity<ApiResponse<List<Service>>> getAllServices(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) ServiceStatus status,
            @RequestParam(required = false) Boolean featured) {
        List<Service> serviceList = serviceService.getAllServices(status, category, featured);
        return ResponseEntity.ok(ApiResponse.success(serviceList));
    }
    
    @GetMapping("/admin/services/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('EDITOR')")
    @Operation(summary = "Get service by ID", description = "Get detailed service information (admin only)")
    public ResponseEntity<ApiResponse<Service>> getService(@PathVariable Long id) {
        Optional<Service> service = serviceService.getServiceById(id);
        if (service.isPresent()) {
            return ResponseEntity.ok(ApiResponse.success(service.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/admin/services")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('EDITOR')")
    @Operation(summary = "Create service", description = "Create new service (admin only)")
    public ResponseEntity<ApiResponse<Service>> createService(@RequestBody Service service) {
        Service createdService = serviceService.createService(service);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Service created successfully", createdService));
    }
    
    @PutMapping("/admin/services/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('EDITOR')")
    @Operation(summary = "Update service", description = "Update existing service (admin only)")
    public ResponseEntity<ApiResponse<Service>> updateService(@PathVariable Long id, @RequestBody Service service) {
        Service updatedService = serviceService.updateService(id, service);
        return ResponseEntity.ok(ApiResponse.success("Service updated successfully", updatedService));
    }
    
    @DeleteMapping("/admin/services/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @Operation(summary = "Delete service", description = "Delete service (admin only)")
    public ResponseEntity<ApiResponse<String>> deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
        return ResponseEntity.ok(ApiResponse.success("Service deleted successfully", null));
    }
}
