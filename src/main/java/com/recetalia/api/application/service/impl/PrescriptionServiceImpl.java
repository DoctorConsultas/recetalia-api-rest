package com.recetalia.api.application.service.impl;

import com.recetalia.api.application.domain.model.entities.Prescription;
import com.recetalia.api.application.dto.mapper.request.PrescriptionRequestMapper;
import com.recetalia.api.application.dto.mapper.response.PrescriptionResponseMapper;
import com.recetalia.api.application.dto.request.PrescriptionRequest;
import com.recetalia.api.application.dto.response.PrescriptionResponse;
import com.recetalia.api.application.service.PrescriptionService;
import com.recetalia.api.application.infrastructure.exception.ResourceNotFoundException;
import com.recetalia.api.application.domain.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the PrescriptionService interface.
 */
@Service
public class PrescriptionServiceImpl implements PrescriptionService {

  @Autowired
  private PrescriptionRepository prescriptionRepository;

  @Autowired
  private PrescriptionRequestMapper requestMapper;

  @Autowired
  private PrescriptionResponseMapper responseMapper;

  @Override
  public PrescriptionResponse createPrescription(PrescriptionRequest request) {
    Prescription prescription = requestMapper.toEntity(request);
    prescription = prescriptionRepository.save(prescription);
    return responseMapper.toDto(prescription);
  }

  @Override
  public PrescriptionResponse updatePrescription(String id, PrescriptionRequest request) throws ResourceNotFoundException {
    Prescription prescription = prescriptionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Prescription not found for this id :: " + id));
    requestMapper.updateEntityFromDto(request, prescription);
    prescription = prescriptionRepository.save(prescription);
    return responseMapper.toDto(prescription);
  }

  @Override
  public PrescriptionResponse getPrescriptionById(String id) throws ResourceNotFoundException {
    Prescription prescription = prescriptionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Prescription not found for this id :: " + id));
    return responseMapper.toDto(prescription);
  }

  @Override
  public void deletePrescription(String id) throws ResourceNotFoundException {
    Prescription prescription = prescriptionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Prescription not found for this id :: " + id));
    prescriptionRepository.delete(prescription);
  }

  @Override
  public List<PrescriptionResponse> getAllPrescriptions() {
    return prescriptionRepository.findAll().stream()
            .map(responseMapper::toDto)
            .collect(Collectors.toList());
  }

  @Override
  public Page<PrescriptionResponse> getPrescriptionsByMedicalProviderId(String medicalProviderId, Pageable pageable) {
    Page<Prescription> prescriptions = prescriptionRepository.findPrescriptionsByMedicalProviderId(medicalProviderId, pageable);
    return prescriptions.map(responseMapper::toDto);
  }

  @Override
  public Page<PrescriptionResponse> getPrescriptionsByMedicIdAndMedicalProviderId(String medicId, String medicalProviderId, Pageable pageable) {
    Page<Prescription> prescriptions = prescriptionRepository.findPrescriptionsByMedicIdAndMedicalProviderId(medicId, medicalProviderId, pageable);
    return prescriptions.map(responseMapper::toDto);
  }

  @Override
  public long getPrescriptionCountByMedicalProviderId(String medicalProviderId) {
    return prescriptionRepository.countPrescriptionsByMedicalProviderId(medicalProviderId);
  }

  @Override
  public List<PrescriptionResponse> getActivePrescriptionsByMedicalProviderId(String medicalProviderId) {
    List<Prescription> prescriptions = prescriptionRepository.findActivePrescriptionsByMedicalProviderId(medicalProviderId);
    return prescriptions.stream().map(responseMapper::toDto).collect(Collectors.toList());
  }

  @Override
  public List<PrescriptionResponse> getPrescriptionsByMedicalProviderIdAndDateRange(String medicalProviderId, Instant startDate, Instant endDate) {
    List<Prescription> prescriptions = prescriptionRepository.findPrescriptionsByMedicalProviderIdAndDateRange(medicalProviderId, startDate, endDate);
    return prescriptions.stream().map(responseMapper::toDto).collect(Collectors.toList());
  }

  @Override
  public List<PrescriptionResponse> getPrescriptionsByMedicIdAndDateRange(String medicId, Instant startDate, Instant endDate) {
    List<Prescription> prescriptions = prescriptionRepository.findPrescriptionsByMedicIdAndDateRange(medicId, startDate, endDate);
    return prescriptions.stream().map(responseMapper::toDto).collect(Collectors.toList());
  }
}