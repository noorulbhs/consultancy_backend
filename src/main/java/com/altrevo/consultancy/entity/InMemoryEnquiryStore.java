package com.altrevo.consultancy.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class InMemoryEnquiryStore {
    public static final Map<Long, com.altrevo.consultancy.entity.Enquiry> ENQUIRIES = new HashMap<>();
    public static long idCounter = 1L;

    static {
        // Enquiry 1
        Enquiry e1 = new Enquiry();
        e1.setId(1L);
        e1.setReferenceNumber("ALT-2024-001");
        e1.setName("John Doe");
        e1.setEmail("john.doe@example.com");
        e1.setPhone("+1-555-1001");
        e1.setCompany("Example Corp");
        e1.setSubject("Cloud Migration");
        e1.setService("consultation");
        e1.setMessage("We are interested in migrating our legacy systems to the cloud. Can you provide a consultation?");
        e1.setDate(LocalDate.of(2025, 7, 16));
        e1.setIsRead(false);
        e1.setResponseSent(false);
        e1.setPriority("MEDIUM");
        e1.setSource("WEBSITE");
        e1.setNotes(null);
        e1.setAssignedTo(null);
        e1.setCreatedAt(LocalDateTime.of(2025, 7, 16, 21, 2, 26));
        e1.setUpdatedAt(LocalDateTime.of(2025, 7, 16, 21, 2, 26));
        e1.setCreatedBy(null);
        e1.setUpdatedBy(null);
        ENQUIRIES.put(e1.getId(), e1);

        // Enquiry 2
        Enquiry e2 = new Enquiry();
        e2.setId(2L);
        e2.setReferenceNumber("ALT-2024-002");
        e2.setName("Jane Smith");
        e2.setEmail("jane.smith@techstart.com");
        e2.setPhone("+1-555-1002");
        e2.setCompany("TechStart Inc");
        e2.setSubject("DevOps Strategy");
        e2.setService("general");
        e2.setMessage("We need help implementing DevOps practices in our organization. What does your consulting process look like?");
        e2.setDate(LocalDate.of(2025, 7, 16));
        e2.setIsRead(false);
        e2.setResponseSent(false);
        e2.setPriority("MEDIUM");
        e2.setSource("WEBSITE");
        e2.setNotes(null);
        e2.setAssignedTo(null);
        e2.setCreatedAt(LocalDateTime.of(2025, 7, 16, 21, 2, 26));
        e2.setUpdatedAt(LocalDateTime.of(2025, 7, 16, 21, 2, 26));
        e2.setCreatedBy(null);
        e2.setUpdatedBy(null);
        ENQUIRIES.put(e2.getId(), e2);

        // Enquiry 3
        Enquiry e3 = new Enquiry();
        e3.setId(3L);
        e3.setReferenceNumber("ALT-2024-003");
        e3.setName("Mike Johnson");
        e3.setEmail("mike.johnson@innovate.co");
        e3.setPhone("+1-555-1003");
        e3.setCompany("Innovate Co");
        e3.setSubject("AI Implementation");
        e3.setService("consultation");
        e3.setMessage("Looking to implement AI solutions for our customer service. Would like to discuss options.");
        e3.setDate(LocalDate.of(2025, 7, 16));
        e3.setIsRead(true);
        e3.setResponseSent(false);
        e3.setPriority("MEDIUM");
        e3.setSource("WEBSITE");
        e3.setNotes(null);
        e3.setAssignedTo(null);
        e3.setCreatedAt(LocalDateTime.of(2025, 7, 16, 21, 2, 26));
        e3.setUpdatedAt(LocalDateTime.of(2025, 7, 16, 21, 2, 26));
        e3.setCreatedBy(null);
        e3.setUpdatedBy(null);
        ENQUIRIES.put(e3.getId(), e3);

        idCounter = 4L;
    }
}
