package com.banquito.platform.identity.domain.model;

import com.banquito.platform.identity.domain.enums.EstadoSesionEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "SESION_USUARIO")
public class SesionUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "UUID_SESION", length = 36, nullable = false)
    private String uuidSesion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USUARIO_ID", nullable = false)
    private UsuarioIdentidad usuario;

    @Column(name = "ACCESS_TOKEN_JTI", length = 36)
    private String accessTokenJti;

    @Column(name = "REFRESH_TOKEN_HASH", length = 255)
    private String refreshTokenHash;

    @Column(name = "IP_ORIGEN", length = 45)
    private String ipOrigen;

    @Column(name = "USER_AGENT", length = 500)
    private String userAgent;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO", length = 15, nullable = false)
    private EstadoSesionEnum estado;

    @Column(name = "FECHA_INICIO", nullable = false)
    private LocalDateTime fechaInicio;

    @Column(name = "FECHA_EXPIRACION", nullable = false)
    private LocalDateTime fechaExpiracion;

    @Column(name = "FECHA_CIERRE")
    private LocalDateTime fechaCierre;

    @Column(name = "MOTIVO_CIERRE", length = 200)
    private String motivoCierre;

    public SesionUsuario() {}
    public SesionUsuario(Long id) { this.id = id; }

    public void cerrar(String motivo) {
        this.estado = EstadoSesionEnum.CERRADA;
        this.fechaCierre = LocalDateTime.now();
        this.motivoCierre = motivo;
    }

    public void revocar(String motivo) {
        this.estado = EstadoSesionEnum.REVOCADA;
        this.fechaCierre = LocalDateTime.now();
        this.motivoCierre = motivo;
    }

    @Override public boolean equals(Object o) { if (this == o) return true; if (!(o instanceof SesionUsuario that)) return false; if (this.id == null || that.id == null) return false; return Objects.equals(id, that.id); }
    @Override public int hashCode() { return Objects.hashCode(id); }
    @Override public String toString() { return "SesionUsuario{" + "id=" + id + ", uuidSesion='" + uuidSesion + '\'' + ", estado=" + estado + '}'; }
}
