package com.banquito.platform.identity.api.dto.api;

import java.util.List;

public record TokenResponse(
        String accessToken,
        String refreshToken,
        String tokenType,
        long expiresInSeconds,
        String sessionUuid,
        String actorUuid,
        String actorType,
        List<String> roles,
        List<String> scopes,
        String referenceUuid,
        String referenceType,
        String customerUuid
) {}
