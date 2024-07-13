package com.recetalia.api.application.service;

import com.recetalia.api.application.dto.request.MedicalProviderLoginRequest;
import com.recetalia.api.application.dto.request.MedicalProviderRequest;
import com.recetalia.api.application.dto.response.MedicalProviderResponse;
import com.recetalia.api.application.infrastructure.exception.*;

import java.util.List;

/**
 * Service interface for MedicalProvider operations.
 */
public interface MedicalProviderService {

  /**
   * Creates a new MedicalProvider.
   *
   * @param request the MedicalProviderRequest DTO
   * @return the created MedicalProviderResponse DTO
   */
  MedicalProviderResponse createMedicalProvider(MedicalProviderRequest request);

  /**
   * Updates an existing MedicalProvider.
   *
   * @param id the ID of the MedicalProvider to update
   * @param request the MedicalProviderRequest DTO
   * @return the updated MedicalProviderResponse DTO
   * @throws ResourceNotFoundException if the MedicalProvider is not found
   */
  MedicalProviderResponse updateMedicalProvider(String id, MedicalProviderRequest request) throws ResourceNotFoundException;

  /**
   * Gets a MedicalProvider by ID.
   *
   * @param id the ID of the MedicalProvider
   * @return the MedicalProviderResponse DTO
   * @throws ResourceNotFoundException if the MedicalProvider is not found
   */
  MedicalProviderResponse getMedicalProviderById(String id) throws ResourceNotFoundException;

  /**
   * Deletes a MedicalProvider by ID.
   *
   * @param id the ID of the MedicalProvider
   * @throws ResourceNotFoundException if the MedicalProvider is not found
   */
  void deleteMedicalProvider(String id) throws ResourceNotFoundException;

  /**
   * Gets all MedicalProviders.
   *
   * @return a list of MedicalProviderResponse DTOs
   */
  List<MedicalProviderResponse> getAllMedicalProviders();

  /**
   * Authenticates a MedicalProvider using email and password.
   *
   * @param loginRequest the login request DTO
   * @return the authenticated MedicalProviderResponse DTO
   * @throws ResourceNotFoundException if the MedicalProvider is not found
   */
  MedicalProviderResponse login(MedicalProviderLoginRequest loginRequest) throws ResourceNotFoundException;
}