package com.altrevo.consultancy.entity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class InMemoryJobStore {
    public static final Map<Long, Job> JOBS = new HashMap<>();
    public static long idCounter = 1L;

    static {
        Job job1 = new Job();
        job1.setId(idCounter++);
        job1.setTitle("Software Engineer");
        job1.setDepartment("Engineering");
        job1.setLocation("San Francisco, CA");
        job1.setType("Full-Time");
        job1.setLevel("Mid-Level");
        job1.setSalaryMin(80000.0);
        job1.setSalaryMax(120000.0);
        job1.setSalaryCurrency("USD");
        job1.setExperience(3);
        job1.setOpenings(2);
        job1.setDescription("Develop and maintain software applications.");
        job1.setRequirements(Arrays.asList("3+ years of experience in software development."));
        job1.setSkills(Arrays.asList("Java", "Spring Boot", "SQL"));
        job1.setBenefits(Arrays.asList("Health insurance", "401(k)"));
        job1.setIsOpen(true);
        job1.setIsPublic(true);
        job1.setFeatured(true);
        job1.setApplicationDeadline(LocalDateTime.of(2025, 12, 31, 23, 59));
        job1.setCreatedAt(LocalDateTime.now());
        job1.setUpdatedAt(LocalDateTime.now());
        JOBS.put(job1.getId(), job1);

        Job job2 = new Job();
        job2.setId(idCounter++);
        job2.setTitle("Data Scientist");
        job2.setDepartment("Data Science");
        job2.setLocation("New York, NY");
        job2.setType("Full-Time");
        job2.setLevel("Senior-Level");
        job2.setSalaryMin(100000.0);
        job2.setSalaryMax(150000.0);
        job2.setSalaryCurrency("USD");
        job2.setExperience(5);
        job2.setOpenings(1);
        job2.setDescription("Analyze and interpret complex data.");
        job2.setRequirements(Arrays.asList("5+ years of experience in data analysis."));
        job2.setSkills(Arrays.asList("Python", "R", "Machine Learning"));
        job2.setBenefits(Arrays.asList("Health insurance", "Stock options"));
        job2.setIsOpen(true);
        job2.setIsPublic(true);
        job2.setFeatured(false);
        job2.setApplicationDeadline(LocalDateTime.of(2025, 11, 30, 23, 59));
        job2.setCreatedAt(LocalDateTime.now());
        job2.setUpdatedAt(LocalDateTime.now());
        JOBS.put(job2.getId(), job2);

        Job job3 = new Job();
        job3.setId(idCounter++);
        job3.setTitle("Cloud Architect");
        job3.setDepartment("Cloud Services");
        job3.setLocation("Seattle, WA");
        job3.setType("Full-Time");
        job3.setLevel("Senior-Level");
        job3.setSalaryMin(120000.0);
        job3.setSalaryMax(160000.0);
        job3.setSalaryCurrency("USD");
        job3.setExperience(7);
        job3.setOpenings(1);
        job3.setDescription("Design and implement scalable cloud solutions.");
        job3.setRequirements(Arrays.asList("7+ years of experience in cloud architecture."));
        job3.setSkills(Arrays.asList("AWS", "Azure", "Kubernetes"));
        job3.setBenefits(Arrays.asList("Health insurance", "Remote work"));
        job3.setIsOpen(true);
        job3.setIsPublic(true);
        job3.setFeatured(true);
        job3.setApplicationDeadline(LocalDateTime.of(2025, 10, 31, 23, 59));
        job3.setCreatedAt(LocalDateTime.now());
        job3.setUpdatedAt(LocalDateTime.now());
        JOBS.put(job3.getId(), job3);

        Job job4 = new Job();
        job4.setId(idCounter++);
        job4.setTitle("DevOps Engineer");
        job4.setDepartment("Engineering");
        job4.setLocation("Austin, TX");
        job4.setType("Contract");
        job4.setLevel("Mid-Level");
        job4.setSalaryMin(90000.0);
        job4.setSalaryMax(110000.0);
        job4.setSalaryCurrency("USD");
        job4.setExperience(4);
        job4.setOpenings(3);
        job4.setDescription("Implement and manage CI/CD pipelines.");
        job4.setRequirements(Arrays.asList("4+ years of experience in DevOps."));
        job4.setSkills(Arrays.asList("Jenkins", "Docker", "Terraform"));
        job4.setBenefits(Arrays.asList("Flexible hours", "Health insurance"));
        job4.setIsOpen(true);
        job4.setIsPublic(true);
        job4.setFeatured(false);
        job4.setApplicationDeadline(LocalDateTime.of(2025, 9, 30, 23, 59));
        job4.setCreatedAt(LocalDateTime.now());
        job4.setUpdatedAt(LocalDateTime.now());
        JOBS.put(job4.getId(), job4);
    }
}
