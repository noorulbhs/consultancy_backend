package com.altrevo.consultancy.entity;

import com.altrevo.consultancy.enums.MilestoneStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "project_milestones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProjectMilestone extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "milestone_code")
    private String milestoneCode;
    
    @Column(nullable = false)
    private String title;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "due_date")
    private LocalDateTime dueDate;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private MilestoneStatus status = MilestoneStatus.PENDING;
    
    @Column(name = "completed_date")
    private LocalDateTime completedDate;
    
    @Column(name = "progress")
    private Integer progress = 0;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    @com.fasterxml.jackson.annotation.JsonBackReference
    @lombok.ToString.Exclude
    private Project project;
    
    @Column(name = "sort_order")
    private Integer sortOrder = 0;

    @Column(name = "completed")
    private boolean completed;
}
