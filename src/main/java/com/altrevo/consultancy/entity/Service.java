package com.altrevo.consultancy.entity;

import com.altrevo.consultancy.enums.ServiceStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "services")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Service extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "detailed_description", columnDefinition = "TEXT")
    private String detailedDescription;
    
    @Column(name = "category")
    private String category;
    
    @Column(name = "icon")
    private String icon;
    
    @ElementCollection
    @CollectionTable(name = "service_features", joinColumns = @JoinColumn(name = "service_id"))
    @Column(name = "feature")
    private List<String> features;
    
    @ElementCollection
    @CollectionTable(name = "service_technologies", joinColumns = @JoinColumn(name = "service_id"))
    @Column(name = "technology")
    private List<String> technologies;
    
    @Column(name = "duration")
    private String duration;
    
    @ElementCollection
    @CollectionTable(name = "service_deliverables", joinColumns = @JoinColumn(name = "service_id"))
    @Column(name = "deliverable")
    private List<String> deliverables;
    
    @Column(name = "case_study_client")
    private String caseStudyClient;
    
    @Column(name = "case_study_challenge", columnDefinition = "TEXT")
    private String caseStudyChallenge;
    
    @Column(name = "case_study_solution", columnDefinition = "TEXT")
    private String caseStudySolution;
    
    @Column(name = "case_study_results", columnDefinition = "TEXT")
    private String caseStudyResults;
    
    @Column(name = "featured")
    private Boolean featured = false;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ServiceStatus status = ServiceStatus.ACTIVE;
    
    @Column(name = "image_url")
    private String imageUrl;
    
    @Column(name = "sort_order")
    private Integer sortOrder = 0;
}
