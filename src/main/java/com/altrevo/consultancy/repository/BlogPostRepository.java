package com.altrevo.consultancy.repository;

import com.altrevo.consultancy.entity.BlogPost;
import com.altrevo.consultancy.enums.BlogStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    
    // Basic status queries
    List<BlogPost> findByStatus(BlogStatus status);
    List<BlogPost> findByStatus(BlogStatus status, Pageable pageable);
    List<BlogPost> findByStatusOrderByPublishedAtDesc(BlogStatus status, Pageable pageable);
    List<BlogPost> findByStatusOrderByCreatedAtDesc(BlogStatus status, Pageable pageable);
    
    // Featured queries
    List<BlogPost> findByStatusAndFeaturedTrue(BlogStatus status);
    List<BlogPost> findByStatusAndFeaturedTrueOrderByPublishedAtDesc(BlogStatus status, Pageable pageable);
    List<BlogPost> findByStatusAndFeaturedTrueOrderByCreatedAtDesc(BlogStatus status, Pageable pageable);
    List<BlogPost> findByFeaturedTrueOrderByCreatedAtDesc(Pageable pageable);
    
    // Category queries
    List<BlogPost> findByStatusAndCategory(BlogStatus status, String category);
    List<BlogPost> findByStatusAndCategory(BlogStatus status, String category, Pageable pageable);
    List<BlogPost> findByStatusAndCategoryIgnoreCaseOrderByPublishedAtDesc(BlogStatus status, String category, Pageable pageable);
    List<BlogPost> findByStatusAndCategoryIgnoreCaseOrderByCreatedAtDesc(BlogStatus status, String category, Pageable pageable);
    List<BlogPost> findByCategoryIgnoreCaseOrderByCreatedAtDesc(String category, Pageable pageable);
    
    // Category + Featured queries
    List<BlogPost> findByStatusAndCategoryIgnoreCaseAndFeaturedTrueOrderByPublishedAtDesc(BlogStatus status, String category, Pageable pageable);
    List<BlogPost> findByStatusAndCategoryIgnoreCaseAndFeaturedTrueOrderByCreatedAtDesc(BlogStatus status, String category, Pageable pageable);
    List<BlogPost> findByCategoryIgnoreCaseAndFeaturedTrueOrderByCreatedAtDesc(String category, Pageable pageable);
    
    // Search queries
    List<BlogPost> findByStatusAndTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrderByPublishedAtDesc(
            BlogStatus status, String titleKeyword, String contentKeyword, Pageable pageable);
    
    // Slug queries
    Optional<BlogPost> findBySlug(String slug);
    Optional<BlogPost> findBySlugAndStatus(String slug, BlogStatus status);
    
    // Distinct categories and tags
    @Query("SELECT DISTINCT bp.category FROM BlogPost bp WHERE bp.status = :status")
    List<String> findDistinctCategoriesByStatus(@Param("status") BlogStatus status);
    
    @Query("SELECT DISTINCT t FROM BlogPost bp JOIN bp.tags t WHERE bp.status = :status")
    List<String> findDistinctTagsByStatus(@Param("status") BlogStatus status);
    
    // Count queries
    long countByStatus(BlogStatus status);
    long countByStatusAndCategory(BlogStatus status, String category);
    
    // Related posts
    @Query("SELECT bp FROM BlogPost bp WHERE bp.status = :status AND bp.id != :excludeId ORDER BY bp.publishedAt DESC")
    List<BlogPost> findRelatedPosts(@Param("status") BlogStatus status, @Param("excludeId") Long excludeId, Pageable pageable);
    
    // Utility queries
    boolean existsBySlug(String slug);
    
    @Query("UPDATE BlogPost bp SET bp.views = bp.views + 1 WHERE bp.id = :id")
    void incrementViews(@Param("id") Long id);
}
