package com.banquito.platform.identity.infrastructure.security;

import com.banquito.platform.identity.shared.exception.BusinessException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;

@Service
public class GoogleIdTokenVerifierService {
    private final JwtDecoder googleJwtDecoder;

    public GoogleIdTokenVerifierService(@Qualifier("googleJwtDecoder") JwtDecoder googleJwtDecoder) {
        this.googleJwtDecoder = googleJwtDecoder;
    }

    public GoogleIdentity verify(String credential) {
        final Jwt jwt;
        try {
            jwt = googleJwtDecoder.decode(credential);
        } catch (JwtException ex) {
            throw new BusinessException(
                    "GOOGLE_TOKEN_INVALID",
                    "El token de Google es inválido o expiró",
                    HttpStatus.UNAUTHORIZED
            );
        }

        String email = jwt.getClaimAsString("email");
        Boolean emailVerified = jwt.getClaim("email_verified");
        if (email == null || email.isBlank() || !Boolean.TRUE.equals(emailVerified)) {
            throw new BusinessException(
                    "GOOGLE_EMAIL_NOT_VERIFIED",
                    "La cuenta Google no tiene un correo verificado",
                    HttpStatus.UNAUTHORIZED
            );
        }

        return new GoogleIdentity(jwt.getSubject(), email.trim());
    }

    public record GoogleIdentity(String subject, String email) {}
}
