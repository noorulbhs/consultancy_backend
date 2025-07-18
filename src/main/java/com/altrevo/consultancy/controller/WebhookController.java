package com.altrevo.consultancy.controller;

import com.altrevo.consultancy.dto.ApiResponse;
import com.altrevo.consultancy.entity.WebhookEvent;
import com.altrevo.consultancy.service.WebhookService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class WebhookController {
    
    private final WebhookService webhookService;
    
    @PostMapping("/webhooks/form-submission")
    public ResponseEntity<ApiResponse<Map<String, Object>>> handleFormSubmissionWebhook(
            @RequestBody Map<String, Object> payload,
            HttpServletRequest request) {
        log.info("Received form submission webhook");
        
        String sourceIp = getClientIpAddress(request);
        String userAgent = request.getHeader("User-Agent");
        
        // Create webhook event for form submission
        Map<String, Object> eventData = new HashMap<>();
        eventData.put("event", "form.submitted");
        eventData.put("data", payload);
        eventData.put("timestamp", java.time.LocalDateTime.now());
        
        WebhookEvent event = webhookService.createWebhookEvent(
                "form.submitted", eventData, sourceIp, userAgent);
        
        // Process the webhook event asynchronously
        webhookService.processWebhookEvent(event.getId());
        
        Map<String, Object> response = new HashMap<>();
        response.put("eventId", event.getId());
        response.put("status", "received");
        response.put("timestamp", event.getCreatedAt());
        
        return ResponseEntity.ok(ApiResponse.<Map<String, Object>>builder()
                .success(true)
                .message("Webhook received and processed successfully")
                .data(response)
                .build());
    }
    
    @PostMapping("/webhooks/enquiry-created")
    public ResponseEntity<ApiResponse<Map<String, Object>>> handleEnquiryCreatedWebhook(
            @RequestBody Map<String, Object> payload,
            HttpServletRequest request) {
        log.info("Received enquiry created webhook");
        
        String sourceIp = getClientIpAddress(request);
        String userAgent = request.getHeader("User-Agent");
        
        // Create webhook event for enquiry creation
        Map<String, Object> eventData = new HashMap<>();
        eventData.put("event", "enquiry.created");
        eventData.put("data", payload);
        eventData.put("timestamp", java.time.LocalDateTime.now());
        
        WebhookEvent event = webhookService.createWebhookEvent(
                "enquiry.created", eventData, sourceIp, userAgent);
        
        // Process the webhook event asynchronously
        webhookService.processWebhookEvent(event.getId());
        
        Map<String, Object> response = new HashMap<>();
        response.put("eventId", event.getId());
        response.put("status", "received");
        response.put("timestamp", event.getCreatedAt());
        
        return ResponseEntity.ok(ApiResponse.<Map<String, Object>>builder()
                .success(true)
                .message("Webhook received and processed successfully")
                .data(response)
                .build());
    }
    
    @GetMapping("/admin/webhooks")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<ApiResponse<List<WebhookEvent>>> getAllWebhookEvents(
            @RequestParam(required = false) String eventType,
            @RequestParam(required = false) Boolean processed) {
        log.info("Fetching webhook events");
        List<WebhookEvent> webhookEvents;
        if (eventType != null) {
            webhookEvents = webhookService.getWebhookEventsByType(eventType);
        } else if (processed != null) {
            webhookEvents = webhookService.getWebhookEventsByStatus(processed);
        } else {
            webhookEvents = webhookService.getAllWebhookEvents();
        }
        return ResponseEntity.ok(ApiResponse.success(webhookEvents));
    }
    
    @GetMapping("/admin/webhooks/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<ApiResponse<WebhookEvent>> getWebhookEventById(@PathVariable Long id) {
        log.info("Fetching webhook event by ID: {}", id);
        
        WebhookEvent event = webhookService.getWebhookEventById(id)
                .orElseThrow(() -> new RuntimeException("Webhook event not found with ID: " + id));
        
        return ResponseEntity.ok(ApiResponse.<WebhookEvent>builder()
                .success(true)
                .data(event)
                .build());
    }
    
    @PostMapping("/admin/webhooks/{id}/retry")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<ApiResponse<WebhookEvent>> retryWebhookEvent(@PathVariable Long id) {
        log.info("Retrying webhook event: {}", id);
        
        WebhookEvent event = webhookService.processWebhookEvent(id);
        
        return ResponseEntity.ok(ApiResponse.<WebhookEvent>builder()
                .success(true)
                .message("Webhook event retried successfully")
                .data(event)
                .build());
    }
    
    @PostMapping("/admin/webhooks/retry-failed")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<ApiResponse<Map<String, Object>>> retryAllFailedWebhooks() {
        log.info("Retrying all failed webhook events");
        
        List<WebhookEvent> eventsToRetry = webhookService.getEventsToRetry();
        webhookService.retryFailedEvents();
        
        Map<String, Object> response = new HashMap<>();
        response.put("retriedCount", eventsToRetry.size());
        response.put("timestamp", java.time.LocalDateTime.now());
        
        return ResponseEntity.ok(ApiResponse.<Map<String, Object>>builder()
                .success(true)
                .message("Failed webhooks retried successfully")
                .data(response)
                .build());
    }
    
    @GetMapping("/admin/webhooks/stats")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<ApiResponse<Map<String, Long>>> getWebhookStats() {
        log.info("Fetching webhook statistics");
        
        Map<String, Long> stats = webhookService.getWebhookStats();
        
        return ResponseEntity.ok(ApiResponse.<Map<String, Long>>builder()
                .success(true)
                .data(stats)
                .build());
    }
    
    private String getClientIpAddress(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        String xRealIp = request.getHeader("X-Real-IP");
        
        if (xForwardedFor != null && !xForwardedFor.isEmpty() && !"unknown".equalsIgnoreCase(xForwardedFor)) {
            return xForwardedFor.split(",")[0].trim();
        } else if (xRealIp != null && !xRealIp.isEmpty() && !"unknown".equalsIgnoreCase(xRealIp)) {
            return xRealIp;
        } else {
            return request.getRemoteAddr();
        }
    }
}
