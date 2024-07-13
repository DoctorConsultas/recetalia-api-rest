package com.recetalia.api.application.domain.repository;

import com.recetalia.api.application.domain.model.entities.MedicalProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for MedicalProvider entity.
 * Provides CRUD operations for MedicalProvider.
 */
@Repository
public interface MedicalProviderRepository extends JpaRepository<MedicalProvider, String> {
    /**
     * Finds a MedicalProvider by email and password.
     *
     * @param email    the email of the MedicalProvider
     * @param password the password of the MedicalProvider
     * @return an optional containing the found MedicalProvider or empty if not found
     */
    Optional<MedicalProvider> findByEmailAndPassword(String email, String password);
}