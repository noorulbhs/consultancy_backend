package com.altrevo.consultancy.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectExternalTeamMember implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    @com.fasterxml.jackson.annotation.JsonBackReference
    private Project project;

    private String name;
    private String email;
    private String phone;
    private String company;
    private String role;
    private String contractType;
    private String contractStartDate;
    private Integer allocatedHours;
    private Integer hourlyRate;
    private String paymentTerms;
    private String notes;
    private Boolean isActive;
}
