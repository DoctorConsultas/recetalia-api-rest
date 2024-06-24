package com.recetalia.api.application.controller;

import com.recetalia.api.application.dto.request.PatientRequest;
import com.recetalia.api.application.dto.response.PatientResponse;
import com.recetalia.api.application.service.PatientService;
import com.recetalia.api.application.infrastructure.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling Patient-related HTTP requests.
 */
@RestController
@RequestMapping("/api/patients")
public class PatientController {

  @Autowired
  private PatientService patientService;

  /**
   * Create a new Patient.
   *
   * @param request the PatientRequest DTO
   * @return the created PatientResponse DTO
   */
  @PostMapping
  public ResponseEntity<PatientResponse> createPatient(@RequestBody PatientRequest request) {
    PatientResponse response = patientService.createPatient(request);
    return ResponseEntity.ok(response);
  }

  /**
   * Update an existing Patient.
   *
   * @param id the ID of the Patient to update
   * @param request the PatientRequest DTO
   * @return the updated PatientResponse DTO
   * @throws ResourceNotFoundException if the Patient is not found
   */
  @PutMapping("/{id}")
  public ResponseEntity<PatientResponse> updatePatient(@PathVariable String id, @RequestBody PatientRequest request) throws ResourceNotFoundException {
    PatientResponse response = patientService.updatePatient(id, request);
    return ResponseEntity.ok(response);
  }

  /**
   * Get a Patient by ID.
   *
   * @param id the ID of the Patient
   * @return the PatientResponse DTO
   * @throws ResourceNotFoundException if the Patient is not found
   */
  @GetMapping("/{id}")
  public ResponseEntity<PatientResponse> getPatientById(@PathVariable String id) throws ResourceNotFoundException {
    PatientResponse response = patientService.getPatientById(id);
    return ResponseEntity.ok(response);
  }

  /**
   * Delete a Patient by ID.
   *
   * @param id the ID of the Patient
   * @return a response entity indicating the result of the operation
   * @throws ResourceNotFoundException if the Patient is not found
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePatient(@PathVariable String id) throws ResourceNotFoundException {
    patientService.deletePatient(id);
    return ResponseEntity.noContent().build();
  }

  /**
   * Get all Patients.
   *
   * @return a list of PatientResponse DTOs
   */
  @GetMapping
  public ResponseEntity<List<PatientResponse>> getAllPatients() {
    List<PatientResponse> responses = patientService.getAllPatients();
    return ResponseEntity.ok(responses);
  }
}