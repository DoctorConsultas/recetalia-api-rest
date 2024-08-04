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
    List<Prescription> prescriptions = prescriptionRepository.findAll();
    return enrichPrescriptionsWithAmpDetails(prescriptions);
  }

  @Override
  public Page<PrescriptionResponse> getPrescriptionsByMedicalProviderId(String medicalProviderId, List<String> statuses, Pageable pageable) {
    Page<Prescription> prescriptions = prescriptionRepository.findPrescriptionsByMedicalProviderIdAndStatuses(medicalProviderId, statuses, pageable);
    return prescriptions.map(this::mapPrescriptionWithAmpDetails);
  }

  @Override
  public Page<PrescriptionResponse> getPrescriptionsByMedicIdAndMedicalProviderId(String medicId, String medicalProviderId, List<String> statuses, Pageable pageable) {
    Page<Prescription> prescriptions = prescriptionRepository.findPrescriptionsByMedicIdAndMedicalProviderId(medicId, medicalProviderId, statuses, pageable);
    return prescriptions.map(this::mapPrescriptionWithAmpDetails);
  }

  @Override
  public Page<PrescriptionResponse> getPrescriptionsByPatientIddAndMedicalProviderId(String patientId, String medicalProviderId, List<String> statuses, Pageable pageable) {
    Page<Prescription> prescriptions = prescriptionRepository.findPrescriptionsByPatientIdAndMedicalProviderId(patientId, medicalProviderId, statuses, pageable);
    return prescriptions.map(this::mapPrescriptionWithAmpDetails);
  }

  @Override
  public long getPrescriptionCountByMedicalProviderId(String medicalProviderId, List<String> statuses) {
    return prescriptionRepository.countPrescriptionsByMedicalProviderId(medicalProviderId, statuses);
  }

  @Override
  public List<PrescriptionResponse> getActivePrescriptionsByMedicalProviderId(String medicalProviderId) {
    List<Prescription> prescriptions = prescriptionRepository.findActivePrescriptionsByMedicalProviderId(medicalProviderId);
    return enrichPrescriptionsWithAmpDetails(prescriptions);
  }

  @Override
  public Page<PrescriptionResponse> getPrescriptionsByMedicalProviderIdAndDateRange(String medicalProviderId, Instant startDate, Instant endDate, List<String> statuses, Pageable pageable) {
    Page<Prescription> prescriptions = prescriptionRepository.findPrescriptionsByMedicalProviderIdAndDateRange(medicalProviderId, startDate, endDate, statuses, pageable);
    return prescriptions.map(this::mapPrescriptionWithAmpDetails);
  }

  @Override
  public List<PrescriptionResponse> getPrescriptionsByMedicIdAndDateRange(String medicId, Instant startDate, Instant endDate, List<String> statuses) {
    List<Prescription> prescriptions = prescriptionRepository.findPrescriptionsByMedicIdAndDateRange(medicId, startDate, endDate, statuses);
    return enrichPrescriptionsWithAmpDetails(prescriptions);
  }

  private PrescriptionResponse mapPrescriptionWithAmpDetails(Prescription prescription) {
    PrescriptionResponse response = responseMapper.toDto(prescription);
    Map<String, Map<String, String>> ampDetailsMap = dnmaDatabaseService.fetchAmpDetails(List.of(prescription.getProductId()));
    Map<String, String> ampDetails = ampDetailsMap.get(prescription.getProductId());
    response.setAmpDsc(ampDetails.get("amp_dsc"));
    response.setProdMsp(ampDetails.get("prod_msp"));
    response.setNombreLaboratory(ampDetails.get("nombre_laboratory"));
    response.setRutLaboratory(ampDetails.get("rut_laboratory"));
    return response;
  }

  private List<PrescriptionResponse> enrichPrescriptionsWithAmpDetails(List<Prescription> prescriptions) {
    List<String> ampIds = prescriptions.stream()
            .map(Prescription::getProductId)
            .collect(Collectors.toList());
    Map<String, Map<String, String>> ampDetailsMap = dnmaDatabaseService.fetchAmpDetails(ampIds);
    return prescriptions.stream()
            .map(prescription -> {
              PrescriptionResponse response = responseMapper.toDto(prescription);
              Map<String, String> ampDetails = ampDetailsMap.get(prescription.getProductId());
              if (ampDetails != null) {
                response.setAmpDsc(ampDetails.get("amp_dsc"));
                response.setProdMsp(ampDetails.get("prod_msp"));
                response.setNombreLaboratory(ampDetails.get("nombre_laboratory"));
                response.setRutLaboratory(ampDetails.get("rut_laboratory"));
              }
              return response;
            })
            .collect(Collectors.toList());
  }
}
