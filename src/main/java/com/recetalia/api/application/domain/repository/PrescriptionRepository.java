package com.recetalia.api.application.domain.repository;

import com.recetalia.api.application.domain.model.entities.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Prescription entity.
 * Provides CRUD operations for Prescription.
 */
@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, String> {
}
