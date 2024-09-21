package com.recetalia.api.application.service;

import com.recetalia.api.application.dto.request.MedicRequest;
import com.recetalia.api.application.dto.response.MedicResponse;
import com.recetalia.api.application.infrastructure.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service interface for Medic operations.
 */
public interface MedicService {

  /**
   * Creates a new Medic.
   *
   * @param request the MedicRequest DTO containing the Medic data
   * @return the created MedicResponse DTO
   */
  MedicResponse createMedic(MedicRequest request);

  /**
   * Updates an existing Medic.
   *
   * @param id the ID of the Medic to update
   * @param request the MedicRequest DTO with updated data
   * @return the updated MedicResponse DTO
   * @throws ResourceNotFoundException if the Medic is not found
   */
  MedicResponse updateMedic(String id, MedicRequest request) throws ResourceNotFoundException;

  /**
   * Retrieves a Medic by its ID.
   *
   * @param id the ID of the Medic
   * @return the MedicResponse DTO
   * @throws ResourceNotFoundException if the Medic is not found
   */
  MedicResponse getMedicById(String id) throws ResourceNotFoundException;

  /**
   * Deletes a Medic by its ID.
   *
   * @param id the ID of the Medic to delete
   * @throws ResourceNotFoundException if the Medic is not found
   */
  void deleteMedic(String id) throws ResourceNotFoundException;

  /**
   * Retrieves all Medics.
   *
   * @return a list of MedicResponse DTOs
   */
  List<MedicResponse> getAllMedics();

  /**
   * Retrieves all Medics with pagination support.
   *
   * @param pageable pagination information
   * @return a paginated list of MedicResponse DTOs
   */
  public Page<MedicResponse> findAllWithPagination(String searchKeyword, Pageable pageable);

  /**
   * Retrieves all Medics associated with a specific Medical Provider.
   *
   * @param medicalProviderId the ID of the Medical Provider
   * @return a list of MedicResponse DTOs associated with the provider
   */
  List<MedicResponse> getMedicsByMedicalProviderId(String medicalProviderId);

  /**
   * Retrieves Medics by Medical Provider ID and search criteria (name, lastname, email).
   *
   * @param medicalProviderId the ID of the Medical Provider
   * @param searchCriteria the search criteria to match against name, lastname, or email
   * @param limit the maximum number of results to return
   * @return a list of MedicResponse DTOs that match the search criteria
   */
  List<MedicResponse> getMedicsByMedicalProviderIdAndSearchCriteria(String medicalProviderId, String searchCriteria, int limit);
}
