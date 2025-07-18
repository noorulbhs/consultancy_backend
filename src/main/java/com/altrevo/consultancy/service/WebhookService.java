package com.altrevo.consultancy.service;

import com.altrevo.consultancy.entity.WebhookEvent;
import com.altrevo.consultancy.repository.WebhookEventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebhookService {
    
    private final WebhookEventRepository webhookEventRepository;
    private final ObjectMapper objectMapper;
    
    @Transactional
    public WebhookEvent createWebhookEvent(String eventType, Object payload, String sourceIp, String userAgent) {
        log.info("Creating webhook event: {} from IP: {}", eventType, sourceIp);
        
        WebhookEvent event = new WebhookEvent();
        event.setEventType(eventType);
        event.setSourceIp(sourceIp);
        event.setUserAgent(userAgent);
        
        try {
            event.setPayload(objectMapper.writeValueAsString(payload));
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize webhook payload", e);
            event.setPayload(payload.toString());
        }
        
        return webhookEventRepository.save(event);
    }
    
    @Transactional
    public WebhookEvent processWebhookEvent(Long eventId) {
        log.info("Processing webhook event: {}", eventId);
        
        WebhookEvent event = webhookEventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Webhook event not found with ID: " + eventId));
        
        try {
            // Process the webhook event based on its type
            switch (event.getEventType()) {
                case "form.submitted":
                    processFormSubmissionEvent(event);
                    break;
                case "user.registered":
                    processUserRegistrationEvent(event);
                    break;
                case "enquiry.created":
                    processEnquiryCreatedEvent(event);
                    break;
                default:
                    log.warn("Unknown webhook event type: {}", event.getEventType());
            }
            
            event.setProcessed(true);
            event.setProcessedAt(LocalDateTime.now());
            event.setErrorMessage(null);
            
        } catch (Exception e) {
            log.error("Failed to process webhook event: {}", eventId, e);
            event.setErrorMessage(e.getMessage());
            event.setRetryCount(event.getRetryCount() + 1);
            
            if (event.getRetryCount() < event.getMaxRetries()) {
                event.setNextRetryAt(LocalDateTime.now().plusMinutes(5 * event.getRetryCount()));
            }
        }
        
        return webhookEventRepository.save(event);
    }
    
    private void processFormSubmissionEvent(WebhookEvent event) {
        log.info("Processing form submission webhook event");
        // Add your form submission processing logic here
        // For example, send email notifications, update analytics, etc.
    }
    
    private void processUserRegistrationEvent(WebhookEvent event) {
        log.info("Processing user registration webhook event");
        // Add your user registration processing logic here
        // For example, send welcome emails, update user metrics, etc.
    }
    
    private void processEnquiryCreatedEvent(WebhookEvent event) {
        log.info("Processing enquiry created webhook event");
        // Add your enquiry processing logic here
        // For example, send notifications to admin, update CRM, etc.
    }
    
    public List<WebhookEvent> getAllWebhookEvents() {
        log.info("Fetching all webhook events");
        return webhookEventRepository.findAll();
    }
    
    public List<WebhookEvent> getWebhookEventsByType(String eventType) {
        log.info("Fetching webhook events by type: {}", eventType);
        return webhookEventRepository.findByEventType(eventType, Pageable.unpaged());
    }
    
    public List<WebhookEvent> getWebhookEventsByStatus(Boolean processed) {
        log.info("Fetching webhook events by status: {}", processed);
        return webhookEventRepository.findByProcessed(processed, Pageable.unpaged());
    }
    
    public Optional<WebhookEvent> getWebhookEventById(Long id) {
        log.info("Fetching webhook event by ID: {}", id);
        return webhookEventRepository.findById(id);
    }
    
    public List<WebhookEvent> getUnprocessedEvents() {
        log.info("Fetching unprocessed webhook events");
        return webhookEventRepository.findUnprocessedEvents();
    }
    
    public List<WebhookEvent> getEventsToRetry() {
        log.info("Fetching webhook events to retry");
        return webhookEventRepository.findEventsToRetry(LocalDateTime.now());
    }
    
    @Transactional
    public void retryFailedEvents() {
        log.info("Retrying failed webhook events");
        List<WebhookEvent> eventsToRetry = getEventsToRetry();
        
        for (WebhookEvent event : eventsToRetry) {
            try {
                processWebhookEvent(event.getId());
            } catch (Exception e) {
                log.error("Failed to retry webhook event: {}", event.getId(), e);
            }
        }
    }
    
    public Map<String, Long> getWebhookStats() {
        log.info("Fetching webhook statistics");
        
        return Map.of(
            "total", webhookEventRepository.count(),
            "processed", webhookEventRepository.countProcessed(),
            "unprocessed", webhookEventRepository.countUnprocessed(),
            "failed", webhookEventRepository.countFailed()
        );
    }
    
    public long getEventCountByType(String eventType) {
        return webhookEventRepository.countByEventType(eventType);
    }
    
    public List<WebhookEvent> getEventsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        log.info("Fetching webhook events by date range: {} to {}", startDate, endDate);
        return webhookEventRepository.findByDateRange(startDate, endDate);
    }
}
