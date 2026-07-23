package com.banquito.platform.identity.infrastructure.config;

import com.banquito.platform.identity.infrastructure.security.GoogleOAuthProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtTimestampValidator;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import java.util.List;
import java.util.Set;

@Configuration
public class GoogleOAuthConfig {

    @Bean("googleJwtDecoder")
    public JwtDecoder googleJwtDecoder(GoogleOAuthProperties properties) {
        if (properties.clientId() == null || properties.clientId().isBlank()) {
            throw new IllegalStateException("GOOGLE_OAUTH_CLIENT_ID es obligatorio");
        }

        NimbusJwtDecoder decoder = NimbusJwtDecoder.withJwkSetUri(properties.jwkSetUri()).build();

        OAuth2TokenValidator<Jwt> issuerAndAudience = jwt -> {
            String issuer = jwt.getIssuer() == null ? null : jwt.getIssuer().toString();
            Set<String> validIssuers = Set.of(properties.issuer(), "accounts.google.com");
            List<String> audience = jwt.getAudience();

            if (!validIssuers.contains(issuer)) {
                return OAuth2TokenValidatorResult.failure(
                        new OAuth2Error("invalid_token", "Emisor Google inválido", null));
            }
            if (audience == null || !audience.contains(properties.clientId())) {
                return OAuth2TokenValidatorResult.failure(
                        new OAuth2Error("invalid_token", "Audiencia Google inválida", null));
            }
            return OAuth2TokenValidatorResult.success();
        };

        decoder.setJwtValidator(new DelegatingOAuth2TokenValidator<>(
                new JwtTimestampValidator(),
                issuerAndAudience
        ));
        return decoder;
    }
}
