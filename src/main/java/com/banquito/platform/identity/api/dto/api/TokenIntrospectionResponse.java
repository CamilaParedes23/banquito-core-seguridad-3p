package com.banquito.platform.identity.api.dto.api;

import java.util.List;

public record TokenIntrospectionResponse(
        boolean active,
        String subject,
        String actorType,
        String username,
        String clientId,
        List<String> roles,
        List<String> scopes,
        Long expiresAtEpochSeconds
) {}
