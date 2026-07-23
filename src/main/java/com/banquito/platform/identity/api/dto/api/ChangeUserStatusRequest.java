package com.banquito.platform.identity.api.dto.api;

import jakarta.validation.constraints.NotBlank;

public record ChangeUserStatusRequest(@NotBlank String status, String reason) {}
