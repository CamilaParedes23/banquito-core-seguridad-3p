package com.banquito.platform.identity.api.dto.api;

import java.time.LocalDateTime;
import java.util.List;

public record UserDetailResponse(
        String userUuid,
        String username,
        String email,
        String actorType,
        String status,
        String externalReferenceUuid,
        String referenceType,
        LocalDateTime lastLogin,
        List<String> roles,
        List<String> permissions
) {}
