package com.altrevo.consultancy.repository;

import com.altrevo.consultancy.entity.WebhookEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WebhookEventRepository extends JpaRepository<WebhookEvent, Long> {
    
    List<WebhookEvent> findByEventType(String eventType, Pageable pageable);
    
    List<WebhookEvent> findByProcessed(Boolean processed, Pageable pageable);
    
    @Query("SELECT w FROM WebhookEvent w WHERE w.processed = false AND w.retryCount < w.maxRetries")
    List<WebhookEvent> findUnprocessedEvents();
    
    @Query("SELECT w FROM WebhookEvent w WHERE w.processed = false AND w.nextRetryAt <= :now")
    List<WebhookEvent> findEventsToRetry(LocalDateTime now);
    
    @Query("SELECT COUNT(w) FROM WebhookEvent w WHERE w.processed = true")
    long countProcessed();
    
    @Query("SELECT COUNT(w) FROM WebhookEvent w WHERE w.processed = false")
    long countUnprocessed();
    
    @Query("SELECT COUNT(w) FROM WebhookEvent w WHERE w.retryCount >= w.maxRetries AND w.processed = false")
    long countFailed();
    
    @Query("SELECT COUNT(w) FROM WebhookEvent w WHERE w.eventType = :eventType")
    long countByEventType(String eventType);
    
    @Query("SELECT w FROM WebhookEvent w WHERE w.createdAt >= :startDate AND w.createdAt <= :endDate")
    List<WebhookEvent> findByDateRange(LocalDateTime startDate, LocalDateTime endDate);
}
