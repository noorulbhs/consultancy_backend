package com.altrevo.consultancy.service;

import com.altrevo.consultancy.dto.ProjectSummary;
import com.altrevo.consultancy.entity.*;
import com.altrevo.consultancy.enums.ProjectStatus;
import com.altrevo.consultancy.enums.ProjectPriority;
import com.altrevo.consultancy.repository.ProjectInMemoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectService {
    
    private static final Logger logger = LoggerFactory.getLogger(ProjectService.class);
    
    private final ProjectInMemoryRepository projectRepository = new ProjectInMemoryRepository();
    
    // Admin methods (all project operations are admin-only)
    
    public List<Project> getAllProjects(ProjectStatus status, ProjectPriority priority, String category, String clientName) {
        logger.info("Fetching all projects - status: {}, priority: {}, category: {}, client: {}", status, priority, category, clientName);
        if (status != null && priority != null && category != null && clientName != null) {
            return projectRepository.findByStatusAndPriorityAndCategoryIgnoreCaseAndClientNameIgnoreCaseOrderByCreatedAtDesc(status, priority, category, clientName, Pageable.unpaged());
        } else if (status != null && priority != null && category != null) {
            return projectRepository.findByStatusAndPriorityAndCategoryIgnoreCaseOrderByCreatedAtDesc(status, priority, category, Pageable.unpaged());
        } else if (status != null && priority != null) {
            return projectRepository.findByStatusAndPriorityOrderByCreatedAtDesc(status, priority, Pageable.unpaged());
        } else if (status != null && category != null) {
            return projectRepository.findByStatusAndCategoryIgnoreCaseOrderByCreatedAtDesc(status, category, Pageable.unpaged());
        } else if (status != null && clientName != null) {
            return projectRepository.findByStatusAndClientNameIgnoreCaseOrderByCreatedAtDesc(status, clientName, Pageable.unpaged());
        } else if (status != null) {
            return projectRepository.findByStatusOrderByCreatedAtDesc(status, Pageable.unpaged());
        } else if (priority != null) {
            return projectRepository.findByPriorityOrderByCreatedAtDesc(priority, Pageable.unpaged());
        } else if (category != null) {
            return projectRepository.findByCategoryIgnoreCaseOrderByCreatedAtDesc(category, Pageable.unpaged());
        } else if (clientName != null) {
            return projectRepository.findByClientNameIgnoreCaseOrderByCreatedAtDesc(clientName, Pageable.unpaged());
        } else {
            return projectRepository.findAll();
        }
    }
    
    public Optional<Project> getProjectById(Long id) {
        logger.info("Fetching project by ID: {}", id);
        return projectRepository.findById(id);
    }
    
    public Optional<Project> getProjectByProjectCode(String projectCode) {
        logger.info("Fetching project by project code: {}", projectCode);
        return projectRepository.findByProjectCode(projectCode);
    }
    
    public Project createProject(Project project) {
        logger.info("Creating new project: {}", project.getName());

        // Generate project code if not provided
        if (project.getProjectCode() == null || project.getProjectCode().isEmpty()) {
            project.setProjectCode(generateProjectCode());
        }

        // Set project reference for each internal team member
        if (project.getTeamMembers() != null) {
            project.getTeamMembers().forEach(member -> member.setProject(project));
        }

        // Set project reference for each external team member
        if (project.getExternalTeamMembers() != null) {
            project.getExternalTeamMembers().forEach(member -> member.setProject(project));
        }

        // Set project reference for each milestone
        if (project.getMilestones() != null) {
            project.getMilestones().forEach(milestone -> milestone.setProject(project));
        }

        return projectRepository.save(project);
    }

    public Project updateProject(Long id, Project updatedProject) {
        logger.info("Updating project with ID: {}", id);
        return projectRepository.findById(id)
                .map(project -> {
                    project.setName(updatedProject.getName());
                    project.setDescription(updatedProject.getDescription());
                    project.setClientName(updatedProject.getClientName());
                    project.setClientEmail(updatedProject.getClientEmail());
                    project.setClientPhone(updatedProject.getClientPhone());
                    project.setClientCompany(updatedProject.getClientCompany());
                    project.setStartDate(updatedProject.getStartDate());
                    project.setEndDate(updatedProject.getEndDate());
                    project.setEstimatedBudget(updatedProject.getEstimatedBudget());
                    project.setActualBudget(updatedProject.getActualBudget());
                    project.setStatus(updatedProject.getStatus());
                    project.setPriority(updatedProject.getPriority());
                    project.setProgress(updatedProject.getProgress());
                    project.setCategory(updatedProject.getCategory());
                    project.setTechnologies(updatedProject.getTechnologies());
                    project.setProjectManager(updatedProject.getProjectManager());
                    project.setUpdatedBy(updatedProject.getUpdatedBy());

                    // Merge internal team members
                    if (updatedProject.getTeamMembers() != null) {
                        List<ProjectTeamMember> existing = project.getTeamMembers();
                        List<ProjectTeamMember> incoming = updatedProject.getTeamMembers();
                        // Remove missing
                        existing.removeIf(e -> incoming.stream().noneMatch(i -> i.getId() != null && i.getId().equals(e.getId())));
                        // Update existing and add new
                        for (ProjectTeamMember incomingMember : incoming) {
                            incomingMember.setProject(project);
                            if (incomingMember.getId() == null) {
                                existing.add(incomingMember);
                            } else {
                                ProjectTeamMember match = existing.stream().filter(e -> e.getId() != null && e.getId().equals(incomingMember.getId())).findFirst().orElse(null);
                                if (match != null) {
                                    match.setName(incomingMember.getName());
                                    match.setRole(incomingMember.getRole());
                                    match.setAllocatedHours(incomingMember.getAllocatedHours());
                                    match.setHourlyRate(incomingMember.getHourlyRate());
                                    match.setIsActive(incomingMember.getIsActive());
                                    match.setMemberId(incomingMember.getMemberId());
                                } else {
                                    existing.add(incomingMember);
                                }
                            }
                        }
                        project.setTeamMembers(existing);
                    }

                    // Merge external team members
                    if (updatedProject.getExternalTeamMembers() != null) {
                        List<ProjectExternalTeamMember> existing = project.getExternalTeamMembers();
                        List<ProjectExternalTeamMember> incoming = updatedProject.getExternalTeamMembers();
                        // Remove missing
                        existing.removeIf(e -> incoming.stream().noneMatch(i -> i.getId() != null && i.getId().equals(e.getId())));
                        // Update existing and add new
                        for (ProjectExternalTeamMember incomingMember : incoming) {
                            incomingMember.setProject(project);
                            if (incomingMember.getId() == null) {
                                existing.add(incomingMember);
                            } else {
                                ProjectExternalTeamMember match = existing.stream().filter(e -> e.getId() != null && e.getId().equals(incomingMember.getId())).findFirst().orElse(null);
                                if (match != null) {
                                    match.setName(incomingMember.getName());
                                    match.setEmail(incomingMember.getEmail());
                                    match.setPhone(incomingMember.getPhone());
                                    match.setCompany(incomingMember.getCompany());
                                    match.setRole(incomingMember.getRole());
                                    match.setContractType(incomingMember.getContractType());
                                    match.setContractStartDate(incomingMember.getContractStartDate());
                                    match.setAllocatedHours(incomingMember.getAllocatedHours());
                                    match.setHourlyRate(incomingMember.getHourlyRate());
                                    match.setPaymentTerms(incomingMember.getPaymentTerms());
                                    match.setNotes(incomingMember.getNotes());
                                    match.setIsActive(incomingMember.getIsActive());
                                } else {
                                    existing.add(incomingMember);
                                }
                            }
                        }
                        project.setExternalTeamMembers(existing);
                    }

                    // Merge milestones
                    if (updatedProject.getMilestones() != null) {
                        List<ProjectMilestone> existing = project.getMilestones();
                        List<ProjectMilestone> incoming = updatedProject.getMilestones();
                        // Remove missing
                        existing.removeIf(e -> incoming.stream().noneMatch(i -> i.getId() != null && i.getId().equals(e.getId())));
                        // Update existing and add new
                        for (ProjectMilestone incomingMilestone : incoming) {
                            incomingMilestone.setProject(project);
                            if (incomingMilestone.getId() == null) {
                                existing.add(incomingMilestone);
                            } else {
                                ProjectMilestone match = existing.stream().filter(e -> e.getId() != null && e.getId().equals(incomingMilestone.getId())).findFirst().orElse(null);
                                if (match != null) {
                                    match.setMilestoneCode(incomingMilestone.getMilestoneCode());
                                    match.setTitle(incomingMilestone.getTitle());
                                    match.setDescription(incomingMilestone.getDescription());
                                    match.setDueDate(incomingMilestone.getDueDate());
                                    match.setStatus(incomingMilestone.getStatus());
                                    match.setCompletedDate(incomingMilestone.getCompletedDate());
                                    match.setProgress(incomingMilestone.getProgress());
                                    match.setSortOrder(incomingMilestone.getSortOrder());
                                    match.setCompleted(incomingMilestone.isCompleted());
                                } else {
                                    existing.add(incomingMilestone);
                                }
                            }
                        }
                        project.setMilestones(existing);
                    }

                    return projectRepository.save(project);
                })
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
    }
    
    public void deleteProject(Long id) {
        logger.info("Deleting project with ID: {}", id);
        projectRepository.deleteById(id);
    }
    
    // Project search methods
    
    public List<Project> searchProjects(String keyword) {
        logger.info("Searching projects with keyword: {}", keyword);
        return projectRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrClientNameContainingIgnoreCaseOrderByCreatedAtDesc(keyword, keyword, keyword, Pageable.unpaged());
    }
    
    // Dashboard statistics
    
    public ProjectStats getProjectStats() {
        logger.info("Fetching project statistics");
        
        long totalProjects = projectRepository.count();
        long activeProjects = projectRepository.countByStatus(ProjectStatus.ACTIVE);
        long completedProjects = projectRepository.countByStatus(ProjectStatus.COMPLETED);
        long onHoldProjects = projectRepository.countByStatus(ProjectStatus.ON_HOLD);
        long cancelledProjects = projectRepository.countByStatus(ProjectStatus.CANCELLED);
        
        return new ProjectStats(totalProjects, activeProjects, completedProjects, onHoldProjects, cancelledProjects);
    }
    
    // Project filters
    
    public ProjectFilters getProjectFilters() {
        logger.info("Fetching project filters");
        
        List<String> categories = projectRepository.findDistinctCategories();
        List<String> projectManagers = projectRepository.findDistinctProjectManagers();
        List<String> clients = projectRepository.findDistinctClients();
        
        return new ProjectFilters(categories, projectManagers, clients);
    }
    
    // Utility methods
    
    private String generateProjectCode() {
        String prefix = "PROJ-";
        String timestamp = String.valueOf(System.currentTimeMillis());
        return prefix + timestamp.substring(timestamp.length() - 6);
    }
    
    // Inner classes for response DTOs
    
    public static class ProjectStats {
        public final long totalProjects;
        public final long activeProjects;
        public final long completedProjects;
        public final long onHoldProjects;
        public final long cancelledProjects;
        
        public ProjectStats(long totalProjects, long activeProjects, long completedProjects, 
                           long onHoldProjects, long cancelledProjects) {
            this.totalProjects = totalProjects;
            this.activeProjects = activeProjects;
            this.completedProjects = completedProjects;
            this.onHoldProjects = onHoldProjects;
            this.cancelledProjects = cancelledProjects;
        }
    }
    
    public static class ProjectFilters {
        public final List<String> categories;
        public final List<String> projectManagers;
        public final List<String> clients;
        
        public ProjectFilters(List<String> categories, List<String> projectManagers, List<String> clients) {
            this.categories = categories;
            this.projectManagers = projectManagers;
            this.clients = clients;
        }
    }

    public ProjectSummary getProjectSummary() {
        long totalProjects = projectRepository.count();
        long activeProjects = projectRepository.countByStatus(ProjectStatus.ACTIVE);
        long completedProjects = projectRepository.countByStatus(ProjectStatus.COMPLETED);
        double totalBudget = projectRepository.sumBudget();
        double totalActualCost = projectRepository.sumActualCost();
        double averageProgress = projectRepository.averageProgress();
        long overdueMilestones = projectRepository.countOverdueMilestones(LocalDateTime.now());

        return ProjectSummary.builder()
                .totalProjects(totalProjects)
                .activeProjects(activeProjects)
                .completedProjects(completedProjects)
                .totalBudget(totalBudget)
                .totalActualCost(totalActualCost)
                .averageProgress(averageProgress)
                .overdueMilestones(overdueMilestones)
                .build();
    }
}
