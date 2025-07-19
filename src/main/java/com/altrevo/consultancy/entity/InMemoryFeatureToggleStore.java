package com.altrevo.consultancy.entity;

import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;

public class InMemoryFeatureToggleStore {
    public static final Map<String, FeatureToggle> FEATURE_TOGGLES = new HashMap<>();

    static {
        FeatureToggle f1 = new FeatureToggle();
        f1.setId("contact-form");
        f1.setName("Contact Form");
        f1.setDescription("Enable contact form on the contact page");
        f1.setEnabled(true);
        f1.setSection("contact");
        f1.setUpdatedBy("admin@altrevo.com");
        f1.setUpdatedAt(java.time.LocalDateTime.of(2025, 7, 16, 21, 2, 29));
        FEATURE_TOGGLES.put(f1.getId(), f1);

        FeatureToggle f2 = new FeatureToggle();
        f2.setId("cta-section");
        f2.setName("Call to Action");
        f2.setDescription("Display all call to action on the home page.");
        f2.setEnabled(false);
        f2.setSection("home");
        f2.setUpdatedBy("noorul@altrevo.com");
        f2.setUpdatedAt(java.time.LocalDateTime.of(2025, 7, 18, 8, 34, 36, 470403000));
        FEATURE_TOGGLES.put(f2.getId(), f2);

        FeatureToggle f3 = new FeatureToggle();
        f3.setId("featured-team-section");
        f3.setName("Meet Our Expert Team");
        f3.setDescription("Show featured team members on the home page");
        f3.setEnabled(false);
        f3.setSection("home");
        f3.setUpdatedBy("noorul@altrevo.com");
        f3.setUpdatedAt(java.time.LocalDateTime.of(2025, 7, 17, 19, 51, 20, 527913000));
        FEATURE_TOGGLES.put(f3.getId(), f3);

        FeatureToggle f4 = new FeatureToggle();
        f4.setId("navbar-blog");
        f4.setName("Blog Naviagtion");
        f4.setDescription("Display latest blog posts on the home page");
        f4.setEnabled(true);
        f4.setSection("navigation");
        f4.setUpdatedBy("noorul@altrevo.com");
        f4.setUpdatedAt(java.time.LocalDateTime.of(2025, 7, 17, 15, 19, 38, 674646000));
        FEATURE_TOGGLES.put(f4.getId(), f4);

        FeatureToggle f5 = new FeatureToggle();
        f5.setId("navbar-career");
        f5.setName("Career Navigation");
        f5.setDescription("Show career opportunities section");
        f5.setEnabled(false);
        f5.setSection("navigation");
        f5.setUpdatedBy("noorul@altrevo.com");
        f5.setUpdatedAt(java.time.LocalDateTime.of(2025, 7, 17, 19, 51, 17, 272308000));
        FEATURE_TOGGLES.put(f5.getId(), f5);

        FeatureToggle f6 = new FeatureToggle();
        f6.setId("stats-section");
        f6.setName("Our Track Record");
        f6.setDescription("Display statistics and metrics on the home page");
        f6.setEnabled(true);
        f6.setSection("home");
        f6.setUpdatedBy("noorul@altrevo.com");
        f6.setUpdatedAt(java.time.LocalDateTime.of(2025, 7, 17, 15, 27, 53, 459095000));
        FEATURE_TOGGLES.put(f6.getId(), f6);

        FeatureToggle f7 = new FeatureToggle();
        f7.setId("testimonials-section");
        f7.setName("What Our Clients Say");
        f7.setDescription("Display client testimonials on the home page");
        f7.setEnabled(true);
        f7.setSection("home");
        f7.setUpdatedBy("noorul@altrevo.com");
        f7.setUpdatedAt(java.time.LocalDateTime.of(2025, 7, 17, 15, 27, 49, 399038000));
        FEATURE_TOGGLES.put(f7.getId(), f7);

        FeatureToggle f8 = new FeatureToggle();
        f8.setId("why-choose-us-section");
        f8.setName("Why Choose Us");
        f8.setDescription("Show Company advantage and benefits");
        f8.setEnabled(true);
        f8.setSection("home");
        f8.setUpdatedBy("noorul@altrevo.com");
        f8.setUpdatedAt(java.time.LocalDateTime.of(2025, 7, 17, 15, 27, 51, 578295000));
        FEATURE_TOGGLES.put(f8.getId(), f8);
    }
}
