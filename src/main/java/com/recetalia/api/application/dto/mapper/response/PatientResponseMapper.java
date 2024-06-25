package com.recetalia.api.application.dto.mapper.response;

import com.recetalia.api.application.domain.model.entities.Patient;
import com.recetalia.api.application.dto.response.PatientResponse;
import com.recetalia.api.application.infrastructure.config.MapStructConfig;
import org.mapstruct.Mapper;

/**
 * Mapper to convert Patient entity to PatientResponse DTO.
 */
@Mapper(componentModel = "spring", config = MapStructConfig.class)
public interface PatientResponseMapper {

  /**
   * Converts a Patient entity to a PatientResponse DTO.
   *
   * @param entity the Patient entity
   * @return the PatientResponse DTO
   */
  PatientResponse toDto(Patient entity);
}