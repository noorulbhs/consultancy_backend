package com.altrevo.consultancy.controller;

import com.altrevo.consultancy.dto.ApiResponse;
import com.altrevo.consultancy.entity.BlogPost;
import com.altrevo.consultancy.enums.BlogStatus;
import com.altrevo.consultancy.service.BlogService;
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
@Tag(name = "Blogs", description = "Blog post management")
public class BlogController {
    
    private static final Logger logger = LoggerFactory.getLogger(BlogController.class);
    
    @Autowired
    private BlogService blogService;
    
    // PUBLIC ENDPOINTS (No Authentication Required)
    
    @GetMapping("/public/blogs")
    @Operation(summary = "Get all published blogs", description = "Retrieve all published blog posts")
    public ResponseEntity<ApiResponse<List<BlogPost>>> getPublicBlogs(
            @Parameter(description = "Filter by category")
            @RequestParam(required = false) String category,
            @Parameter(description = "Filter featured blogs only")
            @RequestParam(required = false) Boolean featured,
            @Parameter(description = "Search keyword")
            @RequestParam(required = false) String search) {
        logger.info("Fetching public blogs - category: {}, featured: {}", category, featured);
        List<BlogPost> blogs;
        int page = 0;
        int size = Integer.MAX_VALUE;
        if (search != null && !search.trim().isEmpty()) {
            blogs = blogService.searchBlogs(search.trim(), page, size);
        } else if (featured != null && featured) {
            blogs = blogService.getFeaturedBlogs(page, size);
        } else {
            blogs = blogService.getPublicBlogs(category, featured, page, size);
        }
        return ResponseEntity.ok(ApiResponse.<List<BlogPost>>builder()
                .success(true)
                .data(blogs)
                .build());
    }
    
    @GetMapping("/public/blogs/{id}")
    @Operation(summary = "Get blog by ID", description = "Retrieve blog post by ID")
    public ResponseEntity<ApiResponse<BlogPost>> getPublicBlogById(
            @Parameter(description = "Blog post ID")
            @PathVariable Long id) {
        
        logger.info("Fetching public blog by ID: {}", id);
        
        Optional<BlogPost> blog = blogService.getPublicBlogById(id);
        
        if (blog.isPresent()) {
            return ResponseEntity.ok(ApiResponse.<BlogPost>builder()
                    .success(true)
                    .data(blog.get())
                    .build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/public/blogs/slug/{slug}")
    @Operation(summary = "Get blog by slug", description = "Retrieve blog post by slug")
    public ResponseEntity<ApiResponse<BlogPost>> getPublicBlogBySlug(
            @Parameter(description = "Blog post slug")
            @PathVariable String slug) {
        
        logger.info("Fetching public blog by slug: {}", slug);
        
        Optional<BlogPost> blog = blogService.getPublicBlogBySlug(slug);
        
        if (blog.isPresent()) {
            return ResponseEntity.ok(ApiResponse.<BlogPost>builder()
                    .success(true)
                    .data(blog.get())
                    .build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/public/blogs/categories")
    @Operation(summary = "Get blog categories", description = "Retrieve all blog categories")
    public ResponseEntity<ApiResponse<List<String>>> getBlogCategories() {
        logger.info("Fetching blog categories");
        
        List<String> categories = blogService.getPublicCategories();
        
        return ResponseEntity.ok(ApiResponse.<List<String>>builder()
                .success(true)
                .data(categories)
                .build());
    }
    
    @GetMapping("/public/blogs/tags")
    @Operation(summary = "Get blog tags", description = "Retrieve all blog tags")
    public ResponseEntity<ApiResponse<List<String>>> getBlogTags() {
        logger.info("Fetching blog tags");
        
        List<String> tags = blogService.getPublicTags();
        
        return ResponseEntity.ok(ApiResponse.<List<String>>builder()
                .success(true)
                .data(tags)
                .build());
    }
    
    // ADMIN ENDPOINTS (Authentication Required)
    
    @GetMapping("/admin/blogs")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('EDITOR')")
    @Operation(summary = "Get all blogs (Admin)", description = "Retrieve all blog posts for admin management")
    public ResponseEntity<ApiResponse<List<BlogPost>>> getAllBlogs(
            @Parameter(description = "Filter by status")
            @RequestParam(required = false) BlogStatus status,
            @Parameter(description = "Filter by category")
            @RequestParam(required = false) String category,
            @Parameter(description = "Filter featured blogs only")
            @RequestParam(required = false) Boolean featured) {
        logger.info("Fetching all blogs for admin - status: {}, category: {}, featured: {}", status, category, featured);
        int page = 0;
        int size = Integer.MAX_VALUE;
        List<BlogPost> blogs = blogService.getAllBlogs(status, category, featured, page, size);
        return ResponseEntity.ok(ApiResponse.<List<BlogPost>>builder()
                .success(true)
                .data(blogs)
                .build());
    }
    
    @GetMapping("/admin/blogs/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('EDITOR')")
    @Operation(summary = "Get blog by ID (Admin)", description = "Retrieve blog post by ID for admin")
    public ResponseEntity<ApiResponse<BlogPost>> getBlogById(
            @Parameter(description = "Blog post ID")
            @PathVariable Long id) {
        
        logger.info("Fetching blog by ID for admin: {}", id);
        
        Optional<BlogPost> blog = blogService.getBlogById(id);
        
        if (blog.isPresent()) {
            return ResponseEntity.ok(ApiResponse.<BlogPost>builder()
                    .success(true)
                    .data(blog.get())
                    .build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/admin/blogs")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('EDITOR')")
    @Operation(summary = "Create blog", description = "Create a new blog post")
    public ResponseEntity<ApiResponse<BlogPost>> createBlog(
            @Valid @RequestBody BlogPost blog,
            Principal principal) {
        
        logger.info("Creating new blog: {}", blog.getTitle());
        
        // Set the created by user
        blog.setCreatedBy(principal.getName());
        blog.setUpdatedBy(principal.getName());
        
        BlogPost savedBlog = blogService.createBlog(blog);
        
        return ResponseEntity.ok(ApiResponse.<BlogPost>builder()
                .success(true)
                .message("Blog created successfully")
                .data(savedBlog)
                .build());
    }
    
    @PutMapping("/admin/blogs/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('EDITOR')")
    @Operation(summary = "Update blog", description = "Update an existing blog post")
    public ResponseEntity<ApiResponse<BlogPost>> updateBlog(
            @Parameter(description = "Blog post ID")
            @PathVariable Long id,
            @Valid @RequestBody BlogPost blog,
            Principal principal) {
        
        logger.info("Updating blog with ID: {}", id);
        
        // Set the updated by user
        blog.setUpdatedBy(principal.getName());
        
        try {
            BlogPost updatedBlog = blogService.updateBlog(id, blog);
            
            return ResponseEntity.ok(ApiResponse.<BlogPost>builder()
                    .success(true)
                    .message("Blog updated successfully")
                    .data(updatedBlog)
                    .build());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/admin/blogs/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @Operation(summary = "Delete blog", description = "Delete a blog post")
    public ResponseEntity<ApiResponse<Void>> deleteBlog(
            @Parameter(description = "Blog post ID")
            @PathVariable Long id) {
        
        logger.info("Deleting blog with ID: {}", id);
        
        try {
            blogService.deleteBlog(id);
            
            return ResponseEntity.ok(ApiResponse.<Void>builder()
                    .success(true)
                    .message("Blog deleted successfully")
                    .build());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/admin/blogs/stats")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @Operation(summary = "Get blog statistics", description = "Retrieve blog statistics for admin dashboard")
    public ResponseEntity<ApiResponse<BlogService.BlogStats>> getBlogStats() {
        logger.info("Fetching blog statistics");
        
        BlogService.BlogStats stats = blogService.getBlogStats();
        
        return ResponseEntity.ok(ApiResponse.<BlogService.BlogStats>builder()
                .success(true)
                .data(stats)
                .build());
    }
}
