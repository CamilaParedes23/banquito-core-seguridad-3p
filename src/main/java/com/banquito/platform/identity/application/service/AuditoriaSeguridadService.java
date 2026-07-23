package com.banquito.platform.identity.application.service;

import com.banquito.platform.identity.domain.enums.ResultadoAuditoriaSeguridadEnum;
import com.banquito.platform.identity.domain.model.ApiClient;
import com.banquito.platform.identity.domain.model.AuditoriaSeguridad;
import com.banquito.platform.identity.domain.model.UsuarioIdentidad;
import com.banquito.platform.identity.domain.repository.AuditoriaSeguridadRepository;
import com.banquito.platform.identity.shared.tracing.CorrelationIdHolder;
import org.springframework.stereotype.Service;

@Service
public class AuditoriaSeguridadService {
    private final AuditoriaSeguridadRepository repository;

    public AuditoriaSeguridadService(AuditoriaSeguridadRepository repository) {
        this.repository = repository;
    }

    public void registrarUsuario(UsuarioIdentidad usuario, String evento, ResultadoAuditoriaSeguridadEnum resultado,
                                 String ip, String userAgent, String detalleJson) {
        repository.save(AuditoriaSeguridad.crear(CorrelationIdHolder.get(), usuario, null, evento, resultado, ip, userAgent, detalleJson));
    }

    public void registrarClienteTecnico(ApiClient apiClient, String evento, ResultadoAuditoriaSeguridadEnum resultado,
                                        String ip, String userAgent, String detalleJson) {
        repository.save(AuditoriaSeguridad.crear(CorrelationIdHolder.get(), null, apiClient, evento, resultado, ip, userAgent, detalleJson));
    }

    public void registrarSistema(String evento, ResultadoAuditoriaSeguridadEnum resultado, String detalleJson) {
        repository.save(AuditoriaSeguridad.crear(CorrelationIdHolder.get(), null, null, evento, resultado, null, null, detalleJson));
    }
}
