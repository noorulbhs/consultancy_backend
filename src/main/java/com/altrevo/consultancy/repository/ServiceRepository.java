package com.altrevo.consultancy.repository;

import com.altrevo.consultancy.entity.Service;
import com.altrevo.consultancy.enums.ServiceStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    
    List<Service> findByStatus(ServiceStatus status);
    
    List<Service> findByStatusAndFeatured(ServiceStatus status, Boolean featured);
    
    List<Service> findByCategory(String category);
    
    List<Service> findByStatus(ServiceStatus status, Pageable pageable);
    
    List<Service> findByStatusAndCategory(ServiceStatus status, String category, Pageable pageable);
    
    @Query("SELECT s FROM Service s WHERE s.status = :status ORDER BY s.sortOrder ASC, s.createdAt DESC")
    List<Service> findByStatusOrderBySortOrder(@Param("status") ServiceStatus status, Pageable pageable);
    
    @Query("SELECT s FROM Service s WHERE s.status = :status AND s.featured = true ORDER BY s.sortOrder ASC, s.createdAt DESC")
    List<Service> findFeaturedByStatus(@Param("status") ServiceStatus status, Pageable pageable);
    
    // Count methods
    long countByStatus(ServiceStatus status);
    long countByFeaturedTrue();
    long countByFeaturedFalse();
    long countByCategory(String category);
}
