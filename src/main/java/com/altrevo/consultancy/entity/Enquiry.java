package com.altrevo.consultancy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "enquiries")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Enquiry extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "reference_number", unique = true)
    private String referenceNumber;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String email;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "company")
    private String company;
    
    @Column(name = "subject")
    private String subject;
    
    @Column(name = "service")
    private String service;
    
    @Column(name = "message", columnDefinition = "TEXT", nullable = false)
    private String message;
    
    @Column(name = "enquiry_date")
    private LocalDate date;
    
    @Column(name = "is_read")
    private Boolean isRead = false;
    
    @Column(name = "response_sent")
    private Boolean responseSent = false;
    
    @Column(name = "priority")
    private String priority = "MEDIUM";
    
    @Column(name = "source")
    private String source = "WEBSITE";
    
    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;
    
    @Column(name = "assigned_to")
    private String assignedTo;
}
