package com.altrevo.consultancy.repository;

import com.altrevo.consultancy.entity.Testimonial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestimonialRepository extends JpaRepository<Testimonial, Long> {
    
    List<Testimonial> findByPublishedTrue();
    
    List<Testimonial> findByPublishedTrueAndFeaturedTrue();
    
    List<Testimonial> findByPublishedTrueAndRatingGreaterThanEqual(Integer rating);
    
    List<Testimonial> findByPublishedTrue(Pageable pageable);
    
    List<Testimonial> findByPublishedTrueAndRatingGreaterThanEqual(Integer rating, Pageable pageable);
    
    @Query("SELECT t FROM Testimonial t WHERE t.published = true ORDER BY t.sortOrder ASC, t.createdAt DESC")
    List<Testimonial> findPublishedTestimonialsOrderBySortOrder(Pageable pageable);
    
    @Query("SELECT t FROM Testimonial t WHERE t.published = true AND t.featured = true ORDER BY t.sortOrder ASC, t.createdAt DESC")
    List<Testimonial> findFeaturedTestimonials(Pageable pageable);
    
    @Query("SELECT t FROM Testimonial t WHERE t.published = true AND t.rating >= :rating ORDER BY t.rating DESC, t.createdAt DESC")
    List<Testimonial> findByMinRatingOrderByRating(@Param("rating") Integer rating);
    
    // Count methods
    long countByPublishedTrue();
    long countByPublishedFalse();
    long countByFeaturedTrue();
    long countByFeaturedFalse();
    long countByPublishedTrueAndFeaturedTrue();
    long countByRating(Integer rating);
    long countByRatingGreaterThanEqual(Integer rating);
    
    @Query("SELECT AVG(t.rating) FROM Testimonial t WHERE t.published = true")
    Double getAverageRating();
}
