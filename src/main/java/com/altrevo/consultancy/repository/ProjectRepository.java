package com.altrevo.consultancy.repository;

import com.altrevo.consultancy.entity.Project;
import com.altrevo.consultancy.enums.ProjectStatus;
import com.altrevo.consultancy.enums.ProjectPriority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    
    // Basic status queries
    List<Project> findByStatus(ProjectStatus status);
    List<Project> findByStatus(ProjectStatus status, Pageable pageable);
    List<Project> findByStatusOrderByCreatedAtDesc(ProjectStatus status, Pageable pageable);
    long countByStatus(ProjectStatus status);
    
    // Priority queries
    List<Project> findByPriority(ProjectPriority priority);
    List<Project> findByPriority(ProjectPriority priority, Pageable pageable);
    List<Project> findByPriorityOrderByCreatedAtDesc(ProjectPriority priority, Pageable pageable);
    
    // Category queries
    List<Project> findByCategory(String category);
    List<Project> findByCategory(String category, Pageable pageable);
    List<Project> findByCategoryIgnoreCaseOrderByCreatedAtDesc(String category, Pageable pageable);
    
    // Client queries
    List<Project> findByClientName(String clientName);
    List<Project> findByClientNameIgnoreCaseOrderByCreatedAtDesc(String clientName, Pageable pageable);
    
    // Combined queries
    List<Project> findByStatusAndPriorityOrderByCreatedAtDesc(ProjectStatus status, ProjectPriority priority, Pageable pageable);
    List<Project> findByStatusAndCategoryIgnoreCaseOrderByCreatedAtDesc(ProjectStatus status, String category, Pageable pageable);
    List<Project> findByStatusAndClientNameIgnoreCaseOrderByCreatedAtDesc(ProjectStatus status, String clientName, Pageable pageable);
    List<Project> findByStatusAndPriorityAndCategoryIgnoreCaseOrderByCreatedAtDesc(ProjectStatus status, ProjectPriority priority, String category, Pageable pageable);
    List<Project> findByStatusAndPriorityAndCategoryIgnoreCaseAndClientNameIgnoreCaseOrderByCreatedAtDesc(ProjectStatus status, ProjectPriority priority, String category, String clientName, Pageable pageable);
    
    // Search queries
    List<Project> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrClientNameContainingIgnoreCaseOrderByCreatedAtDesc(
            String nameKeyword, String descriptionKeyword, String clientKeyword, Pageable pageable);
    
    // Project code queries
    Optional<Project> findByProjectCode(String projectCode);
    boolean existsByProjectCode(String projectCode);
    
    // Project manager queries
    @Query("SELECT p FROM Project p WHERE p.projectManager = :projectManager")
    List<Project> findByProjectManager(@Param("projectManager") String projectManager);
    
    // Filter queries
    @Query("SELECT DISTINCT p.category FROM Project p WHERE p.category IS NOT NULL")
    List<String> findDistinctCategories();
    
    @Query("SELECT DISTINCT p.projectManager FROM Project p WHERE p.projectManager IS NOT NULL")
    List<String> findDistinctProjectManagers();
    
    @Query("SELECT DISTINCT p.clientName FROM Project p WHERE p.clientName IS NOT NULL")
    List<String> findDistinctClients();
    
    // Statistics queries
    @Query("SELECT p.status, COUNT(p) FROM Project p GROUP BY p.status")
    List<Object[]> countByAllStatuses();
    
    @Query("SELECT AVG(p.progress) FROM Project p WHERE p.status = :status")
    Double getAverageProgressByStatus(@Param("status") ProjectStatus status);
    
    @Query("SELECT SUM(p.estimatedBudget) FROM Project p WHERE p.status = :status")
    Double getTotalEstimatedBudgetByStatus(@Param("status") ProjectStatus status);
    
    @Query("SELECT SUM(p.actualBudget) FROM Project p WHERE p.status = :status")
    Double getTotalActualBudgetByStatus(@Param("status") ProjectStatus status);

    @Query("SELECT COALESCE(SUM(p.estimatedBudget), 0) FROM Project p")
    double sumBudget();

    @Query("SELECT COALESCE(SUM(p.actualBudget), 0) FROM Project p")
    double sumActualCost();

    @Query("SELECT COALESCE(AVG(p.progress), 0) FROM Project p")
    double averageProgress();
}
