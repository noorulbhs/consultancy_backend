package com.altrevo.consultancy.controller;

import com.altrevo.consultancy.dto.ApiResponse;
import com.altrevo.consultancy.entity.TeamMember;
import com.altrevo.consultancy.service.TeamMemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Tag(name = "Team Members", description = "Team member management endpoints")
public class TeamMemberController {
    
    private final TeamMemberService teamMemberService;
    
    @GetMapping("/public/team")
    @Operation(summary = "Get all public team members", description = "Get all public team members (public access)")
    public ResponseEntity<ApiResponse<List<TeamMember>>> getPublicTeamMembers(
            @RequestParam(required = false) Boolean featured,
            @RequestParam(required = false) String department) {
        List<TeamMember> teamMembers = teamMemberService.getPublicTeamMembers(featured, department);
        return ResponseEntity.ok(ApiResponse.success(teamMembers));
    }
    
    @GetMapping("/public/team/{id}")
    @Operation(summary = "Get team member by ID", description = "Get detailed team member information (public access)")
    public ResponseEntity<ApiResponse<TeamMember>> getPublicTeamMember(@PathVariable Long id) {
        Optional<TeamMember> teamMember = teamMemberService.getTeamMemberById(id);
        if (teamMember.isPresent() && teamMember.get().getIsPublic()) {
            return ResponseEntity.ok(ApiResponse.success(teamMember.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/admin/team")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('EDITOR')")
    @Operation(summary = "Get all team members", description = "Get all team members including private (admin only)")
    public ResponseEntity<ApiResponse<List<TeamMember>>> getAllTeamMembers(
            @RequestParam(required = false) Boolean isPublic,
            @RequestParam(required = false) Boolean featured,
            @RequestParam(required = false) String department) {
        List<TeamMember> teamMembers = teamMemberService.getAllTeamMembers(isPublic, featured, department);
        return ResponseEntity.ok(ApiResponse.success(teamMembers));
    }
    
    @GetMapping("/admin/team/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('EDITOR')")
    @Operation(summary = "Get team member by ID", description = "Get detailed team member information (admin only)")
    public ResponseEntity<ApiResponse<TeamMember>> getTeamMember(@PathVariable Long id) {
        Optional<TeamMember> teamMember = teamMemberService.getTeamMemberById(id);
        if (teamMember.isPresent()) {
            return ResponseEntity.ok(ApiResponse.success(teamMember.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/admin/team")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @Operation(summary = "Create team member", description = "Create new team member (admin only)")
    public ResponseEntity<ApiResponse<TeamMember>> createTeamMember(@RequestBody TeamMember teamMember) {
        TeamMember createdTeamMember = teamMemberService.createTeamMember(teamMember);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Team member created successfully", createdTeamMember));
    }
    
    @PutMapping("/admin/team/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @Operation(summary = "Update team member", description = "Update existing team member (admin only)")
    public ResponseEntity<ApiResponse<TeamMember>> updateTeamMember(@PathVariable Long id, @RequestBody TeamMember teamMember) {
        TeamMember updatedTeamMember = teamMemberService.updateTeamMember(id, teamMember);
        return ResponseEntity.ok(ApiResponse.success("Team member updated successfully", updatedTeamMember));
    }
    
    @DeleteMapping("/admin/team/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @Operation(summary = "Delete team member", description = "Delete team member (admin only)")
    public ResponseEntity<ApiResponse<String>> deleteTeamMember(@PathVariable Long id) {
        teamMemberService.deleteTeamMember(id);
        return ResponseEntity.ok(ApiResponse.success("Team member deleted successfully", null));
    }
}
