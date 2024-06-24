package com.recetalia.api.application.domain.repository;

import com.recetalia.api.application.domain.model.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Patient entity.
 * Provides CRUD operations for Patient.
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {
}
