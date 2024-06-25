package com.recetalia.api.application.dto.mapper.request;

import com.recetalia.api.application.domain.model.entities.MedicalProvider;
import com.recetalia.api.application.dto.request.MedicalProviderRequest;
import com.recetalia.api.application.infrastructure.config.MapStructConfig;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Mapper to convert MedicalProviderRequest DTO to MedicalProvider entity.
 */
@Mapper(componentModel = "spring", config = MapStructConfig.class)
public interface MedicalProviderRequestMapper {

  /**
   * Converts a MedicalProviderRequest DTO to a MedicalProvider entity.
   *
   * @param dto the MedicalProviderRequest DTO
   * @return the MedicalProvider entity
   */
  MedicalProvider toEntity(MedicalProviderRequest dto);

  /**
   * Updates a MedicalProvider entity from a MedicalProviderRequest DTO.
   *
   * @param dto the MedicalProviderRequest DTO
   * @param entity the existing MedicalProvider entity
   */
  void updateEntityFromDto(MedicalProviderRequest dto, @MappingTarget MedicalProvider entity);
}