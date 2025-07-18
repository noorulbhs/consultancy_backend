package com.altrevo.consultancy.service;

import com.altrevo.consultancy.entity.Service;
import com.altrevo.consultancy.enums.ServiceStatus;
import com.altrevo.consultancy.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
@Slf4j
public class ServiceService {
    
    private final ServiceRepository serviceRepository;
    
    public List<Service> getAllActiveServices() {
        return serviceRepository.findByStatusOrderBySortOrder(ServiceStatus.ACTIVE, Pageable.unpaged());
    }
    
    public List<Service> getFeaturedServices() {
        return serviceRepository.findFeaturedByStatus(ServiceStatus.ACTIVE, Pageable.unpaged());
    }
    
    public List<Service> getPublicServices(String category, Boolean featured) {
        if (category != null && featured != null && featured) {
            return serviceRepository.findByStatusAndCategory(ServiceStatus.ACTIVE, category, Pageable.unpaged());
        } else if (category != null) {
            return serviceRepository.findByStatusAndCategory(ServiceStatus.ACTIVE, category, Pageable.unpaged());
        } else if (featured != null && featured) {
            return serviceRepository.findFeaturedByStatus(ServiceStatus.ACTIVE, Pageable.unpaged());
        } else {
            return serviceRepository.findByStatusOrderBySortOrder(ServiceStatus.ACTIVE, Pageable.unpaged());
        }
    }
    
    public Optional<Service> getServiceById(Long id) {
        return serviceRepository.findById(id);
    }
    
    public List<Service> getAllServices(ServiceStatus status, String category, Boolean featured) {
        if (status != null && category != null) {
            return serviceRepository.findByStatusAndCategory(status, category, Pageable.unpaged());
        } else if (status != null) {
            return serviceRepository.findByStatus(status, Pageable.unpaged());
        } else {
            return serviceRepository.findAll();
        }
    }
    
    public Service createService(Service service) {
        return serviceRepository.save(service);
    }
    
    public Service updateService(Long id, Service serviceDetails) {
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found with id: " + id));
        
        service.setTitle(serviceDetails.getTitle());
        service.setDescription(serviceDetails.getDescription());
        service.setDetailedDescription(serviceDetails.getDetailedDescription());
        service.setCategory(serviceDetails.getCategory());
        service.setIcon(serviceDetails.getIcon());
        service.setFeatures(serviceDetails.getFeatures());
        service.setTechnologies(serviceDetails.getTechnologies());
        service.setDuration(serviceDetails.getDuration());
        service.setDeliverables(serviceDetails.getDeliverables());
        service.setCaseStudyClient(serviceDetails.getCaseStudyClient());
        service.setCaseStudyChallenge(serviceDetails.getCaseStudyChallenge());
        service.setCaseStudySolution(serviceDetails.getCaseStudySolution());
        service.setCaseStudyResults(serviceDetails.getCaseStudyResults());
        service.setFeatured(serviceDetails.getFeatured());
        service.setStatus(serviceDetails.getStatus());
        service.setImageUrl(serviceDetails.getImageUrl());
        service.setSortOrder(serviceDetails.getSortOrder());
        
        return serviceRepository.save(service);
    }
    
    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }
    
    public long getServiceCount() {
        return serviceRepository.count();
    }
    
    public long getActiveServiceCount() {
        return serviceRepository.countByStatus(ServiceStatus.ACTIVE);
    }
    
    // Statistics methods
    
    public ServiceStats getServiceStats() {
        log.info("Fetching service statistics");
        long totalServices = serviceRepository.count();
        long activeServices = serviceRepository.countByStatus(ServiceStatus.ACTIVE);
        long draftServices = serviceRepository.countByStatus(ServiceStatus.DRAFT);
        long archivedServices = serviceRepository.countByStatus(ServiceStatus.INACTIVE);
        
        return new ServiceStats(totalServices, activeServices, draftServices, archivedServices);
    }
    
    // Inner class for response DTO
    
    public static class ServiceStats {
        public final long totalServices;
        public final long activeServices;
        public final long draftServices;
        public final long archivedServices;
        
        public ServiceStats(long totalServices, long activeServices, long draftServices, long archivedServices) {
            this.totalServices = totalServices;
            this.activeServices = activeServices;
            this.draftServices = draftServices;
            this.archivedServices = archivedServices;
        }
    }
}
