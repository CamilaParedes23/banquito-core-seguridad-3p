package com.banquito.platform.identity.domain.repository;

import com.banquito.platform.identity.domain.enums.EstadoGeneralEnum;
import com.banquito.platform.identity.domain.model.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PermisoRepository extends JpaRepository<Permiso, Integer> {
    Optional<Permiso> findByCodigo(String codigo);
    boolean existsByCodigo(String codigo);
    List<Permiso> findByEstadoOrderByModuloAscCodigoAsc(EstadoGeneralEnum estado);
}
