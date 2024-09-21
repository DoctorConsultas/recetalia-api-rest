package com.recetalia.api.application.service.impl;

import com.recetalia.api.application.domain.model.entities.Medic;
import com.recetalia.api.application.dto.mapper.request.MedicRequestMapper;
import com.recetalia.api.application.dto.mapper.response.MedicResponseMapper;
import com.recetalia.api.application.dto.request.MedicRequest;
import com.recetalia.api.application.dto.response.MedicResponse;
import com.recetalia.api.application.dto.response.MedicalProviderResponse;
import com.recetalia.api.application.infrastructure.exception.ResourceNotFoundException;
import com.recetalia.api.application.service.MedicService;
import com.recetalia.api.application.domain.repository.MedicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementation of the MedicService interface.
 * Provides business logic for Medic operations such as creating, updating, and retrieving Medics.
 */
@Service
public class MedicServiceImpl implements MedicService {

  @Autowired
  private MedicRepository medicRepository;

  @Autowired
  private MedicRequestMapper requestMapper;

  @Autowired
  private MedicResponseMapper responseMapper;

  @Autowired
  private CurrentUserAuthenticatedServiceImpl currentUserAuthenticatedService;

  /**
   * Creates a new Medic in the system.
   *
   * @param request the MedicRequest DTO containing Medic details
   * @return the created MedicResponse DTO
   */
  @Override
  public MedicResponse createMedic(MedicRequest request) {
    Medic medic = requestMapper.toEntity(request);
    medic = medicRepository.save(medic); // No need to manually set the UUID
    return responseMapper.toDto(medic); // Convert the Medic entity to DTO and return
  }

  /**
   * Updates an existing Medic's details.
   *
   * @param id      the ID of the Medic to update
   * @param request the MedicRequest DTO with the updated details
   * @return the updated MedicResponse DTO
   * @throws ResourceNotFoundException if the Medic is not found
   * @throws IllegalArgumentException if the email is already in use by another Medic
   */
  @Override
  public MedicResponse updateMedic(String id, MedicRequest request) throws ResourceNotFoundException {
    // Find the Medic by ID, or throw an exception if not found
    Medic medic = medicRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Medic not found for this id :: " + id));

    // Check if the email in the request is already in use by another Medic
    if (!medic.getEmail().equals(request.getEmail())) {
      // If the email is different, check if it already exists in the database
      Optional<Medic> existingMedic = medicRepository.findByEmail(request.getEmail());
      if (existingMedic.isPresent() && !existingMedic.get().getId().equals(id)) {
        // If another Medic with the same email exists, throw an exception
        throw new IllegalArgumentException("Email is already in use by another Medic.");
      }
    }

    // Update the Medic entity with the new values
    requestMapper.updateEntityFromDto(request, medic);

    // Save the updated Medic entity
    medic = medicRepository.save(medic);

    // Convert the updated entity to DTO and return
    return responseMapper.toDto(medic);
  }

  /**
   * Retrieves a Medic by its ID.
   *
   * @param id the ID of the Medic to retrieve
   * @return the MedicResponse DTO
   * @throws ResourceNotFoundException if the Medic is not found
   */
  @Override
  public MedicResponse getMedicById(String id) throws ResourceNotFoundException {
    Medic medic = medicRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Medic not found for this id :: " + id)); // Throw exception if not found
    return responseMapper.toDto(medic); // Convert the entity to DTO and return
  }

  /**
   * Deletes a Medic by its ID.
   *
   * @param id the ID of the Medic to delete
   * @throws ResourceNotFoundException if the Medic is not found
   */
  @Override
  public void deleteMedic(String id) throws ResourceNotFoundException {
    Medic medic = medicRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Medic not found for this id :: " + id)); // Throw exception if not found
    medicRepository.delete(medic); // Delete the Medic entity
  }

  /**
   * Retrieves all Medics in the system.
   *
   * @return a list of MedicResponse DTOs
   */
  @Override
  public List<MedicResponse> getAllMedics() {
    return medicRepository.findAll().stream()
            .map(responseMapper::toDto) // Convert each Medic entity to DTO
            .collect(Collectors.toList()); // Return a list of MedicResponse DTOs
  }

  /**
   * Retrieves paginated Medics with an optional search keyword.
   * The search keyword can be used to filter Medics by name, lastname, email, or cjp.
   * If no keyword is provided (null or empty), all Medics are returned with pagination.
   *
   * @param pageable pagination information (page number and size)
   * @param searchKeyword the optional search keyword for filtering by name, lastname, email, or cjp
   * @return a paginated list of MedicResponse DTOs
   */
  @Override
  public Page<MedicResponse> findAllWithPagination(String searchKeyword, Pageable pageable) {
    // Query repository for paginated Medics with optional filtering
    Page<Medic> medics = medicRepository.findAllWithPagination(searchKeyword, pageable);

    // Map the Medic entities to MedicResponse DTOs and return paginated result
    return medics.map(responseMapper::toDto);
  }
  /**
   * Retrieves all Medics associated with a specific Medical Provider.
   *
   * @param medicalProviderId the ID of the Medical Provider
   * @return a list of MedicResponse DTOs associated with the provider
   */
  @Override
  public List<MedicResponse> getMedicsByMedicalProviderId(String medicalProviderId) {
    MedicalProviderResponse medicalProvider = currentUserAuthenticatedService.getCurrentMedicalProvider();
    medicalProviderId = medicalProvider.getId(); // Get the current Medical Provider ID
    List<Medic> medics = medicRepository.findByMedicalProviderId(medicalProviderId); // Get Medics by Medical Provider ID
    return medics.stream()
            .map(responseMapper::toDto) // Convert each Medic entity to DTO
            .collect(Collectors.toList());
  }

  /**
   * Retrieves Medics by Medical Provider ID and search criteria (name, lastname, email).
   *
   * @param medicalProviderId the ID of the Medical Provider
   * @param searchCriteria    search criteria to filter Medics by name, lastname, or email
   * @param limit             the maximum number of results to return
   * @return a list of MedicResponse DTOs matching the search criteria
   */
  @Override
  public List<MedicResponse> getMedicsByMedicalProviderIdAndSearchCriteria(String medicalProviderId, String searchCriteria, int limit) {
    MedicalProviderResponse medicalProvider = this.currentUserAuthenticatedService.getCurrentMedicalProvider();
    medicalProviderId = medicalProvider.getId();
    return medicRepository.findByMedicalProviderIdAndSearchCriteria(medicalProviderId, searchCriteria, limit).stream()
            .map(responseMapper::toDto)
            .collect(Collectors.toList());
  }
}
