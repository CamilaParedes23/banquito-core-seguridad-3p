package com.banquito.platform.identity.application.service;

import com.banquito.platform.identity.domain.enums.EstadoAsignacionRolEnum;
import com.banquito.platform.identity.domain.enums.EstadoGeneralEnum;
import com.banquito.platform.identity.domain.model.*;
import com.banquito.platform.identity.domain.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PermissionQueryService {
    private final UsuarioRolRepository usuarioRolRepository;
    private final RolPermisoRepository rolPermisoRepository;
    private final ApiClientScopeRepository apiClientScopeRepository;

    public PermissionQueryService(UsuarioRolRepository usuarioRolRepository,
                                  RolPermisoRepository rolPermisoRepository,
                                  ApiClientScopeRepository apiClientScopeRepository) {
        this.usuarioRolRepository = usuarioRolRepository;
        this.rolPermisoRepository = rolPermisoRepository;
        this.apiClientScopeRepository = apiClientScopeRepository;
    }

    @Transactional(readOnly = true)
    public List<String> rolesActivos(UsuarioIdentidad usuario) {
        return usuarioRolRepository.findByUsuarioAndEstado(usuario, EstadoAsignacionRolEnum.ACTIVO).stream()
                .map(UsuarioRol::getRol)
                .filter(r -> EstadoGeneralEnum.ACTIVO.equals(r.getEstado()))
                .map(Rol::getCodigo)
                .distinct()
                .sorted()
                .toList();
    }

    @Transactional(readOnly = true)
    public List<String> permisosActivos(UsuarioIdentidad usuario) {
        return usuarioRolRepository.findByUsuarioAndEstado(usuario, EstadoAsignacionRolEnum.ACTIVO).stream()
                .flatMap(ur -> rolPermisoRepository.findByRol(ur.getRol()).stream())
                .filter(rp -> EstadoGeneralEnum.ACTIVO.equals(rp.getEstado()))
                .map(RolPermiso::getPermiso)
                .filter(p -> EstadoGeneralEnum.ACTIVO.equals(p.getEstado()))
                .map(Permiso::getCodigo)
                .distinct()
                .sorted()
                .toList();
    }

    @Transactional(readOnly = true)
    public List<String> scopesActivos(UsuarioIdentidad usuario) {
        return permisosActivos(usuario);
    }

    @Transactional(readOnly = true)
    public List<String> scopesActivos(ApiClient client) {
        return apiClientScopeRepository.findByApiClientAndEstado(client, EstadoGeneralEnum.ACTIVO).stream()
                .map(ApiClientScope::getScope)
                .distinct()
                .sorted()
                .toList();
    }

    @Transactional(readOnly = true)
    public boolean apiClientTieneScope(ApiClient client, String scope) {
        if (scope == null || scope.isBlank()) return true;
        return apiClientScopeRepository.existsByApiClientAndScopeAndEstado(client, scope, EstadoGeneralEnum.ACTIVO);
    }
}
