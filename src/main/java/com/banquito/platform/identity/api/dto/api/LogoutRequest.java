package com.banquito.platform.identity.api.dto.api;

public record LogoutRequest(String accessToken, String refreshToken, String sessionUuid) {}
