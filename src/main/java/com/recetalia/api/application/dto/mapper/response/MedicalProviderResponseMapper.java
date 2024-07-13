package com.recetalia.api.application.dto.mapper.response;

import com.recetalia.api.application.domain.model.entities.MedicalProvider;
import com.recetalia.api.application.dto.response.MedicalProviderResponse;
import com.recetalia.api.application.infrastructure.config.MapStructConfig;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Value;

/**
 * Mapper to convert MedicalProvider entity to MedicalProviderResponse DTO.
 */
@Mapper(componentModel = "spring", config = MapStructConfig.class)
public abstract class MedicalProviderResponseMapper {

  @Value("${server.servlet.context-path:/api/prescriptions}")
  private String contextPath;


  /**
   * Converts a MedicalProvider entity to a MedicalProviderResponse DTO.
   *
   * @param entity the MedicalProvider entity
   * @return the MedicalProviderResponse DTO
   */
  public abstract MedicalProviderResponse toDto(MedicalProvider entity);

  /**
   * Adds the link to the individual medical provider resource.
   *
   * @param entity the MedicalProvider entity
   * @param dto the MedicalProviderRequest DTO
   */
  @AfterMapping
  protected void addLink(MedicalProvider entity, @MappingTarget MedicalProviderResponse dto) {
    dto.setLink(contextPath + "/" + entity.getId());
  }
}