package com.altrevo.consultancy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduledTaskService {
    
    private final WebhookService webhookService;
    
    /**
     * Retry failed webhook events every 10 minutes
     */
    @Scheduled(fixedDelay = 600000) // 10 minutes
    public void retryFailedWebhooks() {
        log.info("Starting scheduled webhook retry task");
        
        try {
            webhookService.retryFailedEvents();
            log.info("Completed scheduled webhook retry task");
        } catch (Exception e) {
            log.error("Error during scheduled webhook retry task", e);
        }
    }
    
    /**
     * Clean up old webhook events every day at 2 AM
     */
    @Scheduled(cron = "0 0 2 * * *")
    public void cleanupOldWebhookEvents() {
        log.info("Starting scheduled webhook cleanup task");
        
        try {
            // TODO: Implement cleanup logic for old webhook events
            // For example, delete webhook events older than 30 days
            log.info("Completed scheduled webhook cleanup task");
        } catch (Exception e) {
            log.error("Error during scheduled webhook cleanup task", e);
        }
    }
}
