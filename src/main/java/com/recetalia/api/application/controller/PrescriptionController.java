package com.recetalia.api.application.controller;

import com.recetalia.api.application.dto.request.PrescriptionRequest;
import com.recetalia.api.application.dto.response.PrescriptionResponse;
import com.recetalia.api.application.service.PrescriptionService;
import com.recetalia.api.application.infrastructure.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
   * Get paginated prescriptions by medical provider ID and statuses.
   *
   * @param medicalProviderId the ID of the medical provider
   * @param statuses the list of statuses to filter
   * @param pageable the pagination information
   * @return a page of PrescriptionResponse DTOs
   */
  @GetMapping("/by-medical-provider-paginated")
  public ResponseEntity<Page<PrescriptionResponse>> getPrescriptionsByMedicalProviderIdPaginated(
          @RequestParam String medicalProviderId,
          @RequestParam List<String> statuses,
          Pageable pageable) {
    Page<PrescriptionResponse> prescriptions = prescriptionService.getPrescriptionsByMedicalProviderId(medicalProviderId, statuses, pageable);
    return ResponseEntity.ok(prescriptions);
  }

  /**
   * Get paginated prescriptions by medic ID and medical provider ID and statuses.
   *
   * @param medicId the ID of the medic
   * @param medicalProviderId the ID of the medical provider
   * @param statuses the list of statuses to filter
   * @param pageable the pagination information
   * @return a page of PrescriptionResponse DTOs
   */
  @GetMapping("/by-medic-and-medical-provider-paginated")
  public ResponseEntity<Page<PrescriptionResponse>> getPrescriptionsByMedicIdAndMedicalProviderIdPaginated(
          @RequestParam String medicId,
          @RequestParam String medicalProviderId,
          @RequestParam List<String> statuses,
          Pageable pageable) {
    Page<PrescriptionResponse> prescriptions = prescriptionService.getPrescriptionsByMedicIdAndMedicalProviderId(medicId, medicalProviderId, statuses, pageable);
    return ResponseEntity.ok(prescriptions);
  }

  /**
   * Get paginated prescriptions by patient ID and medical provider ID and statuses.
   *
   * @param patientId the ID of the patient
   * @param medicalProviderId the ID of the medical provider
   * @param statuses the list of statuses to filter
   * @param pageable the pagination information
   * @return a page of PrescriptionResponse DTOs
   */
  @GetMapping("/by-patient-and-medical-provider-paginated")
  public ResponseEntity<Page<PrescriptionResponse>> getPrescriptionsByPatientIdAndMedicalProviderIdPaginated(
          @RequestParam String patientId,
          @RequestParam String medicalProviderId,
          @RequestParam List<String> statuses,
          Pageable pageable) {
    Page<PrescriptionResponse> prescriptions = prescriptionService.getPrescriptionsByPatientIddAndMedicalProviderId(patientId, medicalProviderId, statuses, pageable);
    return ResponseEntity.ok(prescriptions);
  }

  /**
   * Get the number of prescriptions by medical provider ID and statuses.
   *
   * @param medicalProviderId the ID of the medical provider
   * @param statuses the list of statuses to filter
   * @return the number of prescriptions
   */
  @GetMapping("/count-by-medical-provider")
  public ResponseEntity<Long> getPrescriptionCountByMedicalProviderId(@RequestParam String medicalProviderId, @RequestParam List<String> statuses) {
    long count = prescriptionService.getPrescriptionCountByMedicalProviderId(medicalProviderId, statuses);
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
   * Get paginated prescriptions by medical provider ID, statuses, and date range.
   *
   * @param medicalProviderId the ID of the medical provider
   * @param startDate the start date
   * @param endDate the end date
   * @param statuses the list of statuses to filter
   * @param pageable the pagination information
   * @return a page of PrescriptionResponse DTOs
   */
  @GetMapping("/by-medical-provider-and-date-range")
  public ResponseEntity<Page<PrescriptionResponse>> getPrescriptionsByMedicalProviderIdAndDateRange(
          @RequestParam String medicalProviderId,
          @RequestParam(required = false) LocalDate startDate,
          @RequestParam(required = false) LocalDate endDate,
          @RequestParam List<String> statuses,
          Pageable pageable) {
    Instant startInstant = null;
    Instant endInstant = null;
    if (startDate != null) {
      startInstant = startDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
    }
    if (endDate != null) {
      endInstant = endDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1).minusNanos(1).toInstant();
    }
    Page<PrescriptionResponse> prescriptions = prescriptionService.getPrescriptionsByMedicalProviderIdAndDateRange(medicalProviderId, startInstant, endInstant, statuses, pageable);
    return ResponseEntity.ok(prescriptions);
  }

  /**
   * Get prescriptions by medic ID, statuses, and date range.
   *
   * @param medicId the ID of the medic
   * @param startDate the start date
   * @param endDate the end date
   * @param statuses the list of statuses to filter
   * @return a list of PrescriptionResponse DTOs
   */
  @GetMapping("/by-medic-and-date-range")
  public ResponseEntity<List<PrescriptionResponse>> getPrescriptionsByMedicIdAndDateRange(
          @RequestParam String medicId,
          @RequestParam LocalDate startDate,
          @RequestParam LocalDate endDate,
          @RequestParam List<String> statuses) {
    Instant startInstant = startDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
    Instant endInstant = endDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1).minusNanos(1).toInstant();
    List<PrescriptionResponse> prescriptions = prescriptionService.getPrescriptionsByMedicIdAndDateRange(medicId, startInstant, endInstant, statuses);
    return ResponseEntity.ok(prescriptions);
  }

  @GetMapping("/download/excel")
  public ResponseEntity<Void> downloadExcel(
          @RequestParam String medicalProviderId,
          @RequestParam(required = false) String medicId,
          @RequestParam(required = false) String patientId,
          @RequestParam List<String> statuses,
          @RequestParam(required = false) LocalDate startDate,
          @RequestParam(required = false) LocalDate endDate,
          HttpServletResponse response) throws IOException {

    List<PrescriptionResponse> prescriptions;
    Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "createdAt"));

    if (medicId != null) {
      // http://{{ip}}:{{port}}/api/prescriptions/download/excel?medicId=21a72025-a296-4ce5-a1a8-798a8ebe4da4&medicalProviderId=39&statuses=AVAILABLE,DISPENSED
      prescriptions = prescriptionService.getPrescriptionsByMedicIdAndMedicalProviderId(medicId, medicalProviderId, statuses, pageable).getContent();
    } else if (patientId != null) {
      // http://{{ip}}:{{port}}/api/prescriptions/download/excel?patientId=e6f712b7-cd2d-4df5-a036-05afa28282b8&medicalProviderId=39&statuses=AVAILABLE,DISPENSED
      prescriptions = prescriptionService.getPrescriptionsByPatientIddAndMedicalProviderId(patientId, medicalProviderId, statuses, pageable).getContent();
    } else if (endDate != null) {
      // http://{{ip}}:{{port}}/api/prescriptions/download/excel?medicalProviderId=39&startDate=2024-09-03&endDate=2024-09-11&statuses=AVAILABLE,DISPENSED
      Instant startInstant = startDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
      Instant endInstant = endDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1).minusNanos(1).toInstant();
      prescriptions = prescriptionService.getPrescriptionsByMedicalProviderIdAndDateRange(medicalProviderId, startInstant, endInstant, statuses, pageable).getContent();
    } else {
      // http://{{ip}}:{{port}}/api/prescriptions/download/excel?medicalProviderId=39&statuses=AVAILABLE,DISPENSED
      prescriptions = prescriptionService.getPrescriptionsByMedicalProviderId(medicalProviderId, statuses, pageable).getContent();
    }

    Workbook workbook = prescriptionService.exportToExcel(prescriptions);

    // Set response headers
    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=prescriptions.xlsx");

    // Write the workbook to the response output stream
    workbook.write(response.getOutputStream());
    workbook.close();

    return ResponseEntity.ok().build();
  }
}
