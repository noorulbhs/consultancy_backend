package com.altrevo.consultancy.entity;

import java.util.HashMap;
import java.util.Map;

public class InMemoryProjectStore {
    public static final Map<Long, Project> projects = new HashMap<>();
    public static long projectIdSequence = 7L;

    static {
        // Pre-populate with sample project matching sampleProject.md
        Project project = new Project();
        project.setId(6L);
        project.setProjectCode("PROJ-877520");
        project.setName("Banking");
        project.setDescription("I need to have banking project for my client.");
        project.setClientName("Atif");
        project.setClientEmail("atif@gmail.com");
        project.setClientPhone("9876543210");
        project.setClientCompany("ABC");
        project.setStartDate(java.time.LocalDateTime.parse("2025-07-17T00:00:00"));
        project.setEndDate(java.time.LocalDateTime.parse("2025-08-06T00:00:00"));
        project.setCreatedAt(java.time.LocalDateTime.parse("2025-07-17T13:24:37"));
        project.setUpdatedAt(java.time.LocalDateTime.parse("2025-07-17T13:24:37"));
        project.setCreatedBy("noorul@altrevo.com");
        project.setUpdatedBy("noorul@altrevo.com");
        project.setEstimatedBudget(100.0);
        project.setActualBudget(0.0);
        project.setStatus(com.altrevo.consultancy.enums.ProjectStatus.PLANNING);
        project.setPriority(com.altrevo.consultancy.enums.ProjectPriority.MEDIUM);
        project.setProgress(10);
        project.setCategory("Web Development");
        project.setTechnologies(java.util.Collections.singletonList("Java"));
        project.setProjectManager("Atif");
        project.setMilestones(new java.util.ArrayList<>());
        project.setDocuments(new java.util.ArrayList<>());
        project.setTeamMembers(new java.util.ArrayList<>());
        project.setExternalTeamMembers(new java.util.ArrayList<>());
        projects.put(project.getId(), project);
    }
}
