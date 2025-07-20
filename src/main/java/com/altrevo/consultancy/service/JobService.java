package com.altrevo.consultancy.service;

import com.altrevo.consultancy.entity.Job;
import com.altrevo.consultancy.repository.JobInMemoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
//@Transactional
public class JobService {
    
    private static final Logger logger = LoggerFactory.getLogger(JobService.class);
    
    @Autowired
    private JobInMemoryRepository jobRepository;

    // Public methods (no authentication required)
    
    public List<Job> getPublicJobs() {
        logger.info("Fetching public jobs");
        return jobRepository.findByIsPublicTrueAndIsOpenTrueOrderByCreatedAtDesc();
    }
    
    public List<Job> getFeaturedJobs() {
        logger.info("Fetching featured jobs");
        return jobRepository.findByIsPublicTrueAndIsOpenTrueAndFeaturedTrueOrderByCreatedAtDesc();
    }
    
    public List<Job> getJobsByDepartment(String department) {
        logger.info("Fetching jobs by department: {}", department);
        return jobRepository.findByIsPublicTrueAndIsOpenTrueAndDepartmentIgnoreCaseOrderByCreatedAtDesc(department);
    }
    
    public List<Job> getJobsByType(String type) {
        logger.info("Fetching jobs by type: {}", type);
        return jobRepository.findByIsPublicTrueAndIsOpenTrueAndTypeIgnoreCaseOrderByCreatedAtDesc(type);
    }
    
    public List<Job> getJobsByLocation(String location) {
        logger.info("Fetching jobs by location: {}", location);
        return jobRepository.findByIsPublicTrueAndIsOpenTrueAndLocationIgnoreCaseOrderByCreatedAtDesc(location);
    }
    
    public List<Job> getJobsByLevel(String level) {
        logger.info("Fetching jobs by level: {}", level);
        return jobRepository.findByIsPublicTrueAndIsOpenTrueAndLevelIgnoreCaseOrderByCreatedAtDesc(level);
    }
    
    public List<Job> getJobsBySalaryRange(Double minSalary, Double maxSalary) {
        logger.info("Fetching jobs by salary range: {}-{}", minSalary, maxSalary);
        return jobRepository.findBySalaryRange(minSalary, maxSalary);
    }
    
    public List<Job> getJobsByMaxExperience(Integer experience) {
        logger.info("Fetching jobs by max experience: {}", experience);
        return jobRepository.findByMaxExperience(experience);
    }
    
    public List<Job> searchJobs(String keyword) {
        logger.info("Searching jobs with keyword: {}", keyword);
        return jobRepository.findByKeyword(keyword);
    }
    
    public Optional<Job> getPublicJobById(Long id) {
        logger.info("Fetching public job by ID: {}", id);
        return jobRepository.findById(id)
                .filter(job -> job.getIsPublic() && job.getIsOpen());
    }
    
    public JobFilters getJobFilters() {
        logger.info("Fetching job filters");
        return new JobFilters(
                jobRepository.findAllDepartments(),
                jobRepository.findAllLocations(),
                jobRepository.findAllJobTypes(),
                jobRepository.findAllJobLevels()
        );
    }
    
    // Admin methods (authentication required)
    
    public List<Job> getAllJobs() {
        logger.info("Fetching all jobs for admin");
        return jobRepository.findAll();
    }
    
    public Optional<Job> getJobById(Long id) {
        logger.info("Fetching job by ID for admin: {}", id);
        return jobRepository.findById(id);
    }
    
    public Job createJob(Job job) {
        logger.info("Creating new job: {}", job.getTitle());
        return jobRepository.save(job);
    }
    
    public Job updateJob(Long id, Job updatedJob) {
        logger.info("Updating job with ID: {}", id);
        return jobRepository.findById(id)
                .map(job -> {
                    job.setTitle(updatedJob.getTitle());
                    job.setDepartment(updatedJob.getDepartment());
                    job.setLocation(updatedJob.getLocation());
                    job.setType(updatedJob.getType());
                    job.setLevel(updatedJob.getLevel());
                    job.setSalaryMin(updatedJob.getSalaryMin());
                    job.setSalaryMax(updatedJob.getSalaryMax());
                    job.setSalaryCurrency(updatedJob.getSalaryCurrency());
                    job.setExperience(updatedJob.getExperience());
                    job.setOpenings(updatedJob.getOpenings());
                    job.setDescription(updatedJob.getDescription());
                    job.setRequirements(updatedJob.getRequirements());
                    job.setSkills(updatedJob.getSkills());
                    job.setBenefits(updatedJob.getBenefits());
                    job.setIsOpen(updatedJob.getIsOpen());
                    job.setIsPublic(updatedJob.getIsPublic());
                    job.setFeatured(updatedJob.getFeatured());
                    job.setApplicationDeadline(updatedJob.getApplicationDeadline());
                    job.setUpdatedBy(updatedJob.getUpdatedBy());
                    return jobRepository.save(job);
                })
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));
    }
    
    public void deleteJob(Long id) {
        logger.info("Deleting job with ID: {}", id);
        jobRepository.deleteById(id);
    }
    
    // Statistics methods
    
    public JobStats getJobStats() {
        logger.info("Fetching job statistics");
        long totalJobs = jobRepository.count();
        long openJobs = jobRepository.countByIsOpenTrue();
        
        return new JobStats(totalJobs, openJobs);
    }
    
    // Inner classes for response DTOs
    
    public static class JobFilters {
        public final List<String> departments;
        public final List<String> locations;
        public final List<String> types;
        public final List<String> levels;
        
        public JobFilters(List<String> departments, List<String> locations, 
                         List<String> types, List<String> levels) {
            this.departments = departments;
            this.locations = locations;
            this.types = types;
            this.levels = levels;
        }
    }
    
    public static class JobStats {
        public final long totalJobs;
        public final long openJobs;
        
        public JobStats(long totalJobs, long openJobs) {
            this.totalJobs = totalJobs;
            this.openJobs = openJobs;
        }
    }
}
