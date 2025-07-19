package com.altrevo.consultancy.repository;

import com.altrevo.consultancy.entity.InMemorySiteSettingsStore;
import com.altrevo.consultancy.entity.SiteSettings;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SiteSettingsInMemoryRepository {
    public Optional<SiteSettings> findById(String id) {
        if (InMemorySiteSettingsStore.SITE_SETTINGS != null && id.equals(InMemorySiteSettingsStore.SITE_SETTINGS.getId())) {
            return Optional.of(InMemorySiteSettingsStore.SITE_SETTINGS);
        }
        return Optional.empty();
    }

    public SiteSettings save(SiteSettings settings) {
        InMemorySiteSettingsStore.SITE_SETTINGS = settings;
        return settings;
    }
}

