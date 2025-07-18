package com.altrevo.consultancy.repository;

import com.altrevo.consultancy.entity.ProjectMilestone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface MilestoneRepository extends JpaRepository<ProjectMilestone, Long> {
    @Query("SELECT COUNT(m) FROM ProjectMilestone m WHERE m.dueDate < :today AND m.completed = false")
    long countOverdueMilestones(@Param("today") LocalDate today);

    @Query("SELECT COUNT(m) FROM ProjectMilestone m WHERE m.dueDate < :now AND m.completed = false")
    long countOverdueMilestones(@Param("now") java.time.LocalDateTime now);
}