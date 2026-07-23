package com.banquito.platform.identity.api.dto.api;

import jakarta.validation.constraints.NotBlank;

public record ClientTokenRequest(
        @NotBlank String clientId,
        @NotBlank String clientSecret,
        String requiredScope
) {}
