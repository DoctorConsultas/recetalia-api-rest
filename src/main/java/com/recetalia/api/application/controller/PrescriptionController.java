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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

  /**
   * Download prescriptions as an Excel file based on various filters.
   *
   * @param medicId the ID of the medic (optional)
   * @param patientId the ID of the patient (optional)
   * @param statuses the list of statuses to filter
   * @param startDate the start date for filtering by date range (optional)
   * @param endDate the end date for filtering by date range (optional)
   * @param response the HTTP response to write the Excel file to
   * @param pageable the pagination information
   * @return a ResponseEntity indicating the status of the operation
   * @throws IOException if an I/O error occurs
   */
  @GetMapping("/get-prescriptions-by-filters")
  public ResponseEntity<Page<PrescriptionResponse>> getPrescriptionsByFilters(
          @RequestParam(required = false) String medicId,
          @RequestParam(required = false) String patientId,
          @RequestParam List<String> statuses,
          @RequestParam(required = false) LocalDate startDate,
          @RequestParam(required = false) LocalDate endDate,
          Pageable pageable,
          HttpServletResponse response) throws IOException {

    Instant startInstant = startDate != null ? startDate.atStartOfDay(ZoneId.systemDefault()).toInstant() : null;
    Instant endInstant = endDate != null ? endDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1).minusNanos(1).toInstant() : null;

    Page<PrescriptionResponse> prescriptions = prescriptionService.getPrescriptionsByFilters(
            medicId, patientId, statuses, startInstant, endInstant, pageable);

    return ResponseEntity.ok(prescriptions);
  }

  /**
   * Download prescriptions as an Excel file based on various filters.
   *
   * @param medicId the ID of the medic (optional)
   * @param patientId the ID of the patient (optional)
   * @param statuses the list of statuses to filter
   * @param startDate the start date for filtering by date range (optional)
   * @param endDate the end date for filtering by date range (optional)
   * @param response the HTTP response to write the Excel file to
   * @return a ResponseEntity indicating the status of the operation
   * @throws IOException if an I/O error occurs
   */
  @GetMapping("/download/excel")
  public ResponseEntity<Void> downloadExcel(
          @RequestParam(required = false) String medicId,
          @RequestParam(required = false) String patientId,
          @RequestParam List<String> statuses,
          @RequestParam(required = false) LocalDate startDate,
          @RequestParam(required = false) LocalDate endDate,
          HttpServletResponse response) throws IOException {

    Pageable pageable = PageRequest.of(0, 10000, Sort.by(Sort.Direction.DESC, "createdAt"));
    Instant startInstant = startDate != null ? startDate.atStartOfDay(ZoneId.systemDefault()).toInstant() : null;
    Instant endInstant = endDate != null ? endDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1).minusNanos(1).toInstant() : null;

    List<PrescriptionResponse> prescriptions = prescriptionService.getPrescriptionsByFilters(
              medicId, patientId, statuses, startInstant, endInstant, pageable).getContent();

    Workbook workbook = prescriptionService.exportToExcel(prescriptions);

    // Set response headers
    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=prescriptions.xlsx");

    // Write the workbook to the response output stream
    workbook.write(response.getOutputStream());
    workbook.close();

    return ResponseEntity.ok().build();
  }

  /**
   * Endpoint to get admin data with role-based access control.
   *
   * @return a ResponseEntity containing the admin data
   */
  @GetMapping("/admin-data")
  @PreAuthorize("hasRole('TESTROLEr')")
  public ResponseEntity<String> getAdminData() {
    return ResponseEntity.ok("PRESTADORES data");
  }

  @GetMapping("/admin-data2")
  public ResponseEntity<String> getAdminData2() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null && authentication.isAuthenticated()) {
      Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
      String roles = authorities.stream()
              .map(GrantedAuthority::getAuthority)
              .collect(Collectors.joining(", "));

      return ResponseEntity.ok(roles);
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated.");
    }
  }
}
