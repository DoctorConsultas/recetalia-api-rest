package com.recetalia.api.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * DTO for MedicalProvider login requests.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MedicalProviderLoginRequest {

    @Schema(description = "Email address", example = "provider@example.com", required = true)
    private String email;

    @Schema(description = "Password", example = "securePassword123", required = true)
    private String password;
}