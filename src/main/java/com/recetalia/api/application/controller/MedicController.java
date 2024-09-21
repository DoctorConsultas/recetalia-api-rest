package com.recetalia.api.application.controller;

import com.recetalia.api.application.dto.request.MedicRequest;
import com.recetalia.api.application.dto.response.MedicResponse;
import com.recetalia.api.application.service.MedicService;
import com.recetalia.api.application.infrastructure.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling Medic-related HTTP requests.
 */
@RestController
@RequestMapping("/api/medics")
public class MedicController {

  @Autowired
  private MedicService medicService;

  /**
   * Create a new Medic.
   *
   * @param request the MedicRequest DTO
   * @return the created MedicResponse DTO
   */
  @PostMapping
  public ResponseEntity<MedicResponse> createMedic(@Valid @RequestBody MedicRequest request) {
    MedicResponse response = medicService.createMedic(request);
    return ResponseEntity.ok(response);
  }

  /**
   * Update an existing Medic.
   *
   * @param id the ID of the Medic to update
   * @param request the MedicRequest DTO
   * @return the updated MedicResponse DTO
   * @throws ResourceNotFoundException if the Medic is not found
   */
  @PutMapping("/{id}")
  public ResponseEntity<MedicResponse> updateMedic(@PathVariable String id, @Valid @RequestBody MedicRequest request)
          throws ResourceNotFoundException {
    MedicResponse response = medicService.updateMedic(id, request);
    return ResponseEntity.ok(response);
  }

  /**
   * Get a Medic by ID.
   *
   * @param id the ID of the Medic
   * @return the MedicResponse DTO
   * @throws ResourceNotFoundException if the Medic is not found
   */
  @GetMapping("/{id}")
  public ResponseEntity<MedicResponse> getMedicById(@PathVariable String id) throws ResourceNotFoundException {
    MedicResponse response = medicService.getMedicById(id);
    return ResponseEntity.ok(response);
  }

  /**
   * Delete a Medic by ID.
   *
   * @param id the ID of the Medic
   * @return a response entity indicating the result of the operation
   * @throws ResourceNotFoundException if the Medic is not found
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMedic(@PathVariable String id) throws ResourceNotFoundException {
    medicService.deleteMedic(id);
    return ResponseEntity.noContent().build();
  }

  /**
   * Get all Medics.
   *
   * @return a list of MedicResponse DTOs
   */
  @GetMapping
  public ResponseEntity<List<MedicResponse>> getAllMedics() {
    List<MedicResponse> responses = medicService.getAllMedics();
    return ResponseEntity.ok(responses);
  }

  /**
   * Get all Medics with pagination support and an optional search keyword.
   * The search keyword can be used to filter Medics by name, lastname, email, or cjp.
   * If no search keyword is provided, all Medics are returned with pagination.
   *
   * @param pageable pagination information (page number and size)
   * @param searchKeyword the optional search keyword for filtering by name, lastname, email, or cjp
   * @return a paginated list of MedicResponse DTOs
   */
  @GetMapping("/pageable")
  public ResponseEntity<Page<MedicResponse>> findAllWithPagination(
          Pageable pageable,
          @RequestParam(required = false) String searchKeyword) {
    Page<MedicResponse> responses = medicService.findAllWithPagination(searchKeyword, pageable);
    return ResponseEntity.ok(responses);
  }

  /**
   * Get all Medics associated with a specific Medical Provider.
   *
   * @param medicalProviderId the ID of the Medical Provider
   * @return a list of MedicResponse DTOs
   */
  @GetMapping("/by-medical-provider/{medicalProviderId}")
  public ResponseEntity<List<MedicResponse>> getMedicsByMedicalProviderId(@PathVariable String medicalProviderId) {
    List<MedicResponse> responses = medicService.getMedicsByMedicalProviderId(medicalProviderId);
    return ResponseEntity.ok(responses);
  }

  /**
   * Get Medics by medical provider ID and search criteria.
   *
   * @param medicalProviderId the ID of the medical provider
   * @param searchCriteria the search criteria to match against name, lastname, or email
   * @return a list of MedicResponse DTOs
   */
  @GetMapping("/search")
  public ResponseEntity<List<MedicResponse>> getMedicsByMedicalProviderIdAndSearchCriteria(
          @RequestParam String medicalProviderId,
          @RequestParam String searchCriteria) {
    List<MedicResponse> responses = medicService.getMedicsByMedicalProviderIdAndSearchCriteria(medicalProviderId, searchCriteria, 5000);
    return ResponseEntity.ok(responses);
  }
}