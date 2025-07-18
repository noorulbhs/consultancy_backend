package com.altrevo.consultancy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "team_members")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TeamMember extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String role;
    
    @Column(name = "department")
    private String department;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "linkedin_url")
    private String linkedinUrl;
    
    @Column(name = "twitter_url")
    private String twitterUrl;
    
    @Column(name = "github_url")
    private String githubUrl;
    
    @Column(name = "photo_url")
    private String photoUrl;
    
    @Column(name = "bio", columnDefinition = "TEXT")
    private String bio;
    
    @ElementCollection
    @CollectionTable(name = "team_member_skills", joinColumns = @JoinColumn(name = "team_member_id"))
    @Column(name = "skill")
    private List<String> skills;
    
    @Column(name = "experience")
    private String experience;
    
    @Column(name = "education")
    private String education;
    
    @Column(name = "location")
    private String location;
    
    @Column(name = "join_date")
    private LocalDate joinDate;
    
    @Column(name = "is_public")
    private Boolean isPublic = true;
    
    @Column(name = "featured")
    private Boolean featured = false;
    
    @ElementCollection
    @CollectionTable(name = "team_member_achievements", joinColumns = @JoinColumn(name = "team_member_id"))
    @Column(name = "achievement")
    private List<String> achievements;
    
    @ElementCollection
    @CollectionTable(name = "team_member_languages", joinColumns = @JoinColumn(name = "team_member_id"))
    @Column(name = "language")
    private List<String> languages;
    
    @ElementCollection
    @CollectionTable(name = "team_member_specializations", joinColumns = @JoinColumn(name = "team_member_id"))
    @Column(name = "specialization")
    private List<String> specializations;
    
    @ElementCollection
    @CollectionTable(name = "team_member_certifications", joinColumns = @JoinColumn(name = "team_member_id"))
    @Column(name = "certification")
    private List<String> certifications;
    
    @ElementCollection
    @CollectionTable(name = "team_member_interests", joinColumns = @JoinColumn(name = "team_member_id"))
    @Column(name = "interest")
    private List<String> interests;
    
    @Column(name = "work_style")
    private String workStyle;
    
    @Column(name = "motto")
    private String motto;
    
    @Column(name = "sort_order")
    private Integer sortOrder = 0;
}
