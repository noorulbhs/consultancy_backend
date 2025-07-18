package com.altrevo.consultancy.service;

import com.altrevo.consultancy.entity.SiteSettings;
import com.altrevo.consultancy.repository.SiteSettingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SiteSettingsService {
    
    private final SiteSettingsRepository siteSettingsRepository;

    public SiteSettings getSettings() {
//        return siteSettingsRepository.findById("main-settings")
//                .orElseGet(this::createDefaultSettings);
        Optional<SiteSettings> optionalSettings = siteSettingsRepository.findById("main-settings");
        SiteSettings settings = null;
        if (optionalSettings.isPresent()) {
            settings = optionalSettings.get();
        } else {
            settings = createDefaultSettings();
        }
        return settings;
    }

    public SiteSettings updateSettings(SiteSettings settings) {
        settings.setId("main-settings");
        return siteSettingsRepository.save(settings);
    }
    
    private SiteSettings createDefaultSettings() {
        SiteSettings settings = new SiteSettings();
        settings.setId("main-settings");
        settings.setVersion("2.0");
        settings.setCompanyName("Altrevo Tech Solutions");
        settings.setTagline("Innovative Technology Solutions for Modern Business");
        settings.setHeroSubtext("We help startups and enterprises build scalable, secure, and cloud-native systems.");
        settings.setDescription("Leading technology consultancy providing cutting-edge solutions, digital transformation, and innovation services to businesses worldwide.");
        settings.setLogoUrl("altrevo-logo.png");
        settings.setFaviconUrl("altrevo-favicon.png");
        settings.setEmail("contact@altrevo.com");
        settings.setPhone("+1 (555) 123-4567");
        settings.setAlternatePhone("+1 (555) 123-4568");
        settings.setAddress("123 Innovation Drive, Suite 100");
        settings.setCity("San Francisco");
        settings.setState("California");
        settings.setCountry("United States");
        settings.setZipCode("94107");
        settings.setBusinessHours(Map.of(
            "monday", "9:00 AM - 6:00 PM",
            "tuesday", "9:00 AM - 6:00 PM",
            "wednesday", "9:00 AM - 6:00 PM",
            "thursday", "9:00 AM - 6:00 PM",
            "friday", "9:00 AM - 6:00 PM",
            "saturday", "Closed",
            "sunday", "Closed"
        ));
        settings.setSocial(Map.of(
            "linkedin", "https://linkedin.com/company/altrevo",
            "twitter", "https://twitter.com/altrevo",
            "facebook", "https://facebook.com/altrevo",
            "instagram", "https://instagram.com/altrevo",
            "youtube", "https://youtube.com/altrevo",
            "github", "https://github.com/altrevo"
        ));
        settings.setSeo(Map.of(
            "metaTitle", "Altrevo Tech Solutions",
            "metaDescription", "Leading technology consultancy providing cutting-edge solutions.",
            "keywords", java.util.List.of("technology", "consultancy", "cloud", "digital transformation"),
            "googleAnalyticsId", "UA-XXXXX-Y",
            "facebookPixelId", "1234567890"
        ));
        settings.setFooter(Map.of(
            "copyrightText", "© 2025 Altrevo Tech Solutions. All rights reserved.",
            "quickLinks", java.util.List.of(Map.of("title", "Home", "url", "/"), Map.of("title", "About", "url", "/about")),
            "services", java.util.List.of(Map.of("title", "Cloud", "url", "/services/cloud")),
            "aboutLinks", java.util.List.of(Map.of("title", "Team", "url", "/about/team"))
        ));
        settings.setContactForm(Map.of(
            "recipientEmail", "contact@altrevo.com",
            "autoReplyEnabled", true,
            "autoReplySubject", "Thank you for contacting us!",
            "autoReplyMessage", "We have received your enquiry.",
            "subjectOptions", java.util.List.of(Map.of("value", "general", "label", "General", "enabled", true)),
            "serviceOptions", java.util.List.of(Map.of("value", "cloud", "label", "Cloud", "enabled", true))
        ));
        settings.setMaintenanceMode(false);
        settings.setMaintenanceMessage("");
        settings.setTheme("light");
        settings.setPrimaryColor("#1976d2");
        settings.setSecondaryColor("#424242");
        settings.setStatistics(Map.of(
            "projectsCompleted", Map.of("number", "100+", "label", "Projects", "icon", "project", "enabled", true),
            "happyClients", Map.of("number", "50+", "label", "Clients", "icon", "client", "enabled", true),
            "yearsExperience", Map.of("number", "10", "label", "Years", "icon", "experience", "enabled", true),
            "support", Map.of("number", "24/7", "label", "Support", "icon", "support", "enabled", true),
            "clientSatisfaction", Map.of("number", "99%", "label", "Satisfaction", "icon", "satisfaction", "enabled", true),
            "averageRating", Map.of("number", "4.9", "label", "Rating", "icon", "rating", "enabled", true),
            "teamMembers", Map.of("number", "25", "label", "Team", "icon", "team", "enabled", true),
            "successRate", Map.of("number", "98%", "label", "Success", "icon", "success", "enabled", true)
        ));
        settings.setLastUpdated("2025-07-16T00:00:00Z");
        settings.setUpdatedBy("admin");
        // created_at and updated_at are usually handled by JPA auditing, so not set here
        return siteSettingsRepository.save(settings);
    }
 }
