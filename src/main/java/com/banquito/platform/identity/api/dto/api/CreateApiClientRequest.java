package com.banquito.platform.identity.api.dto.api;

import jakarta.validation.constraints.NotBlank;

public record CreateApiClientRequest(
        @NotBlank String clientId,
        @NotBlank String clientSecret,
        @NotBlank String name,
        @NotBlank String originService,
        @NotBlank String clientType
) {}
