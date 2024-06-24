package com.recetalia.api.application.service;


import com.recetalia.api.application.dto.request.MedicRequest;
import com.recetalia.api.application.dto.response.MedicResponse;
import com.recetalia.api.application.infrastructure.exception.ResourceNotFoundException;

import java.util.List;

/**
 * Service interface for Medic operations.
 */
public interface MedicService {

  /**
   * Creates a new Medic.
   *
   * @param request the MedicRequest DTO
   * @return the created MedicResponse DTO
   */
  MedicResponse createMedic(MedicRequest request);

  /**
   * Updates an existing Medic.
   *
   * @param id the ID of the Medic to update
   * @param request the MedicRequest DTO
   * @return the updated MedicResponse DTO
   * @throws ResourceNotFoundException if the Medic is not found
   */
  MedicResponse updateMedic(String id, MedicRequest request) throws ResourceNotFoundException;

  /**
   * Gets a Medic by ID.
   *
   * @param id the ID of the Medic
   * @return the MedicResponse DTO
   * @throws ResourceNotFoundException if the Medic is not found
   */
  MedicResponse getMedicById(String id) throws ResourceNotFoundException;

  /**
   * Deletes a Medic by ID.
   *
   * @param id the ID of the Medic
   * @throws ResourceNotFoundException if the Medic is not found
   */
  void deleteMedic(String id) throws ResourceNotFoundException;

  /**
   * Gets all Medics.
   *
   * @return a list of MedicResponse DTOs
   */
  List<MedicResponse> getAllMedics();
}