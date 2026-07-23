package com.banquito.platform.identity.domain.model;

import com.banquito.platform.identity.domain.enums.EstadoUsuarioIdentidadEnum;
import com.banquito.platform.identity.domain.enums.TipoActorEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "USUARIO_IDENTIDAD")
public class UsuarioIdentidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "UUID_USUARIO", length = 36, nullable = false)
    private String uuidUsuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_ACTOR", length = 15, nullable = false)
    private TipoActorEnum tipoActor;

    @Column(name = "USERNAME", length = 80, nullable = false)
    private String username;

    @Column(name = "EMAIL", length = 160)
    private String email;

    @Column(name = "EMAIL_VERIFICADO", nullable = false)
    private Boolean emailVerificado;

    @Column(name = "TELEFONO_MOVIL", length = 25)
    private String telefonoMovil;

    @Column(name = "PASSWORD_HASH", length = 255)
    private String passwordHash;

    @Column(name = "PASSWORD_ALGORITHM", length = 30)
    private String passwordAlgorithm;

    @Column(name = "PASSWORD_UPDATED_AT")
    private LocalDateTime passwordUpdatedAt;

    @Column(name = "REQUIERE_CAMBIO_PASSWORD", nullable = false)
    private Boolean requiereCambioPassword;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO", length = 20, nullable = false)
    private EstadoUsuarioIdentidadEnum estado;

    @Column(name = "INTENTOS_FALLIDOS", nullable = false)
    private Integer intentosFallidos;

    @Column(name = "BLOQUEADO_HASTA")
    private LocalDateTime bloqueadoHasta;

    @Column(name = "ULTIMO_LOGIN")
    private LocalDateTime ultimoLogin;

    @Column(name = "UUID_REFERENCIA_EXTERNA", length = 36)
    private String uuidReferenciaExterna;

    @Column(name = "REFERENCIA_TIPO", length = 30)
    private String referenciaTipo;

    @Column(name = "FECHA_CREACION", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "FECHA_ACTUALIZACION", nullable = false)
    private LocalDateTime fechaActualizacion;

    @Version
    @Column(name = "VERSION", nullable = false)
    private Integer version;

    public UsuarioIdentidad() {
    }

    public UsuarioIdentidad(Long id) {
        this.id = id;
    }

    public boolean estaActivo() {
        return EstadoUsuarioIdentidadEnum.ACTIVO.equals(this.estado);
    }

    public boolean estaBloqueadoTemporalmente(LocalDateTime ahora) {
        return this.bloqueadoHasta != null && this.bloqueadoHasta.isAfter(ahora);
    }

    public void registrarLoginExitoso(LocalDateTime ahora) {
        this.ultimoLogin = ahora;
        this.intentosFallidos = 0;
        this.bloqueadoHasta = null;
    }

    public void registrarLoginFallido(int maxIntentos, LocalDateTime bloqueadoHasta) {
        this.intentosFallidos = (this.intentosFallidos == null ? 0 : this.intentosFallidos) + 1;
        if (this.intentosFallidos >= maxIntentos) {
            this.estado = EstadoUsuarioIdentidadEnum.BLOQUEADO;
            this.bloqueadoHasta = bloqueadoHasta;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioIdentidad that)) return false;
        if (this.id == null || that.id == null) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "UsuarioIdentidad{" +
                "id=" + id +
                ", uuidUsuario='" + uuidUsuario + '\'' +
                ", username='" + username + '\'' +
                ", tipoActor=" + tipoActor +
                ", estado=" + estado +
                '}';
    }
}
