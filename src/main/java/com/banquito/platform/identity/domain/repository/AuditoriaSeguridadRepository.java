package com.banquito.platform.identity.domain.repository;

import com.banquito.platform.identity.domain.model.AuditoriaSeguridad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditoriaSeguridadRepository extends JpaRepository<AuditoriaSeguridad, Long> {
}
