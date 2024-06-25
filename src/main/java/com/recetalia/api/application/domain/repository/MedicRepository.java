package com.recetalia.api.application.domain.repository;

import com.recetalia.api.application.domain.model.entities.Medic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Medic entity.
 * Provides CRUD operations for Medic.
 */
@Repository
public interface MedicRepository extends JpaRepository<Medic, String> {
  List<Medic> findByMedicalProviderId(String medicalProviderId);
}
