package com.recetalia.api.application.dto.mapper.response;

import com.recetalia.api.application.domain.model.entities.Patient;
import com.recetalia.api.application.dto.response.PatientResponse;
import com.recetalia.api.application.infrastructure.config.MapStructConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
  @Mapping(source = "addressCountry.id", target = "addressCountryId")
  @Mapping(source = "addressLocality.id", target = "addressLocalityId")
  @Mapping(source = "avatar.id", target = "avatarId")
  PatientResponse toDto(Patient entity);
}