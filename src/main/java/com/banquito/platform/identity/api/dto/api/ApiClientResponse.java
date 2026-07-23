package com.banquito.platform.identity.api.dto.api;

import java.util.List;

public record ApiClientResponse(
        String clientId,
        String name,
        String originService,
        String clientType,
        String status,
        List<String> scopes
) {}
