package com.recetalia.api.application.domain.repository;

import com.recetalia.api.application.domain.model.entities.MedicalProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for MedicalProvider entity.
 * Provides CRUD operations for MedicalProvider.
 */
@Repository
public interface MedicalProviderRepository extends JpaRepository<MedicalProvider, String> {
}