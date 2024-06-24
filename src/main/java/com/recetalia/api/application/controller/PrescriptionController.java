package com.recetalia.api.application.controller;

import com.recetalia.api.application.dto.request.PrescriptionRequest;
import com.recetalia.api.application.dto.response.PrescriptionResponse;
import com.recetalia.api.application.service.PrescriptionService;
import com.recetalia.api.application.infrastructure.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}