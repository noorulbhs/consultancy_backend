package com.altrevo.consultancy.repository;

import com.altrevo.consultancy.entity.InMemoryTestimonialStore;
import com.altrevo.consultancy.entity.Testimonial;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TestimonialInMemoryRepository {
    public Testimonial save(Testimonial testimonial) {
        if (testimonial.getId() == null) {
            testimonial.setId(InMemoryTestimonialStore.idCounter++);
        }
        InMemoryTestimonialStore.TESTIMONIALS.put(testimonial.getId(), testimonial);
        return testimonial;
    }

    public Optional<Testimonial> findById(Long id) {
        return Optional.ofNullable(InMemoryTestimonialStore.TESTIMONIALS.get(id));
    }

    public List<Testimonial> findAll() {
        return new ArrayList<>(InMemoryTestimonialStore.TESTIMONIALS.values());
    }

    public void deleteById(Long id) {
        InMemoryTestimonialStore.TESTIMONIALS.remove(id);
    }
}

