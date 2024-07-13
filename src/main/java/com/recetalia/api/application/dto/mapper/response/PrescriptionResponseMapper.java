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

  /**
   * Converts a Prescription entity to a PrescriptionResponse DTO.
   *
   * @param entity the Prescription entity
   * @return the PrescriptionResponse DTO
   */
  @Mapping(source = "medic.id", target = "medicId")
  @Mapping(source = "patient.id", target = "patientId")
  public abstract PrescriptionResponse toDto(Prescription entity);

  /**
   * Adds the link to the individual prescription resource.
   *
   * @param entity the Prescription entity
   * @return the PrescriptionResponse DTO with the link
   */
  @AfterMapping
  protected void addLink(Prescription entity, @MappingTarget PrescriptionResponse response) {
    response.setLink(contextPath + "/" + entity.getId());
  }
}