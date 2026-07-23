package com.banquito.platform.identity.api.dto.api;

import jakarta.validation.constraints.NotBlank;

public record AssignRoleRequest(@NotBlank String roleCode, String assignedByUuid) {}
