package com.recetalia.api.application.controller;

import com.recetalia.api.application.dto.request.MedicalProviderLoginRequest;
import com.recetalia.api.application.dto.request.MedicalProviderRequest;
import com.recetalia.api.application.dto.response.MedicalProviderResponse;
import com.recetalia.api.application.service.MedicalProviderService;
import com.recetalia.api.application.infrastructure.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling MedicalProvider-related HTTP requests.
 */
@RestController
@RequestMapping("/api/medical-providers")
public class MedicalProviderController {

  @Autowired
  private MedicalProviderService medicalProviderService;

  /**
   * Create a new MedicalProvider.
   *
   * @param request the MedicalProviderRequest DTO
   * @return the created MedicalProviderResponse DTO
   */
  @PostMapping
  public ResponseEntity<MedicalProviderResponse> createMedicalProvider(@RequestBody MedicalProviderRequest request) {
    MedicalProviderResponse response = medicalProviderService.createMedicalProvider(request);
    return ResponseEntity.ok(response);
  }

  /**
   * Update an existing MedicalProvider.
   *
   * @param id the ID of the MedicalProvider to update
   * @param request the MedicalProviderRequest DTO
   * @return the updated MedicalProviderResponse DTO
   * @throws ResourceNotFoundException if the MedicalProvider is not found
   */
  @PutMapping("/{id}")
  public ResponseEntity<MedicalProviderResponse> updateMedicalProvider(@PathVariable String id, @RequestBody MedicalProviderRequest request) throws ResourceNotFoundException {
    MedicalProviderResponse response = medicalProviderService.updateMedicalProvider(id, request);
    return ResponseEntity.ok(response);
  }

  /**
   * Get a MedicalProvider by ID.
   *
   * @param id the ID of the MedicalProvider
   * @return the MedicalProviderResponse DTO
   * @throws ResourceNotFoundException if the MedicalProvider is not found
   */
  @GetMapping("/{id}")
  public ResponseEntity<MedicalProviderResponse> getMedicalProviderById(@PathVariable String id) throws ResourceNotFoundException {
    MedicalProviderResponse response = medicalProviderService.getMedicalProviderById(id);
    return ResponseEntity.ok(response);
  }

  /**
   * Delete a MedicalProvider by ID.
   *
   * @param id the ID of the MedicalProvider
   * @return a response entity indicating the result of the operation
   * @throws ResourceNotFoundException if the MedicalProvider is not found
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMedicalProvider(@PathVariable String id) throws ResourceNotFoundException {
    medicalProviderService.deleteMedicalProvider(id);
    return ResponseEntity.noContent().build();
  }

  /**
   * Get all MedicalProviders.
   *
   * @return a list of MedicalProviderResponse DTOs
   */
  @GetMapping
  public ResponseEntity<List<MedicalProviderResponse>> getAllMedicalProviders() {
    List<MedicalProviderResponse> responses = medicalProviderService.getAllMedicalProviders();
    return ResponseEntity.ok(responses);
  }

  /**
   * Authenticate a MedicalProvider using email and password.
   *
   * @param loginRequest the MedicalProviderLoginRequest DTO
   * @return the authenticated MedicalProviderResponse DTO
   */
  @PostMapping("/login")
  public ResponseEntity<MedicalProviderResponse> login(@RequestBody MedicalProviderLoginRequest loginRequest) {
    MedicalProviderResponse response = medicalProviderService.login(loginRequest);
    return ResponseEntity.ok(response);
  }
}
