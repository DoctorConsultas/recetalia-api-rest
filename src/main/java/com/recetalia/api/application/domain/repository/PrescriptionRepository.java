package com.recetalia.api.application.domain.repository;

import com.recetalia.api.application.domain.model.entities.Prescription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
     * Find prescriptions based on various optional filters.
     *
     * @param medicalProviderId the ID of the medical provider
     * @param medicId the ID of the medic (optional)
     * @param patientId the ID of the patient (optional)
     * @param statuses the list of statuses to filter
     * @param startDate the start date for filtering by date range (optional)
     * @param endDate the end date for filtering by date range (optional)
     * @param pageable the pagination information
     * @return a page of prescriptions
     */
    @Query(value = "SELECT p.*, ph.name as pharmacy_name FROM prescription p " +
            "JOIN medic m ON p.medicId = m.id " +
            "JOIN medical_provider mp ON m.medicalProviderId = mp.id " +
            "LEFT JOIN dispensation d ON p.id = d.prescriptionId " +
            "LEFT JOIN pharmacy ph ON d.pharmacyId = ph.id " +
            "WHERE mp.id = :medicalProviderId " +
            "AND (:medicId IS NULL OR p.medicId = :medicId) " +
            "AND (:patientId IS NULL OR p.patientId = :patientId) " +
            "AND p.status IN :statuses " +
            "AND (:startDate IS NULL OR p.createdAt >= :startDate) " +
            "AND (:endDate IS NULL OR p.createdAt <= :endDate) " +
            "ORDER BY p.createdAt DESC",
            countQuery = "SELECT COUNT(p.id) FROM prescription p " +
                    "JOIN medic m ON p.medicId = m.id " +
                    "JOIN medical_provider mp ON m.medicalProviderId = mp.id " +
                    "WHERE mp.id = :medicalProviderId " +
                    "AND (:medicId IS NULL OR p.medicId = :medicId) " +
                    "AND (:patientId IS NULL OR p.patientId = :patientId) " +
                    "AND p.status IN :statuses " +
                    "AND (:startDate IS NULL OR p.createdAt >= :startDate) " +
                    "AND (:endDate IS NULL OR p.createdAt <= :endDate)",
            nativeQuery = true)
    Page<Prescription> findPrescriptionsByFilters(
            @Param("medicalProviderId") String medicalProviderId,
            @Param("medicId") String medicId,
            @Param("patientId") String patientId,
            @Param("statuses") List<String> statuses,
            @Param("startDate") Instant startDate,
            @Param("endDate") Instant endDate,
            Pageable pageable);

  /**
   * Find prescriptions by medical provider ID and status with descending order.
   *
   * @param medicalProviderId the ID of the medical provider
   * @param statuses the list of statuses to filter
   * @param pageable the pagination information
   * @return a page of prescriptions with pharmacy details
   */
  @Query(value = "SELECT p.*, ph.name as pharmacy_name FROM prescription p " +
          "JOIN medic m ON p.medicId = m.id " +
          "JOIN medical_provider mp ON m.medicalProviderId = mp.id " +
          "LEFT JOIN dispensation d ON p.id = d.prescriptionId " +
          "LEFT JOIN pharmacy ph ON d.pharmacyId = ph.id " +
          "WHERE mp.id = :medicalProviderId AND p.status IN :statuses " +
          "ORDER BY p.createdAt DESC",
          countQuery = "SELECT COUNT(p.id) FROM prescription p " +
                  "JOIN medic m ON p.medicId = m.id " +
                  "JOIN medical_provider mp ON m.medicalProviderId = mp.id " +
                  "WHERE mp.id = :medicalProviderId AND p.status IN :statuses",
          nativeQuery = true)
  Page<Prescription> findPrescriptionsByMedicalProviderIdAndStatuses(
          @Param("medicalProviderId") String medicalProviderId,
          @Param("statuses") List<String> statuses,
          Pageable pageable);

  /**
   * Find paginated prescriptions by patient ID and medical provider ID with status filter.
   *
   * @param patientId the ID of the patient
   * @param medicalProviderId the ID of the medical provider
   * @param statuses the list of statuses to filter
   * @param pageable the pagination information
   * @return a page of prescriptions
   */
  @Query(value = "SELECT p.* FROM prescription p " +
          "JOIN medic m ON p.medicId = m.id " +
          "JOIN medical_provider mp ON m.medicalProviderId = mp.id " +
          "WHERE p.patientId = :patientId AND mp.id = :medicalProviderId AND p.status IN :statuses " +
          "ORDER BY p.createdAt DESC",
          countQuery = "SELECT COUNT(p.id) FROM prescription p " +
                  "JOIN medic m ON p.medicId = m.id " +
                  "JOIN medical_provider mp ON m.medicalProviderId = mp.id " +
                  "WHERE p.patientId = :patientId AND mp.id = :medicalProviderId AND p.status IN :statuses",
          nativeQuery = true)
  Page<Prescription> findPrescriptionsByPatientIdAndMedicalProviderId(@Param("patientId") String patientId,
                                                                      @Param("medicalProviderId") String medicalProviderId,
                                                                      @Param("statuses") List<String> statuses,
                                                                      Pageable pageable);

  /**
   * Find paginated prescriptions by medic ID and medical provider ID with status filter.
   *
   * @param medicId the ID of the medic
   * @param medicalProviderId the ID of the medical provider
   * @param statuses the list of statuses to filter
   * @param pageable the pagination information
   * @return a page of prescriptions
   */
  @Query(value = "SELECT p.* FROM prescription p " +
          "JOIN medic m ON p.medicId = m.id " +
          "JOIN medical_provider mp ON m.medicalProviderId = mp.id " +
          "WHERE p.medicId = :medicId AND mp.id = :medicalProviderId AND p.status IN :statuses " +
          "ORDER BY p.createdAt DESC",
          countQuery = "SELECT COUNT(p.id) FROM prescription p " +
                  "JOIN medic m ON p.medicId = m.id " +
                  "JOIN medical_provider mp ON m.medicalProviderId = mp.id " +
                  "WHERE p.medicId = :medicId AND mp.id = :medicalProviderId AND p.status IN :statuses",
          nativeQuery = true)
  Page<Prescription> findPrescriptionsByMedicIdAndMedicalProviderId(@Param("medicId") String medicId,
                                                                    @Param("medicalProviderId") String medicalProviderId,
                                                                    @Param("statuses") List<String> statuses,
                                                                    Pageable pageable);

  /**
   * Count prescriptions by medical provider ID.
   *
   * @param medicalProviderId the ID of the medical provider
   * @return the number of prescriptions
   */
  @Query(value = "SELECT COUNT(*) FROM prescription p " +
          "JOIN medic m ON p.medicId = m.id " +
          "JOIN medical_provider mp ON m.medicalProviderId = mp.id " +
          "WHERE mp.id = :medicalProviderId AND p.status IN :statuses", nativeQuery = true)
  long countPrescriptionsByMedicalProviderId(@Param("medicalProviderId") String medicalProviderId,
                                             @Param("statuses") List<String> statuses);

  /**
   * Find active prescriptions by medical provider ID.
   *
   * @param medicalProviderId the ID of the medical provider
   * @return a list of active prescriptions
   */
  @Query(value = "SELECT p.* FROM prescription p " +
          "JOIN medic m ON p.medicId = m.id " +
          "JOIN medical_provider mp ON m.medicalProviderId = mp.id " +
          "WHERE mp.id = :medicalProviderId AND p.status = 'AVAILABLE' " +
          "ORDER BY p.createdAt DESC", nativeQuery = true)
  List<Prescription> findActivePrescriptionsByMedicalProviderId(@Param("medicalProviderId") String medicalProviderId);

  /**
   * Find prescriptions by medical provider ID and date range with status filter.
   *
   * @param medicalProviderId the ID of the medical provider
   * @param startDate the start date
   * @param endDate the end date
   * @param statuses the list of statuses to filter
   * @param pageable the pagination information
   * @return a page of prescriptions
   */
  @Query(value = "SELECT p.* FROM prescription p " +
          "JOIN medic m ON p.medicId = m.id " +
          "JOIN medical_provider mp ON m.medicalProviderId = mp.id " +
          "WHERE mp.id = :medicalProviderId AND p.status IN :statuses " +
          "AND (:startDate IS NULL OR p.createdAt >= :startDate) " +
          "AND (:endDate IS NULL OR p.createdAt <= :endDate) " +
          "ORDER BY p.createdAt DESC",
          countQuery = "SELECT COUNT(p.id) FROM prescription p " +
                  "JOIN medic m ON p.medicId = m.id " +
                  "JOIN medical_provider mp ON m.medicalProviderId = mp.id " +
                  "WHERE mp.id = :medicalProviderId AND p.status IN :statuses " +
                  "AND (:startDate IS NULL OR p.createdAt >= :startDate) " +
                  "AND (:endDate IS NULL OR p.createdAt <= :endDate)",
          nativeQuery = true)
  Page<Prescription> findPrescriptionsByMedicalProviderIdAndDateRange(@Param("medicalProviderId") String medicalProviderId,
                                                                      @Param("startDate") Instant startDate,
                                                                      @Param("endDate") Instant endDate,
                                                                      @Param("statuses") List<String> statuses,
                                                                      Pageable pageable);

  /**
   * Find prescriptions by medic ID and date range with status filter.
   *
   * @param medicId the ID of the medic
   * @param startDate the start date
   * @param endDate the end date
   * @param statuses the list of statuses to filter
   * @return a list of prescriptions
   */
  @Query(value = "SELECT p.* FROM prescription p " +
          "JOIN medic m ON p.medicId = m.id " +
          "WHERE m.id = :medicId AND p.status IN :statuses " +
          "AND p.createdAt BETWEEN :startDate AND :endDate " +
          "ORDER BY p.createdAt DESC", nativeQuery = true)
  List<Prescription> findPrescriptionsByMedicIdAndDateRange(@Param("medicId") String medicId,
                                                            @Param("startDate") Instant startDate,
                                                            @Param("endDate") Instant endDate,
                                                            @Param("statuses") List<String> statuses);
}
