package com.banquito.platform.identity.domain.repository;

import com.banquito.platform.identity.domain.enums.EstadoAsignacionRolEnum;
import com.banquito.platform.identity.domain.model.UsuarioIdentidad;
import com.banquito.platform.identity.domain.model.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, Long> {
    List<UsuarioRol> findByUsuarioAndEstado(UsuarioIdentidad usuario, EstadoAsignacionRolEnum estado);

    @Query("select ur from UsuarioRol ur join ur.rol r where ur.usuario = :usuario and r.codigo = :rolCodigo")
    Optional<UsuarioRol> findByUsuarioAndRolCodigo(@Param("usuario") UsuarioIdentidad usuario, @Param("rolCodigo") String rolCodigo);
}
