package com.altrevo.consultancy.entity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class InMemoryTestimonialStore {
    public static final Map<Long, Testimonial> TESTIMONIALS = new HashMap<>();
    public static long idCounter = 1L;

    static {
        Testimonial t1 = new Testimonial();
        t1.setId(1L);
        t1.setName("David Wilson");
        t1.setDesignation("CTO");
        t1.setCompany("TechCorp Solutions");
        t1.setCompanyLogo("https://example.com/techcorp-logo.png");
        t1.setMessage("Altrevo transformed our entire infrastructure with their cloud migration expertise. The project was completed on time and under budget with zero downtime.");
        t1.setRating(null);
        t1.setPhotoUrl("https://images.unsplash.com/photo-1580489944761-15a19d654956?w=150&h=150&fit=crop&crop=face&auto=format");
        t1.setPublished(true);
        t1.setFeatured(false);
        t1.setDate(LocalDate.of(2024, 1, 15));
        t1.setProjectType("Cloud Migration");
        t1.setLocation("San Francisco, CA");
        t1.setTags(Arrays.asList("cloud", "migration", "infrastructure"));
        t1.setSortOrder(0);
        TESTIMONIALS.put(t1.getId(), t1);

        Testimonial t2 = new Testimonial();
        t2.setId(2L);
        t2.setName("Lisa Chen");
        t2.setDesignation("CEO");
        t2.setCompany("InnovateTech");
        t2.setCompanyLogo("https://example.com/innovatetech-logo.png");
        t2.setMessage("Outstanding DevOps consulting that streamlined our development process. Our deployment frequency increased by 300% after working with Altrevo.");
        t2.setRating(null);
        t2.setPhotoUrl("");
        t2.setPublished(true);
        t2.setFeatured(false);
        t2.setDate(LocalDate.of(2024, 2, 20));
        t2.setProjectType("DevOps Strategy");
        t2.setLocation("Seattle, WA");
        t2.setTags(Arrays.asList("devops", "automation", "deployment"));
        t2.setSortOrder(0);
        TESTIMONIALS.put(t2.getId(), t2);

        Testimonial t3 = new Testimonial();
        t3.setId(3L);
        t3.setName("Robert Martinez");
        t3.setDesignation("VP Technology");
        t3.setCompany("GlobalCorp");
        t3.setCompanyLogo("https://example.com/globalcorp-logo.png");
        t3.setMessage("The AI implementation project exceeded our expectations. The custom machine learning models have improved our operational efficiency by 45%.");
        t3.setRating(null);
        t3.setPhotoUrl("");
        t3.setPublished(true);
        t3.setFeatured(false);
        t3.setDate(LocalDate.of(2024, 3, 10));
        t3.setProjectType("AI Implementation");
        t3.setLocation("Austin, TX");
        t3.setTags(Arrays.asList("ai", "machine learning", "efficiency"));
        t3.setSortOrder(0);
        TESTIMONIALS.put(t3.getId(), t3);

        Testimonial t4 = new Testimonial();
        t4.setId(4L);
        t4.setName("Amanda Taylor");
        t4.setDesignation("CISO");
        t4.setCompany("SecureFinance");
        t4.setCompanyLogo("https://example.com/securefinance-logo.png");
        t4.setMessage("Altrevo's cybersecurity assessment was comprehensive and actionable. They helped us achieve compliance and significantly improved our security posture.");
        t4.setRating(null);
        t4.setPhotoUrl("https://example.com/amanda-taylor.jpg");
        t4.setPublished(false);
        t4.setFeatured(false);
        t4.setDate(LocalDate.of(2024, 4, 5));
        t4.setProjectType("Cybersecurity Consulting");
        t4.setLocation("New York, NY");
        t4.setTags(Arrays.asList("security", "compliance", "assessment"));
        t4.setSortOrder(0);
        TESTIMONIALS.put(t4.getId(), t4);

        idCounter = 5L;
    }
}
