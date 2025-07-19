package com.altrevo.consultancy.repository;

import com.altrevo.consultancy.entity.FeatureToggle;
import com.altrevo.consultancy.entity.InMemoryFeatureToggleStore;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FeatureToggleInMemoryRepository {
    public List<FeatureToggle> findAll() {
        return new ArrayList<>(InMemoryFeatureToggleStore.FEATURE_TOGGLES.values());
    }

    public Optional<FeatureToggle> findById(String id) {
        return Optional.ofNullable(InMemoryFeatureToggleStore.FEATURE_TOGGLES.get(id));
    }

    public FeatureToggle save(FeatureToggle featureToggle) {
        InMemoryFeatureToggleStore.FEATURE_TOGGLES.put(featureToggle.getId(), featureToggle);
        return featureToggle;
    }

    public void deleteById(String id) {
        InMemoryFeatureToggleStore.FEATURE_TOGGLES.remove(id);
    }

    public List<FeatureToggle> findAllEnabled() {
        List<FeatureToggle> result = new ArrayList<>();
        for (FeatureToggle f : InMemoryFeatureToggleStore.FEATURE_TOGGLES.values()) {
            if (Boolean.TRUE.equals(f.getEnabled())) {
                result.add(f);
            }
        }
        return result;
    }

    public List<FeatureToggle> findBySection(String section) {
        List<FeatureToggle> result = new ArrayList<>();
        for (FeatureToggle f : InMemoryFeatureToggleStore.FEATURE_TOGGLES.values()) {
            if (section != null && section.equals(f.getSection())) {
                result.add(f);
            }
        }
        return result;
    }

    public List<FeatureToggle> findBySectionAndEnabled(String section) {
        List<FeatureToggle> result = new ArrayList<>();
        for (FeatureToggle f : InMemoryFeatureToggleStore.FEATURE_TOGGLES.values()) {
            if (section != null && section.equals(f.getSection()) && Boolean.TRUE.equals(f.getEnabled())) {
                result.add(f);
            }
        }
        return result;
    }

    public boolean existsById(String id) {
        return InMemoryFeatureToggleStore.FEATURE_TOGGLES.containsKey(id);
    }

    public long countEnabled() {
        return InMemoryFeatureToggleStore.FEATURE_TOGGLES.values().stream().filter(f -> Boolean.TRUE.equals(f.getEnabled())).count();
    }

    public long countDisabled() {
        return InMemoryFeatureToggleStore.FEATURE_TOGGLES.values().stream().filter(f -> Boolean.FALSE.equals(f.getEnabled())).count();
    }

    public long count() {
        return InMemoryFeatureToggleStore.FEATURE_TOGGLES.size();
    }
}
