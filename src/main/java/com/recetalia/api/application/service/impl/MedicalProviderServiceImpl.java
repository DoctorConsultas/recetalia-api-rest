package com.recetalia.api.application.service.impl;

import com.recetalia.api.application.domain.model.entities.MedicalProvider;
import com.recetalia.api.application.dto.mapper.request.MedicalProviderRequestMapper;
import com.recetalia.api.application.dto.mapper.response.MedicalProviderResponseMapper;
import com.recetalia.api.application.dto.request.MedicalProviderRequest;
import com.recetalia.api.application.dto.response.MedicalProviderResponse;
import com.recetalia.api.application.infrastructure.exception.ResourceNotFoundException;
import com.recetalia.api.application.service.MedicalProviderService;
import com.recetalia.api.application.domain.repository.MedicalProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the MedicalProviderService interface.
 */
@Service
public class MedicalProviderServiceImpl implements MedicalProviderService {

  @Autowired
  private MedicalProviderRepository medicalProviderRepository;

  @Autowired
  private MedicalProviderRequestMapper requestMapper;

  @Autowired
  private MedicalProviderResponseMapper responseMapper;

  @Override
  public MedicalProviderResponse createMedicalProvider(MedicalProviderRequest request) {
    MedicalProvider medicalProvider = requestMapper.toEntity(request);
    medicalProvider = medicalProviderRepository.save(medicalProvider);
    return responseMapper.toDto(medicalProvider);
  }

  @Override
  public MedicalProviderResponse updateMedicalProvider(String id, MedicalProviderRequest request) throws ResourceNotFoundException {
    MedicalProvider medicalProvider = medicalProviderRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("MedicalProvider not found for this id :: " + id));
    requestMapper.updateEntityFromDto(request, medicalProvider);
    medicalProvider = medicalProviderRepository.save(medicalProvider);
    return responseMapper.toDto(medicalProvider);
  }

  @Override
  public MedicalProviderResponse getMedicalProviderById(String id) throws ResourceNotFoundException {
    MedicalProvider medicalProvider = medicalProviderRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("MedicalProvider not found for this id :: " + id));
    return responseMapper.toDto(medicalProvider);
  }

  @Override
  public void deleteMedicalProvider(String id) throws ResourceNotFoundException {
    MedicalProvider medicalProvider = medicalProviderRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("MedicalProvider not found for this id :: " + id));
    medicalProviderRepository.delete(medicalProvider);
  }

  @Override
  public List<MedicalProviderResponse> getAllMedicalProviders() {
    return medicalProviderRepository.findAll().stream()
            .map(responseMapper::toDto)
            .collect(Collectors.toList());
  }


}