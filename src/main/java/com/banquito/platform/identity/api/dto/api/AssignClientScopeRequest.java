package com.banquito.platform.identity.api.dto.api;

import jakarta.validation.constraints.NotBlank;

public record AssignClientScopeRequest(@NotBlank String scope, String description) {}
