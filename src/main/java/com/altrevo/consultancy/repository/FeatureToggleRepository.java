package com.altrevo.consultancy.repository;

import com.altrevo.consultancy.entity.FeatureToggle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeatureToggleRepository extends JpaRepository<FeatureToggle, String> {
    
    @Query("SELECT f FROM FeatureToggle f WHERE f.enabled = true")
    List<FeatureToggle> findAllEnabled();
    
    @Query("SELECT f FROM FeatureToggle f WHERE f.section = :section")
    List<FeatureToggle> findBySection(String section);
    
    @Query("SELECT f FROM FeatureToggle f WHERE f.section = :section AND f.enabled = true")
    List<FeatureToggle> findBySectionAndEnabled(String section);
    
    Optional<FeatureToggle> findById(String id);
    
    @Query("SELECT COUNT(f) FROM FeatureToggle f WHERE f.enabled = true")
    long countEnabled();
    
    @Query("SELECT COUNT(f) FROM FeatureToggle f WHERE f.enabled = false")
    long countDisabled();
}
