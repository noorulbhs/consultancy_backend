package com.altrevo.consultancy.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectSummary {
    private long totalProjects;
    private long activeProjects;
    private long completedProjects;
    private double totalBudget;
    private double totalActualCost;
    private double averageProgress;
    private long overdueMilestones;
}