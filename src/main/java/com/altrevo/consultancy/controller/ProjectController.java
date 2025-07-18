package com.altrevo.consultancy.controller;

import com.altrevo.consultancy.dto.ApiResponse;
import com.altrevo.consultancy.dto.ProjectSummary;
import com.altrevo.consultancy.entity.Project;
import com.altrevo.consultancy.entity.ProjectExternalTeamMember;
import com.altrevo.consultancy.entity.ProjectTeamMember;
import com.altrevo.consultancy.enums.ProjectStatus;
import com.altrevo.consultancy.enums.ProjectPriority;
import com.altrevo.consultancy.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.security.Principal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@Tag(name = "Projects", description = "Project management")
public class ProjectController {
    
    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
    
    @Autowired
    private ProjectService projectService;
    
    // All project endpoints are admin-only
    
    @GetMapping("/projects")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('EDITOR')")
    @Operation(summary = "Get all projects", description = "Retrieve all projects for admin management")
    public ResponseEntity<ApiResponse<List<Project>>> getAllProjects(
            @Parameter(description = "Filter by status")
            @RequestParam(required = false) ProjectStatus status,
            @Parameter(description = "Filter by priority")
            @RequestParam(required = false) ProjectPriority priority,
            @Parameter(description = "Filter by category")
            @RequestParam(required = false) String category,
            @Parameter(description = "Filter by client name")
            @RequestParam(required = false) String clientName,
            @Parameter(description = "Search keyword")
            @RequestParam(required = false) String search) {
        
        logger.info("Fetching all projects - status: {}, priority: {}, category: {}, client: {}",
                   status, priority, category, clientName);

        List<Project> projects;
        
        if (search != null && !search.trim().isEmpty()) {
            projects = projectService.searchProjects(search.trim());
        } else {
            projects = projectService.getAllProjects(status, priority, category, clientName);
        }
        
        return ResponseEntity.ok(ApiResponse.<List<Project>>builder()
                .success(true)
                .data(projects)
                .build());
    }
    
    @GetMapping("/projects/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('EDITOR')")
    @Operation(summary = "Get project by ID", description = "Retrieve project details by ID")
    public ResponseEntity<ApiResponse<Project>> getProjectById(
            @Parameter(description = "Project ID")
            @PathVariable Long id) {
        
        logger.info("Fetching project by ID: {}", id);
        
        Optional<Project> project = projectService.getProjectById(id);
        
        if (project.isPresent()) {
            return ResponseEntity.ok(ApiResponse.<Project>builder()
                    .success(true)
                    .data(project.get())
                    .build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/projects/code/{code}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('EDITOR')")
    @Operation(summary = "Get project by code", description = "Retrieve project details by project code")
    public ResponseEntity<ApiResponse<Project>> getProjectByCode(
            @Parameter(description = "Project code")
            @PathVariable String code) {
        
        logger.info("Fetching project by code: {}", code);
        
        Optional<Project> project = projectService.getProjectByProjectCode(code);
        
        if (project.isPresent()) {
            return ResponseEntity.ok(ApiResponse.<Project>builder()
                    .success(true)
                    .data(project.get())
                    .build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/projects")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @Operation(summary = "Create project", description = "Create a new project")
    public ResponseEntity<ApiResponse<Project>> createProject(
            @Valid @RequestBody Project project,
            Principal principal) {
        logger.info("Creating new project: {}", project.getName());
        // Set the created by user
        project.setCreatedBy(principal.getName());
        project.setUpdatedBy(principal.getName());

        // Ensure only new ProjectTeamMember instances are added
        if (project.getTeamMembers() != null && !project.getTeamMembers().isEmpty()) {
            List<ProjectTeamMember> newTeamMembers = project.getTeamMembers().stream().map(member -> {
                ProjectTeamMember newMember = new ProjectTeamMember();
                newMember.setMemberId(member.getMemberId());
                newMember.setName(member.getName());
                newMember.setRole(member.getRole());
                newMember.setAllocatedHours(member.getAllocatedHours());
                newMember.setHourlyRate(member.getHourlyRate());
                newMember.setIsActive(member.getIsActive());
                // project reference will be set in service
                return newMember;
            }).toList();
            project.setTeamMembers(newTeamMembers);
        }
        // Optionally, do the same for external team members if needed
        if (project.getExternalTeamMembers() != null && !project.getExternalTeamMembers().isEmpty()) {
            List<ProjectExternalTeamMember> newExternalMembers = project.getExternalTeamMembers().stream().map(member -> {
                ProjectExternalTeamMember newMember = new ProjectExternalTeamMember();
                newMember.setName(member.getName());
                newMember.setEmail(member.getEmail());
                newMember.setPhone(member.getPhone());
                newMember.setCompany(member.getCompany());
                newMember.setRole(member.getRole());
                newMember.setContractType(member.getContractType());
                newMember.setContractStartDate(member.getContractStartDate());
                newMember.setAllocatedHours(member.getAllocatedHours());
                newMember.setHourlyRate(member.getHourlyRate());
                newMember.setPaymentTerms(member.getPaymentTerms());
                newMember.setNotes(member.getNotes());
                newMember.setIsActive(member.getIsActive());
                // project reference will be set in service
                return newMember;
            }).toList();
            project.setExternalTeamMembers(newExternalMembers);
        }
        Project savedProject = projectService.createProject(project);
        return ResponseEntity.ok(ApiResponse.<Project>builder()
                .success(true)
                .message("Project created successfully")
                .data(savedProject)
                .build());
    }
    
    @PutMapping("/projects/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @Operation(summary = "Update project", description = "Update an existing project")
    public ResponseEntity<ApiResponse<Project>> updateProject(
            @Parameter(description = "Project ID")
            @PathVariable Long id,
            @Valid @RequestBody Project project,
            Principal principal) {
        
        logger.info("Updating project with ID: {}", id);
        logger.info("Incoming project team member IDs: {}", project.getTeamMembers() != null ? project.getTeamMembers().stream().map(m -> m.getId()).toList() : "null");
        logger.info("Incoming project external team member IDs: {}", project.getExternalTeamMembers() != null ? project.getExternalTeamMembers().stream().map(m -> m.getId()).toList() : "null");
        // Set the updated by user
        project.setUpdatedBy(principal.getName());
        
        try {
            Project updatedProject = projectService.updateProject(id, project);
            
            return ResponseEntity.ok(ApiResponse.<Project>builder()
                    .success(true)
                    .message("Project updated successfully")
                    .data(updatedProject)
                    .build());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/projects/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @Operation(summary = "Delete project", description = "Delete a project")
    public ResponseEntity<ApiResponse<Void>> deleteProject(
            @Parameter(description = "Project ID")
            @PathVariable Long id) {
        
        logger.info("Deleting project with ID: {}", id);
        
        try {
            projectService.deleteProject(id);
            
            return ResponseEntity.ok(ApiResponse.<Void>builder()
                    .success(true)
                    .message("Project deleted successfully")
                    .build());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/projects/stats")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @Operation(summary = "Get project statistics", description = "Retrieve project statistics for admin dashboard")
    public ResponseEntity<ApiResponse<ProjectService.ProjectStats>> getProjectStats() {
        logger.info("Fetching project statistics");
        
        ProjectService.ProjectStats stats = projectService.getProjectStats();
        
        return ResponseEntity.ok(ApiResponse.<ProjectService.ProjectStats>builder()
                .success(true)
                .data(stats)
                .build());
    }
    
    @GetMapping("/projects/filters")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('EDITOR')")
    @Operation(summary = "Get project filters", description = "Retrieve available project filters")
    public ResponseEntity<ApiResponse<ProjectService.ProjectFilters>> getProjectFilters() {
        logger.info("Fetching project filters");
        
        ProjectService.ProjectFilters filters = projectService.getProjectFilters();
        
        return ResponseEntity.ok(ApiResponse.<ProjectService.ProjectFilters>builder()
                .success(true)
                .data(filters)
                .build());
    }

    @GetMapping("/projects/summary")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @Operation(summary = "Get project summary", description = "Retrieve summary statistics for projects")
    public ResponseEntity<ApiResponse<ProjectSummary>> getProjectSummary(HttpServletRequest request) {
        ProjectSummary summary = projectService.getProjectSummary();
        return ResponseEntity.ok(ApiResponse.<ProjectSummary>builder()
                .success(true)
                .data(summary)
                .timestamp(ZonedDateTime.now(java.time.ZoneOffset.UTC).toLocalDateTime())
                .path(request.getRequestURI())
                .build());
    }
}
