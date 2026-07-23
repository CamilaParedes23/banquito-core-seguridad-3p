package com.banquito.platform.identity.infrastructure.config;

import com.banquito.platform.identity.domain.enums.*;
import com.banquito.platform.identity.domain.model.*;
import com.banquito.platform.identity.domain.repository.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class DemoDataInitializer implements ApplicationRunner {
    private final boolean enabled;
    private final String demoPassword;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioIdentidadRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final UsuarioRolRepository usuarioRolRepository;
    private final ApiClientRepository apiClientRepository;

    public DemoDataInitializer(@Value("${banquito.demo.enabled:true}") boolean enabled,
                               @Value("${banquito.demo.password:password}") String demoPassword,
                               PasswordEncoder passwordEncoder,
                               UsuarioIdentidadRepository usuarioRepository,
                               RolRepository rolRepository,
                               UsuarioRolRepository usuarioRolRepository,
                               ApiClientRepository apiClientRepository) {
        this.enabled = enabled;
        this.demoPassword = demoPassword;
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.usuarioRolRepository = usuarioRolRepository;
        this.apiClientRepository = apiClientRepository;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        if (!enabled) return;
        createUserIfMissing("admin.core", "admin.core@banquito.com", TipoActorEnum.EMPLEADO, "ADMIN_SEGURIDAD");
        createUserIfMissing("cajero.norte", "cajero.norte@banquito.com", TipoActorEnum.EMPLEADO, "CAJERO");
        createUserIfMissing("empresa.demo", "empresa.demo@banquito.com", TipoActorEnum.CLIENTE, "CLIENTE_EMPRESA");
        apiClientRepository.findAll().forEach(client -> {
            if ("CHANGE_ME_HASH".equals(client.getClientSecretHash())) {
                client.setClientSecretHash(passwordEncoder.encode(demoPassword));
                apiClientRepository.save(client);
            }
        });
    }

    private void createUserIfMissing(String username, String email, TipoActorEnum tipoActor, String roleCode) {
        if (usuarioRepository.existsByUsername(username)) return;
        UsuarioIdentidad user = new UsuarioIdentidad();
        user.setUuidUsuario(UUID.randomUUID().toString());
        user.setTipoActor(tipoActor);
        user.setUsername(username);
        user.setEmail(email);
        user.setEmailVerificado(true);
        user.setPasswordHash(passwordEncoder.encode(demoPassword));
        user.setPasswordAlgorithm("BCrypt");
        user.setPasswordUpdatedAt(LocalDateTime.now());
        user.setRequiereCambioPassword(false);
        user.setEstado(EstadoUsuarioIdentidadEnum.ACTIVO);
        user.setIntentosFallidos(0);
        user.setFechaCreacion(LocalDateTime.now());
        user.setFechaActualizacion(LocalDateTime.now());
        user.setVersion(0);
        UsuarioIdentidad savedUser = usuarioRepository.saveAndFlush(user);
        rolRepository.findByCodigo(roleCode)
                .ifPresent(role -> usuarioRolRepository.save(UsuarioRol.crear(savedUser, role, "SYSTEM")));
    }
}
