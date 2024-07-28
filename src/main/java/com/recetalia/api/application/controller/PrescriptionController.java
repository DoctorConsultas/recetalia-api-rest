package com.recetalia.api.application.controller;

import com.recetalia.api.application.dto.request.PrescriptionRequest;
import com.recetalia.api.application.dto.response.PrescriptionResponse;
import com.recetalia.api.application.service.PrescriptionService;
import com.recetalia.api.application.infrastructure.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

/**
 * Controller for handling Prescription-related HTTP requests.
 */
@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

  @Autowired
  private PrescriptionService prescriptionService;

  /**
   * Create a new Prescription.
   *
   * @param request the PrescriptionRequest DTO
   * @return the created PrescriptionResponse DTO
   */
  @PostMapping
  public ResponseEntity<PrescriptionResponse> createPrescription(@RequestBody PrescriptionRequest request) {
    PrescriptionResponse response = prescriptionService.createPrescription(request);
    return ResponseEntity.ok(response);
  }

  /**
   * Update an existing Prescription.
   *
   * @param id the ID of the Prescription to update
   * @param request the PrescriptionRequest DTO
   * @return the updated PrescriptionResponse DTO
   * @throws ResourceNotFoundException if the Prescription is not found
   */
  @PutMapping("/{id}")
  public ResponseEntity<PrescriptionResponse> updatePrescription(@PathVariable String id, @RequestBody PrescriptionRequest request) throws ResourceNotFoundException {
    PrescriptionResponse response = prescriptionService.updatePrescription(id, request);
    return ResponseEntity.ok(response);
  }

  /**
   * Get a Prescription by ID.
   *
   * @param id the ID of the Prescription
   * @return the PrescriptionResponse DTO
   * @throws ResourceNotFoundException if the Prescription is not found
   */
  @GetMapping("/{id}")
  public ResponseEntity<PrescriptionResponse> getPrescriptionById(@PathVariable String id) throws ResourceNotFoundException {
    PrescriptionResponse response = prescriptionService.getPrescriptionById(id);
    return ResponseEntity.ok(response);
  }

  /**
   * Delete a Prescription by ID.
   *
   * @param id the ID of the Prescription
   * @return a response entity indicating the result of the operation
   * @throws ResourceNotFoundException if the Prescription is not found
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePrescription(@PathVariable String id) throws ResourceNotFoundException {
    prescriptionService.deletePrescription(id);
    return ResponseEntity.noContent().build();
  }

  /**
   * Get all Prescriptions.
   *
   * @return a list of PrescriptionResponse DTOs
   */
  @GetMapping
  public ResponseEntity<List<PrescriptionResponse>> getAllPrescriptions() {
    List<PrescriptionResponse> responses = prescriptionService.getAllPrescriptions();
    return ResponseEntity.ok(responses);
  }

  /**
   * Get paginated prescriptions by medical provider ID.
   *
   * @param medicalProviderId the ID of the medical provider
   * @param pageable the pagination information
   * @return a page of PrescriptionResponse DTOs
   */
  @GetMapping("/by-medical-provider-paginated")
  public ResponseEntity<Page<PrescriptionResponse>> getPrescriptionsByMedicalProviderIdPaginated(@RequestParam String medicalProviderId, Pageable pageable) {
    Page<PrescriptionResponse> prescriptions = prescriptionService.getPrescriptionsByMedicalProviderId(medicalProviderId, pageable);
    return ResponseEntity.ok(prescriptions);
  }

  /**
   * Get paginated prescriptions by medic ID and medical provider ID.
   *
   * @param medicId the ID of the medic
   * @param medicalProviderId the ID of the medical provider
   * @param pageable the pagination information
   * @return a page of PrescriptionResponse DTOs
   */
  @GetMapping("/by-medic-and-medical-provider-paginated")
  public ResponseEntity<Page<PrescriptionResponse>> getPrescriptionsByMedicIdAndMedicalProviderIdPaginated(@RequestParam String medicId,
                                                                                                           @RequestParam String medicalProviderId,
                                                                                                           Pageable pageable) {
    Page<PrescriptionResponse> prescriptions = prescriptionService.getPrescriptionsByMedicIdAndMedicalProviderId(medicId, medicalProviderId, pageable);
    return ResponseEntity.ok(prescriptions);
  }

  /**
   * Get paginated prescriptions by patient ID and medical provider ID.
   *
   * @param patientId the ID of the patient
   * @param medicalProviderId the ID of the medical provider
   * @param pageable the pagination information
   * @return a page of PrescriptionResponse DTOs
   */
  @GetMapping("/by-patient-and-medical-provider-paginated")
  public ResponseEntity<Page<PrescriptionResponse>> getPrescriptionsByPatientIdAndMedicalProviderIdPaginated(@RequestParam String patientId,
                                                                                                           @RequestParam String medicalProviderId,
                                                                                                           Pageable pageable) {
    Page<PrescriptionResponse> prescriptions = prescriptionService.getPrescriptionsByPatientIddAndMedicalProviderId(patientId, medicalProviderId, pageable);
    return ResponseEntity.ok(prescriptions);
  }

  /**
   * Get the number of prescriptions by medical provider ID.
   *
   * @param medicalProviderId the ID of the medical provider
   * @return the number of prescriptions
   */
  @GetMapping("/count-by-medical-provider")
  public ResponseEntity<Long> getPrescriptionCountByMedicalProviderId(@RequestParam String medicalProviderId) {
    long count = prescriptionService.getPrescriptionCountByMedicalProviderId(medicalProviderId);
    return ResponseEntity.ok(count);
  }

  /**
   * Get active prescriptions by medical provider ID.
   *
   * @param medicalProviderId the ID of the medical provider
   * @return a list of PrescriptionResponse DTOs
   */
  @GetMapping("/active-by-medical-provider")
  public ResponseEntity<List<PrescriptionResponse>> getActivePrescriptionsByMedicalProviderId(@RequestParam String medicalProviderId) {
    List<PrescriptionResponse> prescriptions = prescriptionService.getActivePrescriptionsByMedicalProviderId(medicalProviderId);
    return ResponseEntity.ok(prescriptions);
  }

  /**
   * Get paginated prescriptions by medical provider ID and date range.
   *
   * @param medicalProviderId the ID of the medical provider
   * @param startDate the start date
   * @param endDate the end date
   * @param pageable the pagination information
   * @return a page of PrescriptionResponse DTOs
   */
  @GetMapping("/by-medical-provider-and-date-range")
  public ResponseEntity<Page<PrescriptionResponse>> getPrescriptionsByMedicalProviderIdAndDateRange(@RequestParam String medicalProviderId,
                                                                                                    @RequestParam(required = false) LocalDate startDate,
                                                                                                    @RequestParam(required = false) LocalDate endDate,
                                                                                                    Pageable pageable) {
    Instant startInstant = null;
    Instant endInstant = null;
    if (startDate != null) {
      startInstant = startDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
    }
    if (endDate != null) {
      endInstant = endDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1).minusNanos(1).toInstant();
    }
    Page<PrescriptionResponse> prescriptions = prescriptionService.getPrescriptionsByMedicalProviderIdAndDateRange(medicalProviderId, startInstant, endInstant, pageable);
    return ResponseEntity.ok(prescriptions);
  }

  /**
   * Get prescriptions by medic ID and date range.
   *
   * @param medicId the ID of the medic
   * @param startDate the start date
   * @param endDate the end date
   * @return a list of PrescriptionResponse DTOs
   */
  @GetMapping("/by-medic-and-date-range")
  public ResponseEntity<List<PrescriptionResponse>> getPrescriptionsByMedicIdAndDateRange(@RequestParam String medicId,
                                                                                          @RequestParam LocalDate startDate,
                                                                                          @RequestParam LocalDate endDate) {
    Instant startInstant = startDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
    Instant endInstant = endDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1).minusNanos(1).toInstant(); // end of the day
    List<PrescriptionResponse> prescriptions = prescriptionService.getPrescriptionsByMedicIdAndDateRange(medicId, startInstant, endInstant);
    return ResponseEntity.ok(prescriptions);
  }
}