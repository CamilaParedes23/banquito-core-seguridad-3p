package com.banquito.platform.identity.domain.model;

import com.banquito.platform.identity.domain.enums.EstadoAsignacionRolEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "USUARIO_ROL")
public class UsuarioRol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USUARIO_ID", nullable = false)
    private UsuarioIdentidad usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ROL_ID", nullable = false)
    private Rol rol;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO", length = 15, nullable = false)
    private EstadoAsignacionRolEnum estado;

    @Column(name = "ASIGNADO_POR_UUID", length = 36)
    private String asignadoPorUuid;

    @Column(name = "FECHA_ASIGNACION", nullable = false)
    private LocalDateTime fechaAsignacion;

    @Column(name = "FECHA_REVOCACION")
    private LocalDateTime fechaRevocacion;

    public UsuarioRol() {}
    public UsuarioRol(Long id) { this.id = id; }

    public static UsuarioRol crear(UsuarioIdentidad usuario, Rol rol, String asignadoPorUuid) {
        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.usuario = usuario;
        usuarioRol.rol = rol;
        usuarioRol.estado = EstadoAsignacionRolEnum.ACTIVO;
        usuarioRol.asignadoPorUuid = asignadoPorUuid;
        usuarioRol.fechaAsignacion = LocalDateTime.now();
        return usuarioRol;
    }

    public void revocar() {
        this.estado = EstadoAsignacionRolEnum.REVOCADO;
        this.fechaRevocacion = LocalDateTime.now();
    }

    @Override public boolean equals(Object o) { if (this == o) return true; if (!(o instanceof UsuarioRol that)) return false; if (this.id == null || that.id == null) return false; return Objects.equals(id, that.id); }
    @Override public int hashCode() { return Objects.hashCode(id); }
    @Override public String toString() { return "UsuarioRol{" + "id=" + id + ", estado=" + estado + '}'; }
}
