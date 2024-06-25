package com.recetalia.api.application.service;

import com.recetalia.api.application.dto.request.PatientRequest;
import com.recetalia.api.application.dto.response.PatientResponse;
import com.recetalia.api.application.infrastructure.exception.ResourceNotFoundException;

import java.util.List;

/**
 * Service interface for Patient operations.
 */
public interface PatientService {

  /**
   * Creates a new Patient.
   *
   * @param request the PatientRequest DTO
   * @return the created PatientResponse DTO
   */
  PatientResponse createPatient(PatientRequest request);

  /**
   * Updates an existing Patient.
   *
   * @param id the ID of the Patient to update
   * @param request the PatientRequest DTO
   * @return the updated PatientResponse DTO
   * @throws ResourceNotFoundException if the Patient is not found
   */
  PatientResponse updatePatient(String id, PatientRequest request) throws ResourceNotFoundException;

  /**
   * Gets a Patient by ID.
   *
   * @param id the ID of the Patient
   * @return the PatientResponse DTO
   * @throws ResourceNotFoundException if the Patient is not found
   */
  PatientResponse getPatientById(String id) throws ResourceNotFoundException;

  /**
   * Deletes a Patient by ID.
   *
   * @param id the ID of the Patient
   * @throws ResourceNotFoundException if the Patient is not found
   */
  void deletePatient(String id) throws ResourceNotFoundException;

  /**
   * Gets all Patients.
   *
   * @return a list of PatientResponse DTOs
   */
  List<PatientResponse> getAllPatients();

  /**
   * Gets all Patients associated with a specific Medic and Medical Provider.
   *
   * @param medicId the ID of the Medic
   * @param medicalProviderId the ID of the Medical Provider
   * @return a list of PatientResponse DTOs
   */
  List<PatientResponse> getPatientsByMedicAndMedicalProvider(String medicId, String medicalProviderId);
}