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
import java.util.Map;
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

  @Autowired
  private DnmaDatabaseService dnmaDatabaseService;

  @Override
  public PrescriptionResponse createPrescription(PrescriptionRequest request) {
    Prescription prescription = requestMapper.toEntity(request);
    prescription = prescriptionRepository.save(prescription);
    return mapPrescriptionWithAmpDetails(prescription);
  }

  @Override
  public PrescriptionResponse updatePrescription(String id, PrescriptionRequest request) throws ResourceNotFoundException {
    Prescription prescription = prescriptionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Prescription not found for this id :: " + id));
    requestMapper.updateEntityFromDto(request, prescription);
    prescription = prescriptionRepository.save(prescription);
    return mapPrescriptionWithAmpDetails(prescription);
  }

  @Override
  public PrescriptionResponse getPrescriptionById(String id) throws ResourceNotFoundException {
    Prescription prescription = prescriptionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Prescription not found for this id :: " + id));
    return mapPrescriptionWithAmpDetails(prescription);
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
            .map(this::mapPrescriptionWithAmpDetails)
            .collect(Collectors.toList());
  }

  @Override
  public Page<PrescriptionResponse> getPrescriptionsByMedicalProviderId(String medicalProviderId, Pageable pageable) {
    return prescriptionRepository.findPrescriptionsByMedicalProviderId(medicalProviderId, pageable)
            .map(this::mapPrescriptionWithAmpDetails);
  }

  @Override
  public Page<PrescriptionResponse> getPrescriptionsByMedicIdAndMedicalProviderId(String medicId, String medicalProviderId, Pageable pageable) {
    return prescriptionRepository.findPrescriptionsByMedicIdAndMedicalProviderId(medicId, medicalProviderId, pageable)
            .map(this::mapPrescriptionWithAmpDetails);
  }

  @Override
  public Page<PrescriptionResponse> getPrescriptionsByPatientIddAndMedicalProviderId(String patientId, String medicalProviderId, Pageable pageable) {
    return prescriptionRepository.findPrescriptionsByPatientIdAndMedicalProviderId(patientId, medicalProviderId, pageable)
            .map(this::mapPrescriptionWithAmpDetails);
  }

  @Override
  public long getPrescriptionCountByMedicalProviderId(String medicalProviderId) {
    return prescriptionRepository.countPrescriptionsByMedicalProviderId(medicalProviderId);
  }

  @Override
  public List<PrescriptionResponse> getActivePrescriptionsByMedicalProviderId(String medicalProviderId) {
    return prescriptionRepository.findActivePrescriptionsByMedicalProviderId(medicalProviderId).stream()
            .map(this::mapPrescriptionWithAmpDetails)
            .collect(Collectors.toList());
  }

  @Override
  public Page<PrescriptionResponse> getPrescriptionsByMedicalProviderIdAndDateRange(String medicalProviderId, Instant startDate, Instant endDate, Pageable pageable) {
    return prescriptionRepository.findPrescriptionsByMedicalProviderIdAndDateRange(medicalProviderId, startDate, endDate, pageable)
            .map(this::mapPrescriptionWithAmpDetails);
  }

  @Override
  public List<PrescriptionResponse> getPrescriptionsByMedicIdAndDateRange(String medicId, Instant startDate, Instant endDate) {
    return prescriptionRepository.findPrescriptionsByMedicIdAndDateRange(medicId, startDate, endDate).stream()
            .map(this::mapPrescriptionWithAmpDetails)
            .collect(Collectors.toList());
  }

  private PrescriptionResponse mapPrescriptionWithAmpDetails(Prescription prescription) {
    PrescriptionResponse response = responseMapper.toDto(prescription);
    Map<String, String> ampDetails = dnmaDatabaseService.fetchAmpDetails(prescription.getProductId());
    response.setAmpDsc(ampDetails.get("amp_dsc"));
    response.setProdMsp(ampDetails.get("prod_msp"));
    response.setNombreLaboratory(ampDetails.get("nombre_laboratory"));
    response.setRutLaboratory(ampDetails.get("rut_laboratory"));
    return response;
  }
}
