package com.altrevo.consultancy.entity;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryStaticPageStore {
    public static final Map<String, StaticPage> STATIC_PAGES = new HashMap<>();
    static {
        StaticPage aboutMission = new StaticPage();
        aboutMission.setId("about-mission");
        aboutMission.setTitle("About US");
        aboutMission.setContent("<p>To deliver reliable and scalable digital solutions tailored to every business need.</p><p>We believe in empowering organizations through innovative technology that drives growth, efficiency, and competitive advantage in the digital age.</p>");
        aboutMission.setCategory("About Us");
        aboutMission.setStatus("published");
        aboutMission.setLastUpdated(Instant.parse("2025-07-17T18:18:54.565893Z"));
        aboutMission.setMetaDescription("Our mission is to deliver reliable and scalable digital solutions for every business.");
        aboutMission.setKeywords(List.of("mission", "digital solutions", "business technology", "scalable solutions"));
        STATIC_PAGES.put(aboutMission.getId(), aboutMission);

        StaticPage aboutStory = new StaticPage();
        aboutStory.setId("about-story");
        aboutStory.setTitle("About Us");
        aboutStory.setContent("<p>At Altrevo Tech Solutions, we empower product companies, startups, and fast-growing businesses to scale quickly — without the overhead of expanding internal teams. Our expertise spans cloud deployment, infrastructure optimization, data migration, AI and data analytics, cybersecurity, and end-to-end website development — from design to deployment, with ongoing support and enhancements. Driven by a team that understands both code and commerce, Altrevo bridges the gap between technical excellence and business outcomes — bringing deep insight, speed, and strategic clarity to every engagement. Whether you're building a new platform or modernizing legacy systems, we deliver cost-effective, AI-enabled, and time-sensitive solutions that help you move fast and scale smart. Built on the pillars of innovation, trust, and affordability, we partner with clients to solve real problems — not just write code.</p>");
        aboutStory.setCategory("About Us");
        aboutStory.setStatus("published");
        aboutStory.setLastUpdated(Instant.parse("2025-07-17T18:18:32.109569Z"));
        aboutStory.setMetaDescription("Learn about Altrevo's journey and commitment to delivering exceptional technology solutions.");
        aboutStory.setKeywords(List.of("technology consultancy", "technology solutions", "software development", "digital transformation"));
        STATIC_PAGES.put(aboutStory.getId(), aboutStory);

        StaticPage aboutVision = new StaticPage();
        aboutVision.setId("about-vision");
        aboutVision.setTitle("About Us");
        aboutVision.setContent("<p>To evolve into a cutting-edge product company, delivering intelligent, AI-powered solutions that help businesses adapt, scale, and lead in a fast-changing digital world.</p>");
        aboutVision.setCategory("About Us");
        aboutVision.setStatus("published");
        aboutVision.setLastUpdated(Instant.parse("2025-07-17T18:19:02.740720Z"));
        aboutVision.setMetaDescription("Our vision is to become a global leader in technology consultancy through innovation.");
        aboutVision.setKeywords(List.of("vision", "global leadership", "innovation", "technology consultancy"));
        STATIC_PAGES.put(aboutVision.getId(), aboutVision);

        StaticPage privacyPolicy = new StaticPage();
        privacyPolicy.setId("privacy-policy");
        privacyPolicy.setTitle("Legal");
        privacyPolicy.setContent("<p>Welcome to WhatBytes (\"Company\", \"we\", \"our\", or \"us\"). We are committed to protecting your personal information and your right to privacy. If you have any questions about this Privacy Policy or our practices with regard to your personal information, please contact us at info@whatbytes.com.</p>...<p>If you have any questions about this Privacy Policy, you can contact us at:</p><p>WhatBytes Technologies (OPC) Private Limited</p><p>235 Binnamangala 2nd floor, 13th Cross Road 2nd Stage,</p><p>Indiranagar, Bangalore, Karnataka, India, 560038</p><p>Email: info@whatbytes.com</p>");
        privacyPolicy.setCategory("Legal");
        privacyPolicy.setStatus("published");
        privacyPolicy.setLastUpdated(Instant.parse("2025-07-18T10:17:30.926556Z"));
        privacyPolicy.setMetaDescription("Privacy policy for ITConsult - how we collect and use your information.");
        privacyPolicy.setKeywords(List.of("privacy", "policy", "data protection", "information security"));
        STATIC_PAGES.put(privacyPolicy.getId(), privacyPolicy);

        StaticPage termsOfService = new StaticPage();
        termsOfService.setId("terms-of-service");
        termsOfService.setTitle("Legal");
        termsOfService.setContent("<h2>Terms of Service</h2><p>Last updated: January 15, 2024</p><h3>Acceptance of Terms</h3><p>By accessing and using our services, you accept and agree to be bound by the terms and provision of this agreement.</p>...<p>If you have any questions about this Privacy Policy, you can contact us at:</p><p>WhatBytes Technologies (OPC) Private Limited</p><p>235 Binnamangala 2nd floor, 13th Cross Road 2nd Stage,</p><p>Indiranagar, Bangalore, Karnataka, India, 560038</p><p>Email: info@whatbytes.com</p>");
        termsOfService.setCategory("Legal");
        termsOfService.setStatus("published");
        termsOfService.setLastUpdated(Instant.parse("2024-01-15T00:00:00.000000Z"));
        termsOfService.setMetaDescription("Terms of service for ITConsult - rules and guidelines for using our services.");
        termsOfService.setKeywords(List.of("terms", "service", "agreement", "legal", "conditions"));
        STATIC_PAGES.put(termsOfService.getId(), termsOfService);
    }
}

