package com.recetalia.api.application.service.impl;

import com.recetalia.api.application.domain.model.entities.Patient;
import com.recetalia.api.application.dto.mapper.request.PatientRequestMapper;
import com.recetalia.api.application.dto.mapper.response.PatientResponseMapper;
import com.recetalia.api.application.dto.request.PatientRequest;
import com.recetalia.api.application.dto.response.PatientResponse;
import com.recetalia.api.application.service.PatientService;
import com.recetalia.api.application.infrastructure.exception.ResourceNotFoundException;
import com.recetalia.api.application.domain.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the PatientService interface.
 */
@Service
public class PatientServiceImpl implements PatientService {

  @Autowired
  private PatientRepository patientRepository;

  @Autowired
  private PatientRequestMapper requestMapper;

  @Autowired
  private PatientResponseMapper responseMapper;

  @Override
  public PatientResponse createPatient(PatientRequest request) {
    Patient patient = requestMapper.toEntity(request);
    patient = patientRepository.save(patient);
    return responseMapper.toDto(patient);
  }

  @Override
  public PatientResponse updatePatient(String id, PatientRequest request) throws ResourceNotFoundException {
    Patient patient = patientRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + id));
    requestMapper.updateEntityFromDto(request, patient);
    patient = patientRepository.save(patient);
    return responseMapper.toDto(patient);
  }

  @Override
  public PatientResponse getPatientById(String id) throws ResourceNotFoundException {
    Patient patient = patientRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + id));
    return responseMapper.toDto(patient);
  }

  @Override
  public void deletePatient(String id) throws ResourceNotFoundException {
    Patient patient = patientRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + id));
    patientRepository.delete(patient);
  }

  @Override
  public List<PatientResponse> getAllPatients() {
    return patientRepository.findAll().stream()
            .map(responseMapper::toDto)
            .collect(Collectors.toList());
  }
}