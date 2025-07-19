package com.altrevo.consultancy.entity;

import java.util.Map;
import java.util.List;
import java.time.LocalDateTime;

public class InMemorySiteSettingsStore {
    public static SiteSettings SITE_SETTINGS = null;

    static {
        SiteSettings settings = new SiteSettings();
        settings.setId("main-settings");
        settings.setVersion("2.0");
        settings.setCompanyName("Altrevo Tech Solutions");
        settings.setTagline("Innovative Technology Solutions and for Modern Business");
        settings.setHeroSubtext("We help startups and enterprises build scalable, secure, and cloud-native systems.");
        settings.setDescription("Leading technology consultancy providing cutting-edge solutions, digital transformation, and innovation services to businesses worldwide.");
        settings.setLogoUrl("altrevo-logo.png");
        settings.setFaviconUrl("altrevo-favicon.png");
        settings.setEmail("altrevo@altrevo.com");
        settings.setPhone("9876543210");
        settings.setAlternatePhone(null);
        settings.setAddress("Bank Road");
        settings.setCity("banglore");
        settings.setState("Karnatka");
        settings.setCountry(null);
        settings.setZipCode("211002");
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
            "linkedin", "https://www.linkedin.com/in/noorul-islam658",
            "twitter", "https://www.linkedin.com/in/noorul-islam658",
            "facebook", "https://www.linkedin.com/in/noorul-islam658",
            "instagram", "https://www.linkedin.com/in/noorul-islam658",
            "youtube", "https://www.linkedin.com/in/noorul-islam658",
            "github", "https://www.linkedin.com/in/noorul-islam658"
        ));
        settings.setSeo(Map.of(
            "metaTitle", "Altrevo Tech Solutions",
            "metaDescription", "Leading technology consultancy providing cutting-edge solutions.",
            "keywords", List.of("technology", "consultancy", "cloud", "digital transformation"),
            "googleAnalyticsId", "UA-XXXXX-Y",
            "facebookPixelId", "1234567890"
        ));
        settings.setFooter(Map.of(
            "copyrightText", "© 2025 Altrevo Tech Solutions. All rights reserved.",
            "quickLinks", List.of(),
            "services", List.of(),
            "aboutLinks", List.of()
        ));
        settings.setContactForm(Map.of(
            "recipientEmail", "altrvo@altrevo.com",
            "autoReplyEnabled", true,
            "autoReplySubject", "",
            "autoReplyMessage", "",
            "subjectOptions", List.of(),
            "serviceOptions", List.of()
        ));
        settings.setMaintenanceMode(false);
        settings.setMaintenanceMessage("");
        settings.setTheme("light");
        settings.setPrimaryColor("#1976d2");
        settings.setSecondaryColor("#424242");
        settings.setStatistics(Map.of(
            "projectsCompleted", Map.of("number", "101+", "label", "Projects", "icon", "project", "enabled", true),
            "happyClients", Map.of("number", "50+", "label", "Clients", "icon", "client", "enabled", true),
            "yearsExperience", Map.of("number", "10", "label", "Years", "icon", "experience", "enabled", true),
            "support", Map.of("number", "24/7", "label", "Support", "icon", "support", "enabled", true),
            "clientSatisfaction", Map.of("number", "99%", "label", "Satisfaction", "icon", "satisfaction", "enabled", false),
            "averageRating", Map.of("number", "4.9", "label", "Rating", "icon", "rating", "enabled", false),
            "teamMembers", Map.of("number", "25", "label", "Team", "icon", "team", "enabled", false),
            "successRate", Map.of("number", "98%", "label", "Success", "icon", "success", "enabled", false)
        ));
        settings.setLastUpdated("2025-07-16 21:20:13.000000");
        settings.setUpdatedBy(null);
        settings.setCreatedAt(LocalDateTime.of(2025, 7, 16, 21, 20, 13));
        settings.setUpdatedAt(LocalDateTime.of(2025, 7, 17, 20, 8, 42, 700857000));
        InMemorySiteSettingsStore.SITE_SETTINGS = settings;
    }
}
