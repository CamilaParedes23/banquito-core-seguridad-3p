package com.banquito.platform.identity.api.dto.api;

import jakarta.validation.constraints.NotBlank;

public record CreatePermissionRequest(
        @NotBlank String code,
        @NotBlank String name,
        @NotBlank String module,
        @NotBlank String action,
        @NotBlank String resource
) {}
