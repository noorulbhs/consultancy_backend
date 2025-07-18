package com.altrevo.consultancy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "site_settings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SiteSettings extends BaseEntity{

    @Id
    private String id = "main-settings";

    @Column(name = "version")
    private String version = "2.0";

    // Company Information
    @Column(name = "company_name")
    private String companyName;
    private String tagline;
    private String heroSubtext;
    private String description;
    private String logoUrl;
    private String faviconUrl;
    private String email;
    private String phone;
    private String alternatePhone;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipCode;

    @Convert(converter = JsonMapConverter.class)
    private Map<String, Object> businessHours;
    @Convert(converter = JsonMapConverter.class)
    private Map<String, Object> social;
    @Convert(converter = JsonMapConverter.class)
    private Map<String, Object> seo;
    @Convert(converter = JsonMapConverter.class)
    private Map<String, Object> footer;
    @Convert(converter = JsonMapConverter.class)
    private Map<String, Object> contactForm;

    // General Settings
    private Boolean maintenanceMode;
    private String maintenanceMessage;
    private String theme;
    private String primaryColor;
    private String secondaryColor;

    @Convert(converter = JsonMapConverter.class)
    private Map<String, Object> statistics;

    // Last Updated
    @Column(name = "last_updated")
    private String lastUpdated;

    @Column(name = "updated_by")
    private String updatedBy;

    // Timestamps
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and setters...
}
//public class SiteSettings extends BaseEntity {
//
//    @Id
//    private String id = "main-settings";
//
//    @Column(name = "version")
//    private String version = "2.0";
//
//    @Column(name = "company_name")
//    private String companyName;
//
//    @Column(name = "tagline")
//    private String tagline;
//
//    @Column(name = "description", columnDefinition = "TEXT")
//    private String description;
//
//    @Column(name = "logo_url")
//    private String logoUrl;
//
//    @Column(name = "favicon_url")
//    private String faviconUrl;
//
//    @Column(name = "email")
//    private String email;
//
//    @Column(name = "phone")
//    private String phone;
//
//    @Column(name = "address")
//    private String address;
//
//    @Column(name = "city")
//    private String city;
//
//    @Column(name = "state")
//    private String state;
//
//    @Column(name = "country")
//    private String country;
//
//    @Column(name = "zip_code")
//    private String zipCode;
//
//    @Column(name = "social_linkedin")
//    private String socialLinkedin;
//
//    @Column(name = "social_facebook")
//    private String socialFacebook;
//
//    @Column(name = "social_instagram")
//    private String socialInstagram;
//
//    @Column(name = "social_twitter")
//    private String socialTwitter;
//
//    @Column(name = "meta_title")
//    private String metaTitle;
//
//    @Column(name = "meta_description", columnDefinition = "TEXT")
//    private String metaDescription;
//
//    @Column(name = "meta_keywords")
//    private String metaKeywords;
//
//    @Column(name = "copyright_text")
//    private String copyrightText;
//
//    @Column(name = "contact_recipient_email")
//    private String contactRecipientEmail;
//
//    @Column(name = "projects_completed")
//    private String projectsCompleted;
//
//    @Column(name = "projects_completed_label")
//    private String projectsCompletedLabel;
//
//    @Column(name = "clients_served")
//    private String clientsServed;
//
//    @Column(name = "clients_served_label")
//    private String clientsServedLabel;
//
//    @Column(name = "years_experience")
//    private String yearsExperience;
//
//    @Column(name = "years_experience_label")
//    private String yearsExperienceLabel;
//
//    @Column(name = "team_members")
//    private String teamMembers;
//
//    @Column(name = "team_members_label")
//    private String teamMembersLabel;
//
//}
