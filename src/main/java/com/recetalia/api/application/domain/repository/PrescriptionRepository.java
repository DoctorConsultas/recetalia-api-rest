package com.recetalia.api.application.domain.repository;

import com.recetalia.api.application.domain.model.entities.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

/**
 * Repository interface for Prescription entity.
 * Provides CRUD operations for Prescription.
 */
@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, String> {
  /**
   * Find prescriptions by medical provider ID.
   *
   * @param medicalProviderId the ID of the medical provider
   * @return a list of prescriptions
   */
  @Query(value = "SELECT p.* FROM prescription p " +
          "JOIN medic m ON p.medicId = m.id " +
          "JOIN medical_provider mp ON m.medicalProviderId = mp.id " +
          "WHERE mp.id = :medicalProviderId", nativeQuery = true)
  List<Prescription> findPrescriptionsByMedicalProviderId(@Param("medicalProviderId") String medicalProviderId);

  /**
   * Find prescriptions by medic ID and medical provider ID.
   *
   * @param medicId the ID of the medic
   * @param medicalProviderId the ID of the medical provider
   * @return a list of prescriptions
   */
  @Query(value = "SELECT p.* FROM prescription p " +
          "JOIN medic m ON p.medicId = m.id " +
          "JOIN medical_provider mp ON m.medicalProviderId = mp.id " +
          "WHERE p.medicId = :medicId AND mp.id = :medicalProviderId", nativeQuery = true)
  List<Prescription> findPrescriptionsByMedicIdAndMedicalProviderId(@Param("medicId") String medicId, @Param("medicalProviderId") String medicalProviderId);

  /**
   * Count prescriptions by medical provider ID.
   *
   * @param medicalProviderId the ID of the medical provider
   * @return the number of prescriptions
   */
  @Query(value = "SELECT COUNT(*) FROM prescription p " +
          "JOIN medic m ON p.medicId = m.id " +
          "JOIN medical_provider mp ON m.medicalProviderId = mp.id " +
          "WHERE mp.id = :medicalProviderId", nativeQuery = true)
  long countPrescriptionsByMedicalProviderId(@Param("medicalProviderId") String medicalProviderId);

  /**
   * Find active prescriptions by medical provider ID.
   *
   * @param medicalProviderId the ID of the medical provider
   * @return a list of active prescriptions
   */
  @Query(value = "SELECT p.* FROM prescription p " +
          "JOIN medic m ON p.medicId = m.id " +
          "JOIN medical_provider mp ON m.medicalProviderId = mp.id " +
          "WHERE mp.id = :medicalProviderId AND p.status = 'AVAILABLE'", nativeQuery = true)
  List<Prescription> findActivePrescriptionsByMedicalProviderId(@Param("medicalProviderId") String medicalProviderId);

  /**
   * Find prescriptions by medical provider ID and date range.
   *
   * @param medicalProviderId the ID of the medical provider
   * @param startDate the start date
   * @param endDate the end date
   * @return a list of prescriptions
   */
  @Query(value = "SELECT p.* FROM prescription p " +
          "JOIN medic m ON p.medicId = m.id " +
          "JOIN medical_provider mp ON m.medicalProviderId = mp.id " +
          "WHERE mp.id = :medicalProviderId AND p.createdAt BETWEEN :startDate AND :endDate", nativeQuery = true)
  List<Prescription> findPrescriptionsByMedicalProviderIdAndDateRange(@Param("medicalProviderId") String medicalProviderId,
                                                                      @Param("startDate") Instant startDate,
                                                                      @Param("endDate") Instant endDate);

  /**
   * Find prescriptions by medic ID and date range.
   *
   * @param medicId the ID of the medic
   * @param startDate the start date
   * @param endDate the end date
   * @return a list of prescriptions
   */
  @Query(value = "SELECT p.* FROM prescription p " +
          "JOIN medic m ON p.medicId = m.id " +
          "WHERE m.id = :medicId AND p.createdAt BETWEEN :startDate AND :endDate", nativeQuery = true)
  List<Prescription> findPrescriptionsByMedicIdAndDateRange(@Param("medicId") String medicId,
                                                            @Param("startDate") Instant startDate,
                                                            @Param("endDate") Instant endDate);
}
