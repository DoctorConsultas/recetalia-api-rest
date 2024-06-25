package com.recetalia.api.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.Instant;

/**
 * DTO for representing Prescription.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionResponse {

  @Schema(description = "ID of the prescription", example = "prescription123")
  private String id;

  @Schema(description = "Expiration timestamp", example = "2023-07-15T00:00:00Z")
  private Instant expireAt;

  @Schema(description = "ID of the medic", example = "medic123", required = true)
  private String medicId;

  @Schema(description = "ID of the patient", example = "patient123", required = true)
  private String patientId;

  @Schema(description = "Prescription code", example = "RX123456", required = true)
  private String code;

  @Schema(description = "Status of the prescription", example = "AVAILABLE", required = true)
  private String status;

  @Schema(description = "Dose amount", example = "2")
  private Integer dose;

  @Schema(description = "Dose unit", example = "mg")
  private String doseUnit;

  @Schema(description = "Frequency of the dose", example = "3")
  private Integer frecuency;

  @Schema(description = "Frequency unit", example = "times per day")
  private String frecuencyUnit;

  @Schema(description = "Medical history details", example = "Patient has a history of hypertension")
  private String medicalHistory;

  @Schema(description = "Ailments or conditions", example = "Hypertension, Diabetes")
  private String affections;

  @Schema(description = "Duration of the treatment", example = "30")
  private Integer duration;

  @Schema(description = "Duration unit", example = "days")
  private String durationUnit;

  @Schema(description = "Product type", example = "MEDICINE", required = true)
  private String productType;

  @Schema(description = "ID of the product", example = "product123", required = true)
  private String productId;

  @Schema(description = "Type of dose", example = "tablet")
  private String doseType;

  @Schema(description = "Creation timestamp", example = "2023-07-15T00:00:00Z")
  private Instant createdAt;

  @Schema(description = "Last update timestamp", example = "2023-07-15T00:00:00Z")
  private Instant updatedAt;

  @Schema(description = "Deletion timestamp", example = "2023-07-15T00:00:00Z")
  private Instant deletedAt;
}
