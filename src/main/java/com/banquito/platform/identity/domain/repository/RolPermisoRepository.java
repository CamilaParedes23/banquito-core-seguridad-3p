package com.banquito.platform.identity.domain.repository;

import com.banquito.platform.identity.domain.model.Rol;
import com.banquito.platform.identity.domain.model.RolPermiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RolPermisoRepository extends JpaRepository<RolPermiso, Long> {
    List<RolPermiso> findByRol(Rol rol);

    @Query("select rp from RolPermiso rp join rp.permiso p where rp.rol = :rol and p.codigo = :permisoCodigo")
    Optional<RolPermiso> findByRolAndPermisoCodigo(@Param("rol") Rol rol, @Param("permisoCodigo") String permisoCodigo);
}
