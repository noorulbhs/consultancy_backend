package com.altrevo.consultancy.repository;

import com.altrevo.consultancy.entity.Enquiry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EnquiryRepository extends JpaRepository<Enquiry, Long> {
    
    List<Enquiry> findByIsReadFalse();
    
    List<Enquiry> findBySubject(String subject);
    
    List<Enquiry> findByService(String service);
    
    List<Enquiry> findByIsRead(Boolean isRead);

    List<Enquiry> findByDateBetween(LocalDate startDate, LocalDate endDate);
    
    @Query("SELECT e FROM Enquiry e WHERE e.date >= :startDate AND e.date <= :endDate ORDER BY e.createdAt DESC")
    List<Enquiry> findByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    
    // Count methods
    long countByIsReadTrue();
    long countByIsReadFalse();
    long countBySubject(String subject);
    long countByService(String service);
    
    @Query("SELECT COUNT(e) FROM Enquiry e WHERE e.createdAt >= :startDate AND e.createdAt <= :endDate")
    long countByCreatedAtBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT COUNT(e) FROM Enquiry e WHERE e.date >= :startDate AND e.date <= :endDate")
    long countByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    
    @Query("SELECT e.service, COUNT(e) FROM Enquiry e GROUP BY e.service ORDER BY COUNT(e) DESC")
    List<Object[]> findTopServicesByEnquiries();
    
    boolean existsByReferenceNumber(String referenceNumber);
}
