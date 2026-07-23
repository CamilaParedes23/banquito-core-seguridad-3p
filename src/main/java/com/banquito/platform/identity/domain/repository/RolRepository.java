package com.banquito.platform.identity.domain.repository;

import com.banquito.platform.identity.domain.enums.EstadoGeneralEnum;
import com.banquito.platform.identity.domain.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByCodigo(String codigo);
    boolean existsByCodigo(String codigo);
    List<Rol> findByEstadoOrderByNombreAsc(EstadoGeneralEnum estado);
}
