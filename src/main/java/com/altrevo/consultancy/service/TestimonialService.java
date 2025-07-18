package com.altrevo.consultancy.service;

import com.altrevo.consultancy.entity.Testimonial;
import com.altrevo.consultancy.repository.TestimonialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestimonialService {
    
    private final TestimonialRepository testimonialRepository;
    
    public List<Testimonial> getAllPublishedTestimonials() {
        return testimonialRepository.findPublishedTestimonialsOrderBySortOrder(Pageable.unpaged());
    }
    
    public List<Testimonial> getFeaturedTestimonials() {
        return testimonialRepository.findFeaturedTestimonials(Pageable.unpaged());
    }
    
    public List<Testimonial> getPublicTestimonials(Boolean featured, Integer rating) {
        if (featured != null && featured) {
            return testimonialRepository.findByPublishedTrue(Pageable.unpaged());
        } else if (rating != null) {
            return testimonialRepository.findByPublishedTrueAndRatingGreaterThanEqual(rating, Pageable.unpaged());
        } else {
            return testimonialRepository.findByPublishedTrue(Pageable.unpaged());
        }
    }
    
    public Optional<Testimonial> getTestimonialById(Long id) {
        return testimonialRepository.findById(id);
    }
    
    public List<Testimonial> getAllTestimonials(Boolean published, Boolean featured, Integer rating) {
        if (published != null && published && rating != null) {
            return testimonialRepository.findByPublishedTrueAndRatingGreaterThanEqual(rating, Pageable.unpaged());
        } else if (published != null && published) {
            return testimonialRepository.findByPublishedTrue(Pageable.unpaged());
        } else {
            return testimonialRepository.findAll();
        }
    }
    
    public Testimonial createTestimonial(Testimonial testimonial) {
        return testimonialRepository.save(testimonial);
    }
    
    public Testimonial updateTestimonial(Long id, Testimonial testimonialDetails) {
        Testimonial testimonial = testimonialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Testimonial not found with id: " + id));
        
        testimonial.setName(testimonialDetails.getName());
        testimonial.setDesignation(testimonialDetails.getDesignation());
        testimonial.setCompany(testimonialDetails.getCompany());
        testimonial.setCompanyLogo(testimonialDetails.getCompanyLogo());
        testimonial.setMessage(testimonialDetails.getMessage());
        testimonial.setRating(testimonialDetails.getRating());
        testimonial.setPhotoUrl(testimonialDetails.getPhotoUrl());
        testimonial.setPublished(testimonialDetails.getPublished());
        testimonial.setFeatured(testimonialDetails.getFeatured());
        testimonial.setDate(testimonialDetails.getDate());
        testimonial.setProjectType(testimonialDetails.getProjectType());
        testimonial.setLocation(testimonialDetails.getLocation());
        testimonial.setTags(testimonialDetails.getTags());
        testimonial.setSortOrder(testimonialDetails.getSortOrder());
        
        return testimonialRepository.save(testimonial);
    }
    
    public void deleteTestimonial(Long id) {
        testimonialRepository.deleteById(id);
    }
    
    public long getPublishedCount() {
        return testimonialRepository.countByPublishedTrue();
    }
    
    public long getFeaturedCount() {
        return testimonialRepository.countByPublishedTrueAndFeaturedTrue();
    }
    
    public Double getAverageRating() {
        return testimonialRepository.getAverageRating();
    }
    
    // Statistics methods
    
    public TestimonialStats getTestimonialStats() {
        long totalTestimonials = testimonialRepository.count();
        long publishedTestimonials = testimonialRepository.countByPublishedTrue();
        long featuredTestimonials = testimonialRepository.countByFeaturedTrue();
        long unpublishedTestimonials = testimonialRepository.countByPublishedFalse();
        
        return new TestimonialStats(totalTestimonials, publishedTestimonials, featuredTestimonials, unpublishedTestimonials);
    }
    
    // Inner class for response DTO
    
    public static class TestimonialStats {
        public final long totalTestimonials;
        public final long publishedTestimonials;
        public final long featuredTestimonials;
        public final long unpublishedTestimonials;
        
        public TestimonialStats(long totalTestimonials, long publishedTestimonials, long featuredTestimonials, long unpublishedTestimonials) {
            this.totalTestimonials = totalTestimonials;
            this.publishedTestimonials = publishedTestimonials;
            this.featuredTestimonials = featuredTestimonials;
            this.unpublishedTestimonials = unpublishedTestimonials;
        }
    }
}
