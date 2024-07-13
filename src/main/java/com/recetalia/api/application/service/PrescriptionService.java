package com.recetalia.api.application.service;

import com.recetalia.api.application.dto.request.PrescriptionRequest;
import com.recetalia.api.application.dto.response.PrescriptionResponse;
import com.recetalia.api.application.infrastructure.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;

/**
 * Service interface for Prescription operations.
 */
public interface PrescriptionService {

  /**
   * Creates a new Prescription.
   *
   * @param request the PrescriptionRequest DTO
   * @return the created PrescriptionResponse DTO
   */
  PrescriptionResponse createPrescription(PrescriptionRequest request);

  /**
   * Updates an existing Prescription.
   *
   * @param id the ID of the Prescription to update
   * @param request the PrescriptionRequest DTO
   * @return the updated PrescriptionResponse DTO
   * @throws ResourceNotFoundException if the Prescription is not found
   */
  PrescriptionResponse updatePrescription(String id, PrescriptionRequest request) throws ResourceNotFoundException;

  /**
   * Gets a Prescription by ID.
   *
   * @param id the ID of the Prescription
   * @return the PrescriptionResponse DTO
   * @throws ResourceNotFoundException if the Prescription is not found
   */
  PrescriptionResponse getPrescriptionById(String id) throws ResourceNotFoundException;

  /**
   * Deletes a Prescription by ID.
   *
   * @param id the ID of the Prescription
   * @throws ResourceNotFoundException if the Prescription is not found
   */
  void deletePrescription(String id) throws ResourceNotFoundException;

  /**
   * Gets all Prescriptions.
   *
   * @return a list of PrescriptionResponse DTOs
   */
  List<PrescriptionResponse> getAllPrescriptions();

  /**
   * Gets paginated prescriptions by medical provider ID.
   *
   * @param medicalProviderId the ID of the medical provider
   * @param pageable the pagination information
   * @return a page of PrescriptionResponse DTOs
   */
  Page<PrescriptionResponse> getPrescriptionsByMedicalProviderId(String medicalProviderId, Pageable pageable);

  /**
   * Gets paginated prescriptions by medic ID and medical provider ID.
   *
   * @param medicId the ID of the medic
   * @param medicalProviderId the ID of the medical provider
   * @param pageable the pagination information
   * @return a page of PrescriptionResponse DTOs
   */
  Page<PrescriptionResponse> getPrescriptionsByMedicIdAndMedicalProviderId(String medicId, String medicalProviderId, Pageable pageable);

  /**
   * Gets the number of prescriptions by medical provider ID.
   *
   * @param medicalProviderId the ID of the medical provider
   * @return the number of prescriptions
   */
  long getPrescriptionCountByMedicalProviderId(String medicalProviderId);

  /**
   * Gets active prescriptions by medical provider ID.
   *
   * @param medicalProviderId the ID of the medical provider
   * @return a list of PrescriptionResponse DTOs
   */
  List<PrescriptionResponse> getActivePrescriptionsByMedicalProviderId(String medicalProviderId);

  /**
   * Gets prescriptions by medical provider ID and date range.
   *
   * @param medicalProviderId the ID of the medical provider
   * @param startDate the start date
   * @param endDate the end date
   * @return a list of PrescriptionResponse DTOs
   */
  List<PrescriptionResponse> getPrescriptionsByMedicalProviderIdAndDateRange(String medicalProviderId, Instant startDate, Instant endDate);

  /**
   * Get prescriptions by medic ID and date range.
   *
   * @param medicId the ID of the medic
   * @param startDate the start date
   * @param endDate the end date
   * @return a list of PrescriptionResponse DTOs
   */
  List<PrescriptionResponse> getPrescriptionsByMedicIdAndDateRange(String medicId, Instant startDate, Instant endDate);
}
