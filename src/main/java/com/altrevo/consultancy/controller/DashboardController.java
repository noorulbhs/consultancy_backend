package com.altrevo.consultancy.controller;

import com.altrevo.consultancy.dto.ApiResponse;
import com.altrevo.consultancy.entity.AllDocument;
import com.altrevo.consultancy.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@Tag(name = "Dashboard", description = "Admin dashboard analytics")
public class DashboardController {
    
    private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);
    
    @Autowired
    private ServiceService serviceService;
    
    @Autowired
    private TeamMemberService teamMemberService;
    
    @Autowired
    private TestimonialService testimonialService;
    
    @Autowired
    private EnquiryService enquiryService;
    
    @Autowired
    private BlogService blogService;
    
    @Autowired
    private JobService jobService;
    
    @Autowired
    private ProjectService projectService;

    @Autowired
    private SiteSettingsService siteSettingsService;

    @Autowired
    private StaticPageService staticPageService;
    
    @Autowired
    private FeatureToggleService featureToggleService;

    @Autowired
    private AdminUserService adminUserService;

    @GetMapping("/dashboard/stats")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @Operation(summary = "Get dashboard statistics", description = "Retrieve comprehensive dashboard statistics")
    public ResponseEntity<ApiResponse<DashboardStats>> getDashboardStats() {
        logger.info("Fetching dashboard statistics");
        
        // Get available statistics
        ServiceService.ServiceStats serviceStats = serviceService.getServiceStats();
        TeamMemberService.TeamMemberStats teamStats = teamMemberService.getTeamMemberStats();
        TestimonialService.TestimonialStats testimonialStats = testimonialService.getTestimonialStats();
        EnquiryService.EnquiryStats enquiryStats = enquiryService.getEnquiryStats();
        BlogService.BlogStats blogStats = blogService.getBlogStats();
        JobService.JobStats jobStats = jobService.getJobStats();
        ProjectService.ProjectStats projectStats = projectService.getProjectStats();
        
        // Create comprehensive dashboard stats
        DashboardStats dashboardStats = DashboardStats.builder()
                .services(serviceStats)
                .teamMembers(teamStats)
                .testimonials(testimonialStats)
                .enquiries(enquiryStats)
                .blogs(blogStats)
                .jobs(jobStats)
                .projects(projectStats)
                .features(Map.of(
                    "total", featureToggleService.getTotalCount(),
                    "enabled", featureToggleService.getEnabledCount(),
                    "disabled", featureToggleService.getDisabledCount()
                ))
                .build();
        
        return ResponseEntity.ok(ApiResponse.<DashboardStats>builder()
                .success(true)
                .data(dashboardStats)
                .build());
    }
    
    @GetMapping("/dashboard/overview")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @Operation(summary = "Get dashboard overview", description = "Retrieve quick overview statistics")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getDashboardOverview() {
        logger.info("Fetching dashboard overview");
        
        Map<String, Object> overview = new HashMap<>();
        
        // Get statistics
        ServiceService.ServiceStats serviceStats = serviceService.getServiceStats();
        TeamMemberService.TeamMemberStats teamStats = teamMemberService.getTeamMemberStats();
        TestimonialService.TestimonialStats testimonialStats = testimonialService.getTestimonialStats();
        EnquiryService.EnquiryStats enquiryStats = enquiryService.getEnquiryStats();
        BlogService.BlogStats blogStats = blogService.getBlogStats();
        JobService.JobStats jobStats = jobService.getJobStats();
        ProjectService.ProjectStats projectStats = projectService.getProjectStats();
        
        // Add key metrics
        overview.put("totalServices", serviceStats.totalServices);
        overview.put("activeServices", serviceStats.activeServices);
        overview.put("totalTeamMembers", teamStats.totalMembers);
        overview.put("publicTeamMembers", teamStats.publicMembers);
        overview.put("totalTestimonials", testimonialStats.totalTestimonials);
        overview.put("publishedTestimonials", testimonialStats.publishedTestimonials);
        overview.put("totalEnquiries", enquiryStats.totalEnquiries);
        overview.put("unreadEnquiries", enquiryStats.unreadEnquiries);
        overview.put("totalBlogs", blogStats.totalBlogs);
        overview.put("publishedBlogs", blogStats.publishedBlogs);
        overview.put("totalJobs", jobStats.totalJobs);
        overview.put("openJobs", jobStats.openJobs);
        overview.put("totalProjects", projectStats.totalProjects);
        overview.put("activeProjects", projectStats.activeProjects);
        
        // Add feature toggle stats
        overview.put("totalFeatures", featureToggleService.getTotalCount());
        overview.put("enabledFeatures", featureToggleService.getEnabledCount());
        overview.put("disabledFeatures", featureToggleService.getDisabledCount());
        
        return ResponseEntity.ok(ApiResponse.<Map<String, Object>>builder()
                .success(true)
                .data(overview)
                .build());
    }
    
    // Inner class for comprehensive dashboard stats
    public static class DashboardStats {
        public final ServiceService.ServiceStats services;
        public final TeamMemberService.TeamMemberStats teamMembers;
        public final TestimonialService.TestimonialStats testimonials;
        public final EnquiryService.EnquiryStats enquiries;
        public final BlogService.BlogStats blogs;
        public final JobService.JobStats jobs;
        public final ProjectService.ProjectStats projects;
        public final Map<String, Long> features;
        
        private DashboardStats(ServiceService.ServiceStats services,
                              TeamMemberService.TeamMemberStats teamMembers,
                              TestimonialService.TestimonialStats testimonials,
                              EnquiryService.EnquiryStats enquiries,
                              BlogService.BlogStats blogs,
                              JobService.JobStats jobs,
                              ProjectService.ProjectStats projects,
                              Map<String, Long> features) {
            this.services = services;
            this.teamMembers = teamMembers;
            this.testimonials = testimonials;
            this.enquiries = enquiries;
            this.blogs = blogs;
            this.jobs = jobs;
            this.projects = projects;
            this.features = features;
        }
        
        public static DashboardStatsBuilder builder() {
            return new DashboardStatsBuilder();
        }
        
        public static class DashboardStatsBuilder {
            private ServiceService.ServiceStats services;
            private TeamMemberService.TeamMemberStats teamMembers;
            private TestimonialService.TestimonialStats testimonials;
            private EnquiryService.EnquiryStats enquiries;
            private BlogService.BlogStats blogs;
            private JobService.JobStats jobs;
            private ProjectService.ProjectStats projects;
            private Map<String, Long> features;
            
            public DashboardStatsBuilder services(ServiceService.ServiceStats services) {
                this.services = services;
                return this;
            }
            
            public DashboardStatsBuilder teamMembers(TeamMemberService.TeamMemberStats teamMembers) {
                this.teamMembers = teamMembers;
                return this;
            }
            
            public DashboardStatsBuilder testimonials(TestimonialService.TestimonialStats testimonials) {
                this.testimonials = testimonials;
                return this;
            }
            
            public DashboardStatsBuilder enquiries(EnquiryService.EnquiryStats enquiries) {
                this.enquiries = enquiries;
                return this;
            }
            
            public DashboardStatsBuilder blogs(BlogService.BlogStats blogs) {
                this.blogs = blogs;
                return this;
            }
            
            public DashboardStatsBuilder jobs(JobService.JobStats jobs) {
                this.jobs = jobs;
                return this;
            }
            
            public DashboardStatsBuilder projects(ProjectService.ProjectStats projects) {
                this.projects = projects;
                return this;
            }
            
            public DashboardStatsBuilder features(Map<String, Long> features) {
                this.features = features;
                return this;
            }
            
            public DashboardStats build() {
                return new DashboardStats(services, teamMembers, testimonials, enquiries, blogs, jobs, projects, features);
            }
        }
    }

    // In DashboardController.java

    @GetMapping("/dashboard/all-document")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @Operation(summary = "Get all data as a document", description = "Fetches all data from services and creates an AllDocument entity")
    public ResponseEntity<ApiResponse<AllDocument>> getAllDocument() {
        AllDocument allDocument = new AllDocument();

        allDocument.setServices(serviceService.getAllServices(null,null,null));
        allDocument.setTeamMembers(teamMemberService.getAllTeamMembers(null,null,null));
        allDocument.setTestimonials(testimonialService.getAllTestimonials(null,null,null));
        allDocument.setEnquiries(enquiryService.getAllEnquiries(null, null, null, null, null));
        allDocument.setBlogs(blogService.getAllBlogs(null, null, null, 0,Integer.MAX_VALUE ));
        allDocument.setJobs(jobService.getAllJobs());
        allDocument.setProjects(projectService.getAllProjects(null, null, null, null));
        allDocument.setSiteSettings(siteSettingsService.getSettings());
        allDocument.setStaticPages(staticPageService.getAllStaticPages());
        allDocument.setFeatureToggles(featureToggleService.getAllFeatureToggles());
        allDocument.setUsers(adminUserService.getAllUsers());

        return ResponseEntity.ok(ApiResponse.<AllDocument>builder()
                .success(true)
                .data(allDocument)
                .build());
    }
}
