package com.recetalia.api.application.dto.mapper.response;

import com.recetalia.api.application.domain.model.entities.Medic;
import com.recetalia.api.application.dto.response.MedicResponse;
import com.recetalia.api.application.infrastructure.config.MapStructConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper to convert Medic entity to MedicResponse DTO.
 */
@Mapper(componentModel = "spring", config = MapStructConfig.class)
public interface MedicResponseMapper {

  /**
   * Converts a Medic entity to a MedicResponse DTO.
   *
   * @param entity the Medic entity
   * @return the MedicResponse DTO
   */
  @Mapping(source = "addressCountry.id", target = "addressCountryId")
  @Mapping(source = "addressLocality.id", target = "addressLocalityId")
  @Mapping(source = "especiality.id", target = "especialityId")
  @Mapping(source = "especiality.name", target = "especialityName")
  @Mapping(source = "medicalProvider.name", target = "medicalProviderName")
  MedicResponse toDto(Medic entity);
}
