package com.altrevo.consultancy.repository;

import com.altrevo.consultancy.entity.TeamMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
    
    List<TeamMember> findByIsPublicTrue();
    
    List<TeamMember> findByIsPublicTrueAndFeaturedTrue();
    
    List<TeamMember> findByDepartment(String department);
    
    List<TeamMember> findByIsPublicTrue(Pageable pageable);
    
    List<TeamMember> findByIsPublicTrueAndDepartment(String department, Pageable pageable);
    
    @Query("SELECT tm FROM TeamMember tm WHERE tm.isPublic = true ORDER BY tm.sortOrder ASC, tm.createdAt DESC")
    List<TeamMember> findPublicTeamMembersOrderBySortOrder(Pageable pageable);
    
    @Query("SELECT tm FROM TeamMember tm WHERE tm.isPublic = true AND tm.featured = true ORDER BY tm.sortOrder ASC, tm.createdAt DESC")
    List<TeamMember> findFeaturedTeamMembers(Pageable pageable);
    
    // Count methods
    long countByIsPublicTrue();
    long countByIsPublicFalse();
    long countByFeaturedTrue();
    long countByFeaturedFalse();
    long countByDepartment(String department);
    
    boolean existsByEmail(String email);
}
