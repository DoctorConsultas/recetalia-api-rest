package com.recetalia.api.application.dto.mapper.response;

import com.recetalia.api.application.domain.model.entities.Prescription;
import com.recetalia.api.application.dto.response.PrescriptionResponse;
import com.recetalia.api.application.infrastructure.config.MapStructConfig;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Value;

/**
 * Mapper to convert Prescription entity to PrescriptionResponse DTO.
 */
@Mapper(componentModel = "spring", config = MapStructConfig.class)
public abstract class PrescriptionResponseMapper {

  @Value("${server.servlet.context-path:/api/prescriptions}")
  private String contextPath;

  @Value("${server.servlet.context-path:/api/medics}")
  private String medicContextPath;

  @Value("${server.servlet.context-path:/api/patients}")
  private String patientContextPath;

  /**
   * Converts a Prescription entity to a PrescriptionResponse DTO.
   *
   * @param entity the Prescription entity
   * @return the PrescriptionResponse DTO
   */
  @Mapping(source = "medic.id", target = "medicId")
  @Mapping(source = "medic.cjp", target = "medicCjp")
  @Mapping(source = "medic.name", target = "medicName")
  @Mapping(source = "medic.lastname", target = "medicLastname")
  @Mapping(source = "patient.id", target = "patientId")
  @Mapping(source = "patient.document", target = "patientDocument")
  @Mapping(source = "patient.name", target = "patientName")
  @Mapping(source = "patient.lastname", target = "patientLastname")
  public abstract PrescriptionResponse toDto(Prescription entity);

  /**
   * Adds the link to the individual prescription resource, and links to the respective doctor and patient.
   *
   * @param entity the Prescription entity
   * @param response the PrescriptionResponse DTO
   */
  @AfterMapping
  protected void addLink(Prescription entity, @MappingTarget PrescriptionResponse response) {
    response.setLink(contextPath + "/" + entity.getId());
    response.setMedicLink(medicContextPath + "/" + entity.getMedic().getId());
    response.setPatientLink(patientContextPath + "/" + entity.getPatient().getId());
  }
}
