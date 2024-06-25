package com.recetalia.api.application.dto.mapper.request;

import com.recetalia.api.application.domain.model.entities.Prescription;
import com.recetalia.api.application.dto.request.PrescriptionRequest;
import com.recetalia.api.application.infrastructure.config.MapStructConfig;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Mapper to convert PrescriptionRequest DTO to Prescription entity.
 */
@Mapper(componentModel = "spring", config = MapStructConfig.class)
public interface PrescriptionRequestMapper {

  /**
   * Converts a PrescriptionRequest DTO to a Prescription entity.
   *
   * @param dto the PrescriptionRequest DTO
   * @return the Prescription entity
   */
  Prescription toEntity(PrescriptionRequest dto);

  /**
   * Updates a Prescription entity from a PrescriptionRequest DTO.
   *
   * @param dto the PrescriptionRequest DTO
   * @param entity the existing Prescription entity
   */
  void updateEntityFromDto(PrescriptionRequest dto, @MappingTarget Prescription entity);
}