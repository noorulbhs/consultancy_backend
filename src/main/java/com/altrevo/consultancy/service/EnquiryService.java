package com.altrevo.consultancy.service;

import com.altrevo.consultancy.entity.Enquiry;
import com.altrevo.consultancy.repository.EnquiryInMemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnquiryService {
    
    private final EnquiryInMemoryRepository enquiryRepository;
    
    public Enquiry createEnquiry(Enquiry enquiry) {
        // Generate reference number
        String referenceNumber = generateReferenceNumber();
        enquiry.setReferenceNumber(referenceNumber);
        enquiry.setDate(LocalDate.now());
        enquiry.setIsRead(false);
        
        Enquiry savedEnquiry = enquiryRepository.save(enquiry);
        return savedEnquiry;
    }
    
    public List<Enquiry> getAllEnquiries(Boolean isRead, String subject, String service,
                                         LocalDate dateFrom, LocalDate dateTo) {
        // Paging removed: just return all filtered results as a list
        if (dateFrom != null && dateTo != null) {
            return enquiryRepository.findByDateBetween(dateFrom, dateTo);
        } else if (isRead != null) {
            return enquiryRepository.findByIsRead(isRead);
        } else if (subject != null) {
            return enquiryRepository.findBySubject(subject);
        } else if (service != null) {
            return enquiryRepository.findByService(service);
        } else {
            return enquiryRepository.findAll();
        }
    }
    
    public Optional<Enquiry> getEnquiryById(Long id) {
        return enquiryRepository.findById(id);
    }
    
    public Enquiry updateEnquiry(Long id, Enquiry enquiryDetails) {
        Enquiry enquiry = enquiryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enquiry not found with id: " + id));
        
        enquiry.setIsRead(enquiryDetails.getIsRead());
        enquiry.setNotes(enquiryDetails.getNotes());
        enquiry.setAssignedTo(enquiryDetails.getAssignedTo());
        enquiry.setPriority(enquiryDetails.getPriority());
        enquiry.setResponseSent(enquiryDetails.getResponseSent());
        
        return enquiryRepository.save(enquiry);
    }
    
    public void deleteEnquiry(Long id) {
        enquiryRepository.deleteById(id);
    }
    
    public long getUnreadCount() {
        return enquiryRepository.countByIsReadFalse();
    }
    
    public long getTotalCount() {
        return enquiryRepository.count();
    }
    
    // Statistics methods

    public Enquiry updateEnquiryIsRead(Long id, Boolean isRead) {
        Enquiry enquiry = enquiryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enquiry not found with id: " + id));
        enquiry.setIsRead(isRead);
        return enquiryRepository.save(enquiry);
    }

    // Inner class for response DTO
    
    public static class EnquiryStats {
        public final long totalEnquiries;
        public final long unreadEnquiries;
        public final long readEnquiries;
        
        public EnquiryStats(long totalEnquiries, long unreadEnquiries, long readEnquiries) {
            this.totalEnquiries = totalEnquiries;
            this.unreadEnquiries = unreadEnquiries;
            this.readEnquiries = readEnquiries;
        }
    }
    
    // Statistics method for admin/dashboard
    public EnquiryStats getEnquiryStats() {
        long totalEnquiries = enquiryRepository.count();
        long unreadEnquiries = enquiryRepository.countByIsReadFalse();
        long readEnquiries = enquiryRepository.countByIsReadTrue();
        return new EnquiryStats(totalEnquiries, unreadEnquiries, readEnquiries);
    }

    private String generateReferenceNumber() {
        String prefix = "ALT-" + java.time.Year.now().getValue() + "-";
        long count = enquiryRepository.count() + 1;
        return prefix + String.format("%04d", count);
    }
}
