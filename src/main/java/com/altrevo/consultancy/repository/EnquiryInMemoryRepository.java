package com.altrevo.consultancy.repository;

import com.altrevo.consultancy.entity.Enquiry;
import com.altrevo.consultancy.entity.InMemoryEnquiryStore;
import java.util.*;
import org.springframework.stereotype.Repository;

@Repository
public class EnquiryInMemoryRepository {
    public Enquiry save(Enquiry enquiry) {
        if (enquiry.getId() == null) {
            enquiry.setId(InMemoryEnquiryStore.idCounter++);
        }
        InMemoryEnquiryStore.ENQUIRIES.put(enquiry.getId(), enquiry);
        return enquiry;
    }

    public Optional<Enquiry> findById(Long id) {
        return Optional.ofNullable(InMemoryEnquiryStore.ENQUIRIES.get(id));
    }

    public List<Enquiry> findAll() {
        return new ArrayList<>(InMemoryEnquiryStore.ENQUIRIES.values());
    }

    public void deleteById(Long id) {
        InMemoryEnquiryStore.ENQUIRIES.remove(id);
    }

    public Optional<Enquiry> findByReferenceNumber(String referenceNumber) {
        return InMemoryEnquiryStore.ENQUIRIES.values().stream()
                .filter(e -> referenceNumber.equals(e.getReferenceNumber()))
                .findFirst();
    }

    public List<Enquiry> findByIsRead(Boolean isRead) {
        List<Enquiry> result = new ArrayList<>();
        for (Enquiry e : InMemoryEnquiryStore.ENQUIRIES.values()) {
            if (e.getIsRead() != null && e.getIsRead().equals(isRead)) {
                result.add(e);
            }
        }
        return result;
    }

    public List<Enquiry> findBySubject(String subject) {
        List<Enquiry> result = new ArrayList<>();
        for (Enquiry e : InMemoryEnquiryStore.ENQUIRIES.values()) {
            if (subject.equals(e.getSubject())) {
                result.add(e);
            }
        }
        return result;
    }

    public List<Enquiry> findByService(String service) {
        List<Enquiry> result = new ArrayList<>();
        for (Enquiry e : InMemoryEnquiryStore.ENQUIRIES.values()) {
            if (service.equals(e.getService())) {
                result.add(e);
            }
        }
        return result;
    }

    public List<Enquiry> findByDateBetween(java.time.LocalDate from, java.time.LocalDate to) {
        List<Enquiry> result = new ArrayList<>();
        for (Enquiry e : InMemoryEnquiryStore.ENQUIRIES.values()) {
            if (e.getDate() != null && !e.getDate().isBefore(from) && !e.getDate().isAfter(to)) {
                result.add(e);
            }
        }
        return result;
    }

    public long count() {
        return InMemoryEnquiryStore.ENQUIRIES.size();
    }

    public long countByIsReadFalse() {
        return InMemoryEnquiryStore.ENQUIRIES.values().stream().filter(e -> Boolean.FALSE.equals(e.getIsRead())).count();
    }

    public long countByIsReadTrue() {
        return InMemoryEnquiryStore.ENQUIRIES.values().stream().filter(e -> Boolean.TRUE.equals(e.getIsRead())).count();
    }
}
