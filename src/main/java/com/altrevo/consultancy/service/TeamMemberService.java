package com.altrevo.consultancy.service;

import com.altrevo.consultancy.entity.TeamMember;
import com.altrevo.consultancy.repository.TeamMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamMemberService {
    
    private final TeamMemberRepository teamMemberRepository;
    
    public List<TeamMember> getAllPublicTeamMembers() {
        return teamMemberRepository.findPublicTeamMembersOrderBySortOrder(Pageable.unpaged());
    }
    
    public List<TeamMember> getFeaturedTeamMembers() {
        return teamMemberRepository.findFeaturedTeamMembers(Pageable.unpaged());
    }
    
    public List<TeamMember> getPublicTeamMembers(Boolean featured, String department) {
        if (department != null) {
            return teamMemberRepository.findByIsPublicTrueAndDepartment(department, Pageable.unpaged());
        } else {
            return teamMemberRepository.findByIsPublicTrue(Pageable.unpaged());
        }
    }
    
    public Optional<TeamMember> getTeamMemberById(Long id) {
        return teamMemberRepository.findById(id);
    }
    
    public List<TeamMember> getAllTeamMembers(Boolean isPublic, Boolean featured, String department) {
        if (isPublic != null && isPublic && department != null) {
            return teamMemberRepository.findByIsPublicTrueAndDepartment(department, Pageable.unpaged());
        } else if (isPublic != null && isPublic) {
            return teamMemberRepository.findByIsPublicTrue(Pageable.unpaged());
        } else {
            return teamMemberRepository.findAll();
        }
    }
    
    public TeamMember createTeamMember(TeamMember teamMember) {
        return teamMemberRepository.save(teamMember);
    }
    
    public TeamMember updateTeamMember(Long id, TeamMember teamMemberDetails) {
        TeamMember teamMember = teamMemberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team member not found with id: " + id));
        
        teamMember.setName(teamMemberDetails.getName());
        teamMember.setRole(teamMemberDetails.getRole());
        teamMember.setDepartment(teamMemberDetails.getDepartment());
        teamMember.setEmail(teamMemberDetails.getEmail());
        teamMember.setPhone(teamMemberDetails.getPhone());
        teamMember.setLinkedinUrl(teamMemberDetails.getLinkedinUrl());
        teamMember.setTwitterUrl(teamMemberDetails.getTwitterUrl());
        teamMember.setGithubUrl(teamMemberDetails.getGithubUrl());
        teamMember.setPhotoUrl(teamMemberDetails.getPhotoUrl());
        teamMember.setBio(teamMemberDetails.getBio());
        teamMember.setSkills(teamMemberDetails.getSkills());
        teamMember.setExperience(teamMemberDetails.getExperience());
        teamMember.setEducation(teamMemberDetails.getEducation());
        teamMember.setLocation(teamMemberDetails.getLocation());
        teamMember.setJoinDate(teamMemberDetails.getJoinDate());
        teamMember.setIsPublic(teamMemberDetails.getIsPublic());
        teamMember.setFeatured(teamMemberDetails.getFeatured());
        teamMember.setAchievements(teamMemberDetails.getAchievements());
        teamMember.setLanguages(teamMemberDetails.getLanguages());
        teamMember.setSpecializations(teamMemberDetails.getSpecializations());
        teamMember.setCertifications(teamMemberDetails.getCertifications());
        teamMember.setInterests(teamMemberDetails.getInterests());
        teamMember.setWorkStyle(teamMemberDetails.getWorkStyle());
        teamMember.setMotto(teamMemberDetails.getMotto());
        teamMember.setSortOrder(teamMemberDetails.getSortOrder());
        
        return teamMemberRepository.save(teamMember);
    }
    
    public void deleteTeamMember(Long id) {
        teamMemberRepository.deleteById(id);
    }
    
    public long getPublicCount() {
        return teamMemberRepository.countByIsPublicTrue();
    }
    
    public long getDepartmentCount(String department) {
        return teamMemberRepository.countByDepartment(department);
    }
    
    public boolean existsByEmail(String email) {
        return teamMemberRepository.existsByEmail(email);
    }
    
    // Statistics methods
    
    public TeamMemberStats getTeamMemberStats() {
        log.info("Fetching team member statistics");
        long totalMembers = teamMemberRepository.count();
        long publicMembers = teamMemberRepository.countByIsPublicTrue();
        long featuredMembers = teamMemberRepository.countByFeaturedTrue();
        long privateMembers = teamMemberRepository.countByIsPublicFalse();
        
        return new TeamMemberStats(totalMembers, publicMembers, featuredMembers, privateMembers);
    }
    
    // Inner class for response DTO
    
    public static class TeamMemberStats {
        public final long totalMembers;
        public final long publicMembers;
        public final long featuredMembers;
        public final long privateMembers;
        
        public TeamMemberStats(long totalMembers, long publicMembers, long featuredMembers, long privateMembers) {
            this.totalMembers = totalMembers;
            this.publicMembers = publicMembers;
            this.featuredMembers = featuredMembers;
            this.privateMembers = privateMembers;
        }
    }
}
