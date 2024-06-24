package com.recetalia.api.application.dto.mapper.response;

import com.recetalia.api.application.domain.model.entities.MedicalProvider;
import com.recetalia.api.application.dto.response.MedicalProviderResponse;
import com.recetalia.api.application.infrastructure.config.MapStructConfig;
import org.mapstruct.Mapper;

/**
 * Mapper to convert MedicalProvider entity to MedicalProviderResponse DTO.
 */
@Mapper(componentModel = "spring", config = MapStructConfig.class)
public interface MedicalProviderResponseMapper {

  /**
   * Converts a MedicalProvider entity to a MedicalProviderResponse DTO.
   *
   * @param entity the MedicalProvider entity
   * @return the MedicalProviderResponse DTO
   */
  MedicalProviderResponse toDto(MedicalProvider entity);
}