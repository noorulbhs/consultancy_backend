package com.altrevo.consultancy.repository;

import com.altrevo.consultancy.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    
    // Find all public jobs
    List<Job> findByIsPublicTrueOrderByCreatedAtDesc(Pageable pageable);
    
    // Find all open jobs
    List<Job> findByIsOpenTrueOrderByCreatedAtDesc(Pageable pageable);
    
    // Find all public and open jobs
    List<Job> findByIsPublicTrueAndIsOpenTrueOrderByCreatedAtDesc(Pageable pageable);
    
    // Find featured jobs
    List<Job> findByIsPublicTrueAndIsOpenTrueAndFeaturedTrueOrderByCreatedAtDesc(Pageable pageable);
    
    // Find jobs by department
    List<Job> findByIsPublicTrueAndIsOpenTrueAndDepartmentIgnoreCaseOrderByCreatedAtDesc(
            @Param("department") String department, Pageable pageable);
    
    // Find jobs by type
    List<Job> findByIsPublicTrueAndIsOpenTrueAndTypeIgnoreCaseOrderByCreatedAtDesc(
            @Param("type") String type, Pageable pageable);
    
    // Find jobs by location
    List<Job> findByIsPublicTrueAndIsOpenTrueAndLocationIgnoreCaseOrderByCreatedAtDesc(
            @Param("location") String location, Pageable pageable);
    
    // Find jobs by level
    List<Job> findByIsPublicTrueAndIsOpenTrueAndLevelIgnoreCaseOrderByCreatedAtDesc(
            @Param("level") String level, Pageable pageable);
    
    // Find jobs by salary range
    @Query("SELECT j FROM Job j WHERE j.isPublic = true AND j.isOpen = true " +
           "AND j.salaryMin >= :minSalary AND j.salaryMax <= :maxSalary " +
           "ORDER BY j.createdAt DESC")
    List<Job> findBySalaryRange(@Param("minSalary") Double minSalary, 
                                @Param("maxSalary") Double maxSalary, 
                                Pageable pageable);
    
    // Find jobs by experience
    @Query("SELECT j FROM Job j WHERE j.isPublic = true AND j.isOpen = true " +
           "AND j.experience <= :experience ORDER BY j.createdAt DESC")
    List<Job> findByMaxExperience(@Param("experience") Integer experience, Pageable pageable);
    
    // Search jobs by title or description
    @Query("SELECT j FROM Job j WHERE j.isPublic = true AND j.isOpen = true " +
           "AND (LOWER(j.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "OR LOWER(j.description) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
           "ORDER BY j.createdAt DESC")
    List<Job> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
    
    // Count open jobs
    long countByIsOpenTrue();
    
    // Count jobs by department
    long countByDepartmentIgnoreCase(String department);
    
    // Get all departments
    @Query("SELECT DISTINCT j.department FROM Job j WHERE j.isPublic = true AND j.isOpen = true")
    List<String> findAllDepartments();
    
    // Get all locations
    @Query("SELECT DISTINCT j.location FROM Job j WHERE j.isPublic = true AND j.isOpen = true")
    List<String> findAllLocations();
    
    // Get all job types
    @Query("SELECT DISTINCT j.type FROM Job j WHERE j.isPublic = true AND j.isOpen = true")
    List<String> findAllJobTypes();
    
    // Get all job levels
    @Query("SELECT DISTINCT j.level FROM Job j WHERE j.isPublic = true AND j.isOpen = true")
    List<String> findAllJobLevels();
}
