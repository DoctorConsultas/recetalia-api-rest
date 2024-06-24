package com.recetalia.api.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.Instant;

/**
 * DTO for creating and updating MedicalProvider.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MedicalProviderRequest {

  @Schema(description = "Type ID of the medical provider", example = "type123", required = true)
  private String medicalProviderTypeId;

  @Schema(description = "Name of the medical provider", example = "HealthCare Inc.", required = true)
  private String name;

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

  @Schema(description = "Phone number", example = "1234567890", required = true)
  private String phone;

  @Schema(description = "Email address", example = "provider@example.com", required = true)
  private String email;

  @Schema(description = "Password", example = "securePassword123", required = true)
  private String password;

  @Schema(description = "Business name", example = "HealthCare Business", required = true)
  private String businessName;

  @Schema(description = "RUT number", example = "1234567890", required = true)
  private String rut;

  @Schema(description = "ID of the logo", example = "logo123")
  private String logoId;

  @Schema(description = "Password forgot code", example = "code123")
  private String passwordForgotCode;

  @Schema(description = "Status of the provider", example = "ACTIVE", required = true)
  private String status;
}
