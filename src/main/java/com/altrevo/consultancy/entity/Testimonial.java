package com.altrevo.consultancy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "testimonials")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Testimonial extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(name = "designation")
    private String designation;
    
    @Column(name = "company")
    private String company;
    
    @Column(name = "company_logo")
    private String companyLogo;
    
    @Column(name = "message", columnDefinition = "TEXT", nullable = false)
    private String message;
    
    @Column(name = "rating")
    private Integer rating;
    
    @Column(name = "photo_url")
    private String photoUrl;
    
    @Column(name = "published")
    private Boolean published = true;
    
    @Column(name = "featured")
    private Boolean featured = false;
    
    @Column(name = "testimonial_date")
    private LocalDate date;
    
    @Column(name = "project_type")
    private String projectType;
    
    @Column(name = "location")
    private String location;
    
    @ElementCollection
    @CollectionTable(name = "testimonial_tags", joinColumns = @JoinColumn(name = "testimonial_id"))
    @Column(name = "tag")
    private List<String> tags;
    
    @Column(name = "sort_order")
    private Integer sortOrder = 0;
}
