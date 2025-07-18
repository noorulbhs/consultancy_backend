package com.altrevo.consultancy.controller;

import com.altrevo.consultancy.dto.ApiResponse;
import com.altrevo.consultancy.entity.Job;
import com.altrevo.consultancy.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/api/v1")
@Tag(name = "Jobs", description = "Career job listings management")
public class JobController {
    
    private static final Logger logger = LoggerFactory.getLogger(JobController.class);
    
    @Autowired
    private JobService jobService;
    
    // PUBLIC ENDPOINTS (No Authentication Required)
    
    @GetMapping("/public/careers")
    @Operation(summary = "Get all public job openings", description = "Retrieve all published job openings")
    public ResponseEntity<ApiResponse<List<Job>>> getPublicJobs(
            @Parameter(description = "Filter by department")
            @RequestParam(required = false) String department,
            @Parameter(description = "Filter by job type")
            @RequestParam(required = false) String type,
            @Parameter(description = "Filter by location")
            @RequestParam(required = false) String location,
            @Parameter(description = "Filter by experience level")
            @RequestParam(required = false) String level,
            @Parameter(description = "Filter featured jobs only")
            @RequestParam(required = false) Boolean featured,
            @Parameter(description = "Search keyword")
            @RequestParam(required = false) String search,
            @Parameter(description = "Minimum salary")
            @RequestParam(required = false) Double minSalary,
            @Parameter(description = "Maximum salary")
            @RequestParam(required = false) Double maxSalary,
            @Parameter(description = "Maximum experience required")
            @RequestParam(required = false) Integer maxExperience) {
        
        logger.info("Fetching public jobs");

        List<Job> jobs;
        
        if (search != null && !search.trim().isEmpty()) {
            jobs = jobService.searchJobs(search.trim());
        } else if (featured != null && featured) {
            jobs = jobService.getFeaturedJobs();
        } else if (department != null && !department.trim().isEmpty()) {
            jobs = jobService.getJobsByDepartment(department.trim());
        } else if (type != null && !type.trim().isEmpty()) {
            jobs = jobService.getJobsByType(type.trim());
        } else if (location != null && !location.trim().isEmpty()) {
            jobs = jobService.getJobsByLocation(location.trim());
        } else if (level != null && !level.trim().isEmpty()) {
            jobs = jobService.getJobsByLevel(level.trim());
        } else if (minSalary != null && maxSalary != null) {
            jobs = jobService.getJobsBySalaryRange(minSalary, maxSalary);
        } else if (maxExperience != null) {
            jobs = jobService.getJobsByMaxExperience(maxExperience);
        } else {
            jobs = jobService.getPublicJobs();
        }
        
        return ResponseEntity.ok(ApiResponse.<List<Job>>builder()
                .success(true)
                .data(jobs)
                .build());
    }
    
    @GetMapping("/public/careers/{id}")
    @Operation(summary = "Get job by ID", description = "Retrieve job details by ID")
    public ResponseEntity<ApiResponse<Job>> getPublicJobById(
            @Parameter(description = "Job ID")
            @PathVariable Long id) {
        
        logger.info("Fetching public job by ID: {}", id);
        
        Optional<Job> job = jobService.getPublicJobById(id);
        
        if (job.isPresent()) {
            return ResponseEntity.ok(ApiResponse.<Job>builder()
                    .success(true)
                    .data(job.get())
                    .build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/public/careers/filters")
    @Operation(summary = "Get job filters", description = "Retrieve available job filters")
    public ResponseEntity<ApiResponse<JobService.JobFilters>> getJobFilters() {
        logger.info("Fetching job filters");
        
        JobService.JobFilters filters = jobService.getJobFilters();
        
        return ResponseEntity.ok(ApiResponse.<JobService.JobFilters>builder()
                .success(true)
                .data(filters)
                .build());
    }
    
    // ADMIN ENDPOINTS (Authentication Required)
    
    @GetMapping("/admin/careers")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @Operation(summary = "Get all jobs (Admin)", description = "Retrieve all jobs for admin management")
    public ResponseEntity<ApiResponse<List<Job>>> getAllJobs() {
        logger.info("Fetching all jobs for admin");
        List<Job> jobs = jobService.getAllJobs();
        return ResponseEntity.ok(ApiResponse.<List<Job>>builder()
                .success(true)
                .data(jobs)
                .build());
    }
    
    @GetMapping("/admin/careers/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @Operation(summary = "Get job by ID (Admin)", description = "Retrieve job details by ID for admin")
    public ResponseEntity<ApiResponse<Job>> getJobById(
            @Parameter(description = "Job ID")
            @PathVariable Long id) {
        
        logger.info("Fetching job by ID for admin: {}", id);
        
        Optional<Job> job = jobService.getJobById(id);
        
        if (job.isPresent()) {
            return ResponseEntity.ok(ApiResponse.<Job>builder()
                    .success(true)
                    .data(job.get())
                    .build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/admin/careers")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @Operation(summary = "Create job", description = "Create a new job opening")
    public ResponseEntity<ApiResponse<Job>> createJob(
            @Valid @RequestBody Job job,
            Principal principal) {
        
        logger.info("Creating new job: {}", job.getTitle());
        
        // Set the created by user
        job.setCreatedBy(principal.getName());
        job.setUpdatedBy(principal.getName());
        
        Job savedJob = jobService.createJob(job);
        
        return ResponseEntity.ok(ApiResponse.<Job>builder()
                .success(true)
                .message("Job created successfully")
                .data(savedJob)
                .build());
    }
    
    @PutMapping("/admin/careers/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @Operation(summary = "Update job", description = "Update an existing job opening")
    public ResponseEntity<ApiResponse<Job>> updateJob(
            @Parameter(description = "Job ID")
            @PathVariable Long id,
            @Valid @RequestBody Job job,
            Principal principal) {
        
        logger.info("Updating job with ID: {}", id);
        
        // Set the updated by user
        job.setUpdatedBy(principal.getName());
        
        try {
            Job updatedJob = jobService.updateJob(id, job);
            
            return ResponseEntity.ok(ApiResponse.<Job>builder()
                    .success(true)
                    .message("Job updated successfully")
                    .data(updatedJob)
                    .build());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/admin/careers/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @Operation(summary = "Delete job", description = "Delete a job opening")
    public ResponseEntity<ApiResponse<Void>> deleteJob(
            @Parameter(description = "Job ID")
            @PathVariable Long id) {
        
        logger.info("Deleting job with ID: {}", id);
        
        try {
            jobService.deleteJob(id);
            
            return ResponseEntity.ok(ApiResponse.<Void>builder()
                    .success(true)
                    .message("Job deleted successfully")
                    .build());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/admin/careers/stats")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @Operation(summary = "Get job statistics", description = "Retrieve job statistics for admin dashboard")
    public ResponseEntity<ApiResponse<JobService.JobStats>> getJobStats() {
        logger.info("Fetching job statistics");
        
        JobService.JobStats stats = jobService.getJobStats();
        
        return ResponseEntity.ok(ApiResponse.<JobService.JobStats>builder()
                .success(true)
                .data(stats)
                .build());
    }
}
