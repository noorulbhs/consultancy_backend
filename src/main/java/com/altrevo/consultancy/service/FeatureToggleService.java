package com.altrevo.consultancy.service;

import com.altrevo.consultancy.entity.FeatureToggle;
import com.altrevo.consultancy.repository.FeatureToggleInMemoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeatureToggleService {
    
    private final FeatureToggleInMemoryRepository featureToggleRepository;

    public List<FeatureToggle> getAllFeatureToggles() {
        log.info("Fetching all feature toggles");
        return featureToggleRepository.findAll();
    }
    
    public List<FeatureToggle> getEnabledFeatureToggles() {
        log.info("Fetching enabled feature toggles");
        return featureToggleRepository.findAllEnabled();
    }
    
    public List<FeatureToggle> getFeatureTogglesBySection(String section) {
        log.info("Fetching feature toggles for section: {}", section);
        return featureToggleRepository.findBySection(section);
    }
    
    public List<FeatureToggle> getEnabledFeatureTogglesBySection(String section) {
        log.info("Fetching enabled feature toggles for section: {}", section);
        return featureToggleRepository.findBySectionAndEnabled(section);
    }
    
    public Optional<FeatureToggle> getFeatureToggleById(String id) {
        log.info("Fetching feature toggle by ID: {}", id);
        return featureToggleRepository.findById(id);
    }
    
//    @Transactional
    public FeatureToggle updateFeatureToggle(String id, Boolean enabled, String updatedBy) {
        log.info("Updating feature toggle: {} to enabled: {} by: {}", id, enabled, updatedBy);
        
        FeatureToggle featureToggle = featureToggleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feature toggle not found with ID: " + id));
        
        featureToggle.setEnabled(enabled);
        featureToggle.setUpdatedBy(updatedBy);
        featureToggle.setUpdatedAt(LocalDateTime.now());
        
        return featureToggleRepository.save(featureToggle);
    }
    
//    @Transactional
    public FeatureToggle createFeatureToggle(FeatureToggle featureToggle, String createdBy) {
        log.info("Creating new feature toggle: {} by: {}", featureToggle.getId(), createdBy);
        
        featureToggle.setUpdatedBy(createdBy);
        featureToggle.setUpdatedAt(LocalDateTime.now());
        
        return featureToggleRepository.save(featureToggle);
    }
    
//    @Transactional
    public void deleteFeatureToggle(String id) {
        log.info("Deleting feature toggle with ID: {}", id);
        
        if (!featureToggleRepository.existsById(id)) {
            throw new RuntimeException("Feature toggle not found with ID: " + id);
        }
        
        featureToggleRepository.deleteById(id);
    }
    
    public boolean isFeatureEnabled(String featureId) {
        log.debug("Checking if feature is enabled: {}", featureId);
        return featureToggleRepository.findById(featureId)
                .map(FeatureToggle::getEnabled)
                .orElse(false);
    }
    
    public long getEnabledCount() {
        return featureToggleRepository.countEnabled();
    }
    
    public long getDisabledCount() {
        return featureToggleRepository.countDisabled();
    }
    
    public long getTotalCount() {
        return featureToggleRepository.count();
    }

    public void updateFeatureEnabled(String id, boolean enabled) {
        FeatureToggle feature = featureToggleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feature toggle not found: " + id));
        feature.setEnabled(enabled);
        feature.setUpdatedAt(LocalDateTime.now());
        featureToggleRepository.save(feature);
    }
}
