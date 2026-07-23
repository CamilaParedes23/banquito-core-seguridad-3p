package com.banquito.platform.identity.domain.repository;

import com.banquito.platform.identity.domain.model.ApiClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApiClientRepository extends JpaRepository<ApiClient, Long> {
    Optional<ApiClient> findByClientId(String clientId);
    boolean existsByClientId(String clientId);
}
