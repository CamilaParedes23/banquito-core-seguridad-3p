package com.banquito.platform.identity.api.dto.api;

import jakarta.validation.constraints.NotBlank;

public record CreateRoleRequest(
        @NotBlank String code,
        @NotBlank String name,
        @NotBlank String roleType,
        String description
) {}
