package com.recetalia.api.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.Instant;

/**
 * DTO for representing Medic.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MedicResponse {

  @Schema(description = "ID of the medic", example = "medic123")
  private String id;

  @Schema(description = "Name of the medic", example = "John", required = true)
  private String name;

  @Schema(description = "Lastname of the medic", example = "Doe", required = true)
  private String lastname;

  @Schema(description = "Gender of the medic", example = "Male")
  private String gender;

  @Schema(description = "Email address", example = "medic@example.com", required = true)
  private String email;

  @Schema(description = "Phone number", example = "1234567890", required = true)
  private String phone;

  @Schema(description = "Document number", example = "1234567890", required = true)
  private String document;

  @Schema(description = "Birthdate", example = "1980-01-01", required = true)
  private String birthdate;

  @Schema(description = "Country ID of the address", example = "country123")
  private String addressCountryId;

  @Schema(description = "Locality ID of the address", example = "locality123")
  private String addressLocalityId;

  @Schema(description = "Street of the address", example = "123 Main St")
  private String addressStreet;

  @Schema(description = "Address number", example = "456")
  private String addressNumber;

  @Schema(description = "Comments about the address", example = "Near the park")
  private String addressComments;

  @Schema(description = "Creation timestamp", example = "2023-07-15T00:00:00Z")
  private Instant createdAt;

  @Schema(description = "Last update timestamp", example = "2023-07-15T00:00:00Z")
  private Instant updatedAt;

  @Schema(description = "Deletion timestamp", example = "2023-07-15T00:00:00Z")
  private Instant deletedAt;

  @Schema(description = "CJP number", example = "CJP123456", required = true)
  private String cjp;

  @Schema(description = "Status of the medic", example = "ACTIVE", required = true)
  private String status;

  @Schema(description = "Especiality ID", example = "especiality123")
  private String especialityId;

  @Schema(description = "Medical provider ID", example = "provider123")
  private String medicalProviderId;

  @Schema(description = "Medical Provider name", example = "Provider Name")
  private String medicalProviderName;

  @Schema(description = "Medic Especiality name", example = "Especiality Name")
  private String especialityName;
}
