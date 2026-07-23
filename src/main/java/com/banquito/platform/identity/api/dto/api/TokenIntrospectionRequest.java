package com.banquito.platform.identity.api.dto.api;

import jakarta.validation.constraints.NotBlank;

public record TokenIntrospectionRequest(@NotBlank String token, String requiredScope) {}
