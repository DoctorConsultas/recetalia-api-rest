package com.recetalia.api.application.service;

import com.recetalia.api.application.dto.response.MedicalProviderResponse;
import com.recetalia.api.application.infrastructure.exception.ResourceNotFoundException;

/**
 * Service interface for retrieving the currently authenticated MedicalProvider.
 */
public interface CurrentUserAuthenticatedService {

    /**
     * Gets the current MedicalProvider from the authentication token.
     *
     * @return the MedicalProviderResponse of the currently authenticated user
     * @throws ResourceNotFoundException if the user is not found
     */
    MedicalProviderResponse getCurrentMedicalProvider() throws ResourceNotFoundException;
}
