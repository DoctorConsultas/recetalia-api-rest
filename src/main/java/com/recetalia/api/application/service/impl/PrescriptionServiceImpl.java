package com.recetalia.api.application.service.impl;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recetalia.api.application.domain.model.entities.Prescription;
import com.recetalia.api.application.dto.mapper.request.PrescriptionRequestMapper;
import com.recetalia.api.application.dto.mapper.response.PrescriptionResponseMapper;
import com.recetalia.api.application.dto.request.PrescriptionRequest;
import com.recetalia.api.application.dto.response.MedicalProviderResponse;
import com.recetalia.api.application.dto.response.PrescriptionResponse;
import com.recetalia.api.application.service.PrescriptionService;
import com.recetalia.api.application.infrastructure.exception.ResourceNotFoundException;
import com.recetalia.api.application.domain.repository.PrescriptionRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

  @Autowired
  private CurrentUserAuthenticatedServiceImpl currentUserAuthenticatedService;

  @Override
  public Page<PrescriptionResponse> getPrescriptionsByFilters(String medicId, String patientId, List<String> statuses, Instant startDate, Instant endDate, Pageable pageable) {
    MedicalProviderResponse medicalProvider = this.currentUserAuthenticatedService.getCurrentMedicalProvider();
    String medicalProviderId = medicalProvider.getId();
    Page<Prescription> prescriptions = prescriptionRepository.findPrescriptionsByFilters(medicalProviderId, medicId, patientId, statuses, startDate, endDate, pageable);
    return prescriptions.map(this::mapPrescriptionWithAmpDetails);
  }

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
    MedicalProviderResponse medicalProvider = this.currentUserAuthenticatedService.getCurrentMedicalProvider();
    medicalProviderId = medicalProvider.getId();
    Page<Prescription> prescriptions = prescriptionRepository.findPrescriptionsByMedicalProviderIdAndStatuses(medicalProviderId, statuses, pageable);
    return prescriptions.map(this::mapPrescriptionWithAmpDetails);
  }

  @Override
  public Page<PrescriptionResponse> getPrescriptionsByMedicIdAndMedicalProviderId(String medicId, String medicalProviderId, List<String> statuses, Pageable pageable) {
    MedicalProviderResponse medicalProvider = this.currentUserAuthenticatedService.getCurrentMedicalProvider();
    medicalProviderId = medicalProvider.getId();
    Page<Prescription> prescriptions = prescriptionRepository.findPrescriptionsByMedicIdAndMedicalProviderId(medicId, medicalProviderId, statuses, pageable);
    return prescriptions.map(this::mapPrescriptionWithAmpDetails);
  }

  @Override
  public Page<PrescriptionResponse> getPrescriptionsByPatientIddAndMedicalProviderId(String patientId, String medicalProviderId, List<String> statuses, Pageable pageable) {
    MedicalProviderResponse medicalProvider = this.currentUserAuthenticatedService.getCurrentMedicalProvider();
    medicalProviderId = medicalProvider.getId();
    Page<Prescription> prescriptions = prescriptionRepository.findPrescriptionsByPatientIdAndMedicalProviderId(patientId, medicalProviderId, statuses, pageable);
    return prescriptions.map(this::mapPrescriptionWithAmpDetails);
  }

  @Override
  public long getPrescriptionCountByMedicalProviderId(String medicalProviderId, List<String> statuses) {
    MedicalProviderResponse medicalProvider = this.currentUserAuthenticatedService.getCurrentMedicalProvider();
    medicalProviderId = medicalProvider.getId();
    return prescriptionRepository.countPrescriptionsByMedicalProviderId(medicalProviderId, statuses);
  }

  @Override
  public List<PrescriptionResponse> getActivePrescriptionsByMedicalProviderId(String medicalProviderId) {
    MedicalProviderResponse medicalProvider = this.currentUserAuthenticatedService.getCurrentMedicalProvider();
    medicalProviderId = medicalProvider.getId();
    List<Prescription> prescriptions = prescriptionRepository.findActivePrescriptionsByMedicalProviderId(medicalProviderId);
    return enrichPrescriptionsWithAmpDetails(prescriptions);
  }

  @Override
  public Page<PrescriptionResponse> getPrescriptionsByMedicalProviderIdAndDateRange(String medicalProviderId, Instant startDate, Instant endDate, List<String> statuses, Pageable pageable) {
    MedicalProviderResponse medicalProvider = this.currentUserAuthenticatedService.getCurrentMedicalProvider();
    medicalProviderId = medicalProvider.getId();
    Page<Prescription> prescriptions = prescriptionRepository.findPrescriptionsByMedicalProviderIdAndDateRange(medicalProviderId, startDate, endDate, statuses, pageable);
    return prescriptions.map(this::mapPrescriptionWithAmpDetails);
  }

  @Override
  public List<PrescriptionResponse> getPrescriptionsByMedicIdAndDateRange(String medicId, Instant startDate, Instant endDate, List<String> statuses) {
    List<Prescription> prescriptions = prescriptionRepository.findPrescriptionsByMedicIdAndDateRange(medicId, startDate, endDate, statuses);
    return enrichPrescriptionsWithAmpDetails(prescriptions);
  }

  private PrescriptionResponse mapPrescriptionWithAmpDetails(Prescription prescription) {
    PrescriptionResponse response = null;
    try {
      response = responseMapper.toDto(prescription);
      Map<String, Map<String, String>> ampDetailsMap = dnmaDatabaseService.fetchAmpDetails(List.of(prescription.getProductId()));
      Map<String, String> ampDetails = ampDetailsMap.get(prescription.getProductId());
      response.setAmpDsc(ampDetails.get("amp_dsc"));
      response.setProdMsp(ampDetails.get("prod_msp"));
      response.setNombreLaboratory(ampDetails.get("nombre_laboratory"));
      response.setRutLaboratory(ampDetails.get("rut_laboratory"));
    } catch (Exception e) {
      System.err.println("mapPrescriptionWithAmpDetails: " + e.getMessage() +  "; prescription: " + prescription.toString());
    }
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

  @Override
  public Workbook exportToExcel(List<PrescriptionResponse> prescriptions) {
    Workbook workbook = new XSSFWorkbook();
    Sheet sheet = workbook.createSheet("Prescriptions");
    ObjectMapper objectMapper = new ObjectMapper(); // Jackson ObjectMapper for parsing JSON

    // Create header row
    Row headerRow = sheet.createRow(0);
    String[] headers = {"CÓDIGO", "CREADO", "PACIENTE", "CI", "MÉDICO", "CJP", "MEDICAMENTO", "LABORATORIO", "STATUS", "FARMACIA", "FECHA DE ENTREGA"};
    for (int i = 0; i < headers.length; i++) {
      Cell cell = headerRow.createCell(i);
      cell.setCellValue(headers[i]);
    }
    // Fill data
    Row row;
    String ciNumber = "";
    int rowNum = 1;
    for (PrescriptionResponse prescription : prescriptions) {
      try{
        row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue(prescription.getCode());
        row.createCell(1).setCellValue(prescription.getCreatedAt().toString());
        row.createCell(2).setCellValue(prescription.getPatientName() + " " + prescription.getPatientLastname());
        // Parse the patientDocument JSON string to extract the "number" field
        ciNumber = "";
        if (prescription.getPatientDocument() != null) {
          JsonNode documentNode = objectMapper.readTree(prescription.getPatientDocument());
          ciNumber = documentNode.has("number") ? documentNode.get("number").asText() : "";
        }
        row.createCell(3).setCellValue(ciNumber);
        row.createCell(4).setCellValue(prescription.getMedicName() + " " + prescription.getMedicLastname());
        row.createCell(5).setCellValue(prescription.getMedicCjp());
        row.createCell(6).setCellValue(prescription.getAmpDsc());
        row.createCell(7).setCellValue(prescription.getNombreLaboratory());
        row.createCell(8).setCellValue(getStatusLabel(prescription.getStatus()));
        row.createCell(9).setCellValue(prescription.getPharmacyName() != null ? prescription.getPharmacyName() : "");
        if(prescription.getStatus().contains("AVAILABLE")) {
          row.createCell(10).setCellValue("");
        } else {
          row.createCell(10).setCellValue(prescription.getUpdatedAt() != null ? prescription.getUpdatedAt().toString() : "");
        }
      } catch (IOException e) {
        System.err.println("prescription.getId: " + prescription.toString() + "; Error: " + e.getMessage());
      }
    }

    return workbook;
  }

  private String getStatusLabel(String status) {
    switch (status) {
      case "AVAILABLE":
        return "Disponible";
      case "DISPENSED":
        return "Dispensada";
      default:
        return status;
    }
  }
}
