package com.altrevo.consultancy.service;

import com.altrevo.consultancy.entity.Testimonial;
import com.altrevo.consultancy.repository.TestimonialInMemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestimonialService {
    
    private final TestimonialInMemoryRepository testimonialRepository;

    public List<Testimonial> getAllPublishedTestimonials() {
        return testimonialRepository.findAll().stream().filter(t -> Boolean.TRUE.equals(t.getPublished())).toList();
    }
    
    public List<Testimonial> getFeaturedTestimonials() {
        return testimonialRepository.findAll().stream().filter(t -> Boolean.TRUE.equals(t.getFeatured())).toList();
    }
    
    public List<Testimonial> getPublicTestimonials(Boolean featured, Integer rating) {
        return testimonialRepository.findAll().stream().filter(t -> Boolean.TRUE.equals(t.getPublished())).toList();
    }
    
    public Optional<Testimonial> getTestimonialById(Long id) {
        return testimonialRepository.findById(id);
    }
    
    public List<Testimonial> getAllTestimonials(Boolean published, Boolean featured, Integer rating) {
        return testimonialRepository.findAll();
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
        return testimonialRepository.findAll().stream().filter(t -> Boolean.TRUE.equals(t.getPublished())).count();
    }
    public long getFeaturedCount() {
        return testimonialRepository.findAll().stream().filter(t -> Boolean.TRUE.equals(t.getPublished()) && Boolean.TRUE.equals(t.getFeatured())).count();
    }
    public Double getAverageRating() {
        return testimonialRepository.findAll().stream()
            .filter(t -> t.getRating() != null)
            .mapToInt(Testimonial::getRating)
            .average()
            .orElse(0.0);
    }
    public long count() {
        return testimonialRepository.findAll().size();
    }
    public long countByPublishedTrue() {
        return testimonialRepository.findAll().stream().filter(t -> Boolean.TRUE.equals(t.getPublished())).count();
    }
    public long countByFeaturedTrue() {
        return testimonialRepository.findAll().stream().filter(t -> Boolean.TRUE.equals(t.getFeatured())).count();
    }
    public long countByPublishedFalse() {
        return testimonialRepository.findAll().stream().filter(t -> Boolean.FALSE.equals(t.getPublished())).count();
    }
    public long countByPublishedTrueAndFeaturedTrue() {
        return testimonialRepository.findAll().stream().filter(t -> Boolean.TRUE.equals(t.getPublished()) && Boolean.TRUE.equals(t.getFeatured())).count();
    }
    
    // Statistics methods
    
    public TestimonialStats getTestimonialStats() {
        long totalTestimonials = testimonialRepository.findAll().size();
        long publishedTestimonials = testimonialRepository.findAll().stream().filter(t -> Boolean.TRUE.equals(t.getPublished())).count();
        long featuredTestimonials = testimonialRepository.findAll().stream().filter(t -> Boolean.TRUE.equals(t.getFeatured())).count();
        long unpublishedTestimonials = testimonialRepository.findAll().stream().filter(t -> Boolean.FALSE.equals(t.getPublished())).count();
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
