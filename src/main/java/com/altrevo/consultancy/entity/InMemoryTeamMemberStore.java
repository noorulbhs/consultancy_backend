package com.altrevo.consultancy.entity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class InMemoryTeamMemberStore {
    public static final Map<Long, TeamMember> TEAM_MEMBERS = new HashMap<>();
    public static long idCounter = 1L;

    static {
        TeamMember m1 = new TeamMember();
        m1.setId(1L);
        m1.setName("John Smith");
        m1.setRole("Senior Cloud Architect");
        m1.setDepartment("Engineering");
        m1.setEmail("john.smith@altrevo.com");
        m1.setPhone("+1-555-0101");
        m1.setLinkedinUrl("https://linkedin.com/in/johnsmith");
        m1.setPhotoUrl("https://example.com/john-smith.jpg");
        m1.setBio("John is a seasoned cloud architect with 10+ years of experience in designing and implementing scalable cloud solutions.");
        m1.setEducation("MS Computer Science, Stanford University");
        m1.setExperience("10+ years");
        m1.setLocation("San Francisco, CA");
        m1.setCreatedAt(LocalDateTime.of(2025, 7, 16, 21, 2, 18));
        m1.setUpdatedAt(LocalDateTime.of(2025, 7, 16, 21, 2, 18));
        TEAM_MEMBERS.put(m1.getId(), m1);

        TeamMember m2 = new TeamMember();
        m2.setId(2L);
        m2.setName("Sarah Johnson");
        m2.setRole("DevOps Lead");
        m2.setDepartment("Engineering");
        m2.setEmail("sarah.johnson@altrevo.com");
        m2.setPhone("+1-555-0102");
        m2.setLinkedinUrl("https://linkedin.com/in/sarahjohnson");
        m2.setPhotoUrl("https://example.com/sarah-johnson.jpg");
        m2.setBio("Sarah specializes in DevOps automation and has led numerous successful CI/CD implementations.");
        m2.setEducation("BS Software Engineering, MIT");
        m2.setExperience("8+ years");
        m2.setLocation("Boston, MA");
        m2.setCreatedAt(LocalDateTime.of(2025, 7, 16, 21, 2, 18));
        m2.setUpdatedAt(LocalDateTime.of(2025, 7, 16, 21, 2, 18));
        TEAM_MEMBERS.put(m2.getId(), m2);

        TeamMember m3 = new TeamMember();
        m3.setId(3L);
        m3.setName("Michael Chen");
        m3.setRole("AI/ML Engineer");
        m3.setDepartment("Engineering");
        m3.setEmail("michael.chen@altrevo.com");
        m3.setPhone("+1-555-0103");
        m3.setLinkedinUrl("https://linkedin.com/in/michaelchen");
        m3.setPhotoUrl("https://example.com/michael-chen.jpg");
        m3.setBio("Michael is an expert in artificial intelligence and machine learning with a focus on practical business applications.");
        m3.setEducation("PhD Machine Learning, Carnegie Mellon");
        m3.setExperience("7+ years");
        m3.setLocation("Pittsburgh, PA");
        m3.setCreatedAt(LocalDateTime.of(2025, 7, 16, 21, 2, 18));
        m3.setUpdatedAt(LocalDateTime.of(2025, 7, 16, 21, 2, 18));
        TEAM_MEMBERS.put(m3.getId(), m3);

        TeamMember m4 = new TeamMember();
        m4.setId(4L);
        m4.setName("Emily Rodriguez");
        m4.setRole("Cybersecurity Consultant");
        m4.setDepartment("Security");
        m4.setEmail("emily.rodriguez@altrevo.com");
        m4.setPhone("+1-555-0104");
        m4.setLinkedinUrl("https://linkedin.com/in/emilyrodriguez");
        m4.setPhotoUrl("https://example.com/emily-rodriguez.jpg");
        m4.setBio("Emily is a cybersecurity expert specializing in enterprise security solutions and compliance.");
        m4.setEducation("MS Cybersecurity, George Washington University");
        m4.setExperience("9+ years");
        m4.setLocation("Washington, DC");
        m4.setCreatedAt(LocalDateTime.of(2025, 7, 16, 21, 2, 18));
        m4.setUpdatedAt(LocalDateTime.of(2025, 7, 16, 21, 2, 18));
        TEAM_MEMBERS.put(m4.getId(), m4);

        idCounter = 5L;
    }
}
