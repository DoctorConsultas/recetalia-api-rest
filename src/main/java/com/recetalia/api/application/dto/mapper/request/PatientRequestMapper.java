package com.recetalia.api.application.dto.mapper.request;

import com.recetalia.api.application.domain.model.entities.Patient;
import com.recetalia.api.application.dto.request.PatientRequest;
import com.recetalia.api.application.infrastructure.config.MapStructConfig;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Mapper to convert PatientRequest DTO to Patient entity.
 */
@Mapper(componentModel = "spring", config = MapStructConfig.class)
public interface PatientRequestMapper {

  /**
   * Converts a PatientRequest DTO to a Patient entity.
   *
   * @param dto the PatientRequest DTO
   * @return the Patient entity
   */
  Patient toEntity(PatientRequest dto);

  /**
   * Updates a Patient entity from a PatientRequest DTO.
   *
   * @param dto the PatientRequest DTO
   * @param entity the existing Patient entity
   */
  void updateEntityFromDto(PatientRequest dto, @MappingTarget Patient entity);
}