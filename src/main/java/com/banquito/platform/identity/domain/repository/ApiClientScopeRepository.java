package com.banquito.platform.identity.domain.repository;

import com.banquito.platform.identity.domain.enums.EstadoGeneralEnum;
import com.banquito.platform.identity.domain.model.ApiClient;
import com.banquito.platform.identity.domain.model.ApiClientScope;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApiClientScopeRepository extends JpaRepository<ApiClientScope, Long> {
    List<ApiClientScope> findByApiClientAndEstado(ApiClient apiClient, EstadoGeneralEnum estado);
    Optional<ApiClientScope> findByApiClientAndScope(ApiClient apiClient, String scope);
    boolean existsByApiClientAndScopeAndEstado(ApiClient apiClient, String scope, EstadoGeneralEnum estado);
}
