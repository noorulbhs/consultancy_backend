package com.altrevo.consultancy.controller;

import com.altrevo.consultancy.dto.ApiResponse;
import com.altrevo.consultancy.dto.EnquirySubmissionResponse;
import com.altrevo.consultancy.dto.EnquiryListResponse;
import com.altrevo.consultancy.dto.EnquiryStats;
import com.altrevo.consultancy.entity.Enquiry;
import com.altrevo.consultancy.service.EnquiryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Tag(name = "Enquiries", description = "Enquiry management endpoints")
public class EnquiryController {
    
    private final EnquiryService enquiryService;
    
    @PostMapping("/public/enquiries")
    @Operation(summary = "Submit enquiry", description = "Submit contact form enquiry (public access)")
    public ResponseEntity<ApiResponse<EnquirySubmissionResponse>> submitEnquiry(@RequestBody Enquiry enquiry) {
        Enquiry createdEnquiry = enquiryService.createEnquiry(enquiry);
        
        EnquirySubmissionResponse response = EnquirySubmissionResponse.builder()
                .id(createdEnquiry.getId())
                .referenceNumber(createdEnquiry.getReferenceNumber())
                .createdAt(createdEnquiry.getCreatedAt())
                .build();
        
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Thank you for your enquiry! We will get back to you within 24 hours.", response));
    }
    
    @GetMapping("/admin/enquiries")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('EDITOR')")
    @Operation(summary = "Get all enquiries", description = "Get all contact form enquiries (admin only)")
    public ResponseEntity<ApiResponse<EnquiryListResponse>> getAllEnquiries(
            @RequestParam(required = false) Boolean isRead,
            @RequestParam(required = false) String subject,
            @RequestParam(required = false) String service,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo) {
        List<Enquiry> enquiries = enquiryService.getAllEnquiries(isRead, subject, service, dateFrom, dateTo);
        EnquiryStats stats = EnquiryStats.builder()
                .totalEnquiries(enquiryService.getTotalCount())
                .unreadEnquiries(enquiryService.getUnreadCount())
                .thisMonth(0L) // TODO: implement this month count
                .lastMonth(0L) // TODO: implement last month count
                .build();
        EnquiryListResponse response = EnquiryListResponse.builder()
                .enquiries(enquiries)
                .stats(stats)
                .build();
        return ResponseEntity.ok(ApiResponse.success(response));
    }
    
    @GetMapping("/admin/enquiries/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('EDITOR')")
    @Operation(summary = "Get enquiry by ID", description = "Get detailed enquiry information (admin only)")
    public ResponseEntity<ApiResponse<Enquiry>> getEnquiry(@PathVariable Long id) {
        Optional<Enquiry> enquiry = enquiryService.getEnquiryById(id);
        if (enquiry.isPresent()) {
            return ResponseEntity.ok(ApiResponse.success(enquiry.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/admin/enquiries/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('EDITOR')")
    @Operation(summary = "Update enquiry", description = "Update enquiry status and notes (admin only)")
    public ResponseEntity<ApiResponse<Enquiry>> updateEnquiry(@PathVariable Long id, @RequestBody Enquiry enquiry) {
        Enquiry updatedEnquiry = enquiryService.updateEnquiry(id, enquiry);
        return ResponseEntity.ok(ApiResponse.success("Enquiry updated successfully", updatedEnquiry));
    }
    
    @DeleteMapping("/admin/enquiries/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @Operation(summary = "Delete enquiry", description = "Delete enquiry (admin only)")
    public ResponseEntity<ApiResponse<String>> deleteEnquiry(@PathVariable Long id) {
        enquiryService.deleteEnquiry(id);
        return ResponseEntity.ok(ApiResponse.success("Enquiry deleted successfully", null));
    }

    @PatchMapping("/admin/enquiries/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('EDITOR')")
    @Operation(summary = "Update enquiry read status", description = "Update only the isRead status of an enquiry (admin only)")
    public ResponseEntity<ApiResponse<Enquiry>> updateEnquiryIsRead(
            @PathVariable Long id,
            @RequestBody Enquiry enquiry) {
        if (enquiry.getIsRead() == null) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Missing 'isRead' field"));
        }
        Enquiry updatedEnquiry = enquiryService.updateEnquiryIsRead(id, enquiry.getIsRead());
        return ResponseEntity.ok(ApiResponse.success("Enquiry read status updated", updatedEnquiry));
    }
}
