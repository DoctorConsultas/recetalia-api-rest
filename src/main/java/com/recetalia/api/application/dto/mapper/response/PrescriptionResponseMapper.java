package com.recetalia.api.application.dto.mapper.response;

import com.recetalia.api.application.domain.model.entities.Prescription;
import com.recetalia.api.application.dto.response.PrescriptionResponse;
import com.recetalia.api.application.infrastructure.config.MapStructConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper to convert Prescription entity to PrescriptionResponse DTO.
 */
@Mapper(componentModel = "spring", config = MapStructConfig.class)
public interface PrescriptionResponseMapper {

  /**
   * Converts a Prescription entity to a PrescriptionResponse DTO.
   *
   * @param entity the Prescription entity
   * @return the PrescriptionResponse DTO
   */
  @Mapping(source = "medic.id", target = "medicId")
  @Mapping(source = "patient.id", target = "patientId")
  PrescriptionResponse toDto(Prescription entity);
}
