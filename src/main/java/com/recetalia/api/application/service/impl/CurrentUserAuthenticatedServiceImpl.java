package com.recetalia.api.application.service.impl;


import com.recetalia.api.application.domain.model.entities.MedicalProvider;
import com.recetalia.api.application.dto.mapper.response.MedicalProviderResponseMapper;
import com.recetalia.api.application.dto.response.MedicalProviderResponse;
import com.recetalia.api.application.infrastructure.exception.ResourceNotFoundException;
import com.recetalia.api.application.service.CurrentUserAuthenticatedService;
import com.recetalia.api.application.domain.repository.MedicalProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of the CurrentMedicalProviderService interface.
 */
@Service
public class CurrentUserAuthenticatedServiceImpl implements CurrentUserAuthenticatedService {

    @Autowired
    private MedicalProviderRepository medicalProviderRepository;

    @Autowired
    private MedicalProviderResponseMapper responseMapper;

    @Override
    public MedicalProviderResponse getCurrentMedicalProvider() throws ResourceNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof JwtAuthenticationToken) {
            JwtAuthenticationToken jwtToken = (JwtAuthenticationToken) authentication;
            String email = jwtToken.getToken().getClaim("email"); // Assuming "email" is a claim in the token
            return findByEmail(email).map(responseMapper::toDto)
                    .orElseThrow(() -> new ResourceNotFoundException("MedicalProvider not found for this email :: " + email));
        }
        throw new ResourceNotFoundException("No authentication token found");
    }

    /**
     * Finds a MedicalProvider by email.
     *
     * @param email the email of the MedicalProvider
     * @return an optional containing the found MedicalProvider or empty if not found
     */
    private Optional<MedicalProvider> findByEmail(String email) {
        return medicalProviderRepository.findByEmail(email);
    }
}