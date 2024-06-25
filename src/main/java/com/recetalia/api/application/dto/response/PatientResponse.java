package com.recetalia.api.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.Instant;

/**
 * DTO for representing Patient.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponse {

  @Schema(description = "ID of the patient", example = "patient123")
  private String id;

  @Schema(description = "Name of the patient", example = "Jane", required = true)
  private String name;

  @Schema(description = "Lastname of the patient", example = "Smith", required = true)
  private String lastname;

  @Schema(description = "Email address", example = "patient@example.com")
  private String email;

  @Schema(description = "Phone number", example = "1234567890", required = true)
  private String phone;

  @Schema(description = "Document number", example = "1234567890", required = true)
  private String document;

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

  @Schema(description = "Username", example = "user123", required = true)
  private String user;

  @Schema(description = "Password", example = "securePassword123", required = true)
  private String password;

  @Schema(description = "Birthdate", example = "1990-01-01", required = true)
  private String birthdate;

  @Schema(description = "Creation timestamp", example = "2023-07-15T00:00:00Z")
  private Instant createdAt;

  @Schema(description = "Last update timestamp", example = "2023-07-15T00:00:00Z")
  private Instant updatedAt;

  @Schema(description = "Deletion timestamp", example = "2023-07-15T00:00:00Z")
  private Instant deletedAt;

  @Schema(description = "Sex", example = "Female")
  private String sex;

  @Schema(description = "Avatar ID", example = "avatar123")
  private String avatarId;
}
