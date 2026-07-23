package com.banquito.platform.identity.infrastructure.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "banquito.security.jwt")
public record JwtProperties(
        String issuer,
        String secret,
        long accessTokenMinutes,
        long refreshTokenDays
) {}
