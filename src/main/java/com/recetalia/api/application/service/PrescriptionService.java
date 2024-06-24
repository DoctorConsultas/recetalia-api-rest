package com.recetalia.api.application.service;

import com.recetalia.api.application.dto.request.PrescriptionRequest;
import com.recetalia.api.application.dto.response.PrescriptionResponse;
import com.recetalia.api.application.infrastructure.exception.ResourceNotFoundException;

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
}