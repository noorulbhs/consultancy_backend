package com.altrevo.consultancy.service;

import com.altrevo.consultancy.entity.StaticPage;
import com.altrevo.consultancy.repository.StaticPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class StaticPageService {
    @Autowired
    private StaticPageRepository staticPageRepository;

    public List<StaticPage> getAllStaticPages() {
        return staticPageRepository.findAll();
    }

    public Optional<StaticPage> getStaticPageById(String id) {
        return staticPageRepository.findById(id);
    }

    public StaticPage createStaticPage(StaticPage page) {
        if (page.getId() == null || page.getId().isBlank()) {
            page.setId(java.util.UUID.randomUUID().toString());
        }
        page.setLastUpdated(Instant.now());
        return staticPageRepository.save(page);
    }

    public Optional<StaticPage> updateStaticPage(String id, StaticPage page) {
        Optional<StaticPage> existingOpt = staticPageRepository.findById(id);
        if (existingOpt.isEmpty()) {
            return Optional.empty();
        }
        StaticPage existing = existingOpt.get();
        existing.setTitle(page.getTitle());
        existing.setContent(page.getContent());
        existing.setCategory(page.getCategory());
        existing.setStatus(page.getStatus());
        existing.setMetaDescription(page.getMetaDescription());
        existing.setKeywords(page.getKeywords());
        existing.setLastUpdated(Instant.now());
        return Optional.of(staticPageRepository.save(existing));
    }

    public boolean deleteStaticPage(String id) {
        if (!staticPageRepository.existsById(id)) {
            return false;
        }
        staticPageRepository.deleteById(id);
        return true;
    }
}

