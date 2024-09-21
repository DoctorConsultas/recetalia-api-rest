package com.recetalia.api.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * DTO for creating and updating Medic.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MedicRequest {

  @NotNull
  @Size(min = 2, max = 150)
  @Schema(description = "Name of the medic", example = "John", required = true)
  private String name;

  @NotNull
  @Size(min = 2, max = 150)
  @Schema(description = "Lastname of the medic", example = "Doe", required = true)
  private String lastname;

  @Schema(description = "Gender of the medic", example = "Male")
  private String gender;

  @NotNull
  @Email
  @Schema(description = "Email address", example = "medic@example.com", required = true)
  private String email;

  @NotNull
  @Size(min = 8)
  @Schema(description = "Password", example = "securePassword123", required = true)
  private String password;

  @NotNull
  // @Size(min = 7, max = 15)
  @Schema(description = "Phone number", example = "1234567890", required = true)
  private String phone;

  @NotNull
  @Schema(description = "Document number", example = "1234567890", required = true)
  private String document;

  @NotNull
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

  @NotNull
  @Schema(description = "CJP number", example = "CJP123456", required = true)
  private String cjp;

  @Schema(description = "Password forgot code", example = "code123")
  private String passwordForgotCode;

  @NotNull
  @Schema(description = "Status of the medic", example = "ACTIVE", required = true)
  private String status;

  @Schema(description = "Especiality ID", example = "especiality123")
  private String especialityId;

  @Schema(description = "Medical provider ID", example = "provider123")
  private String medicalProviderId;
}
