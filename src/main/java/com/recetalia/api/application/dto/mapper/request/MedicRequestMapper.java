package com.recetalia.api.application.dto.mapper.request;

import com.recetalia.api.application.domain.model.entities.Medic;
import com.recetalia.api.application.dto.request.MedicRequest;
import com.recetalia.api.application.infrastructure.config.MapStructConfig;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Mapper to convert MedicRequest DTO to Medic entity.
 */
@Mapper(componentModel = "spring", config = MapStructConfig.class)
public interface MedicRequestMapper {

  /**
   * Converts a MedicRequest DTO to a Medic entity.
   *
   * @param dto the MedicRequest DTO
   * @return the Medic entity
   */
  Medic toEntity(MedicRequest dto);

  /**
   * Updates a Medic entity from a MedicRequest DTO.
   *
   * @param dto the MedicRequest DTO
   * @param entity the existing Medic entity
   */
  void updateEntityFromDto(MedicRequest dto, @MappingTarget Medic entity);
}