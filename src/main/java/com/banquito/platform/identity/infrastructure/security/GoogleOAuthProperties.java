package com.banquito.platform.identity.infrastructure.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "banquito.security.google")
public record GoogleOAuthProperties(
        String clientId,
        String issuer,
        String jwkSetUri
) {}
