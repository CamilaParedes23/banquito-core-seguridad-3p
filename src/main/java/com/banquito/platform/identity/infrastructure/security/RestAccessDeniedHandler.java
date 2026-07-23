package com.banquito.platform.identity.infrastructure.security;

import com.banquito.platform.identity.api.dto.api.ErrorResponse;
import com.banquito.platform.identity.shared.tracing.CorrelationIdHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    public RestAccessDeniedHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        ErrorResponse body = new ErrorResponse(
                LocalDateTime.now(),
                CorrelationIdHolder.get(),
                "SECURITY_ACCESS_DENIED",
                "Acceso denegado. El token no posee los permisos o scopes requeridos para este recurso.",
                List.of()
        );
        response.getWriter().write(objectMapper.writeValueAsString(body));
    }
}
