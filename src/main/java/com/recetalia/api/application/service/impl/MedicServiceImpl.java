package com.recetalia.api.application.service.impl;

import com.recetalia.api.application.domain.model.entities.Medic;
import com.recetalia.api.application.dto.mapper.request.MedicRequestMapper;
import com.recetalia.api.application.dto.mapper.response.MedicResponseMapper;
import com.recetalia.api.application.dto.request.MedicRequest;
import com.recetalia.api.application.dto.response.MedicResponse;
import com.recetalia.api.application.dto.response.MedicalProviderResponse;
import com.recetalia.api.application.service.MedicService;
import com.recetalia.api.application.infrastructure.exception.ResourceNotFoundException;
import com.recetalia.api.application.domain.repository.MedicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the MedicService interface.
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

  @Override
  public MedicResponse createMedic(MedicRequest request) {
    Medic medic = requestMapper.toEntity(request);
    medic = medicRepository.save(medic);
    return responseMapper.toDto(medic);
  }

  @Override
  public MedicResponse updateMedic(String id, MedicRequest request) throws ResourceNotFoundException {
    Medic medic = medicRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Medic not found for this id :: " + id));
    requestMapper.updateEntityFromDto(request, medic);
    medic = medicRepository.save(medic);
    return responseMapper.toDto(medic);
  }

  @Override
  public MedicResponse getMedicById(String id) throws ResourceNotFoundException {
    Medic medic = medicRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Medic not found for this id :: " + id));
    return responseMapper.toDto(medic);
  }

  @Override
  public void deleteMedic(String id) throws ResourceNotFoundException {
    Medic medic = medicRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Medic not found for this id :: " + id));
    medicRepository.delete(medic);
  }

  @Override
  public List<MedicResponse> getAllMedics() {
    return medicRepository.findAll().stream()
            .map(responseMapper::toDto)
            .collect(Collectors.toList());
  }

  @Override
  public List<MedicResponse> getMedicsByMedicalProviderId(String medicalProviderId) {
    MedicalProviderResponse medicalProvider = this.currentUserAuthenticatedService.getCurrentMedicalProvider();
    medicalProviderId = medicalProvider.getId();
    List<Medic> medics = medicRepository.findByMedicalProviderId(medicalProviderId);
    return medics.stream()
            .map(responseMapper::toDto)
            .collect(Collectors.toList());
  }

  @Override
  public List<MedicResponse> getMedicsByMedicalProviderIdAndSearchCriteria(String medicalProviderId, String searchCriteria, int limit) {
    MedicalProviderResponse medicalProvider = this.currentUserAuthenticatedService.getCurrentMedicalProvider();
    medicalProviderId = medicalProvider.getId();
    return medicRepository.findByMedicalProviderIdAndSearchCriteria(medicalProviderId, searchCriteria, limit).stream()
            .map(responseMapper::toDto)
            .collect(Collectors.toList());
  }
}
