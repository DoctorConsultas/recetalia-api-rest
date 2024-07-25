package com.recetalia.api.application.domain.repository;

import com.recetalia.api.application.domain.model.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Patient entity.
 * Provides CRUD operations for Patient.
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {
  @Query("SELECT pt FROM Patient pt " +
          "JOIN Prescription p ON pt.id = p.patient.id " +
          "JOIN Medic m ON p.medic.id = m.id " +
          "JOIN MedicalProvider mp ON m.medicalProviderId = mp.id " +
          "WHERE p.medic.id = :medicId AND mp.id = :medicalProviderId")
  List<Patient> findPatientsByMedicAndMedicalProvider(@Param("medicId") String medicId,
                                                      @Param("medicalProviderId") String medicalProviderId);

  @Query("SELECT pt FROM Patient pt " +
          "JOIN Prescription p ON pt.id = p.patient.id " +
          "JOIN Medic m ON p.medic.id = m.id " +
          "JOIN MedicalProvider mp ON m.medicalProviderId = mp.id " +
          "WHERE mp.id = :medicalProviderId")
  List<Patient> findPatientsBMedicalProvider(@Param("medicalProviderId") String medicalProviderId);
}
