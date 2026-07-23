package com.banquito.platform.identity.domain.model;

import com.banquito.platform.identity.domain.enums.EstadoGeneralEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "ROL_PERMISO")
public class RolPermiso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ROL_ID", nullable = false)
    private Rol rol;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PERMISO_ID", nullable = false)
    private Permiso permiso;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO", length = 15, nullable = false)
    private EstadoGeneralEnum estado;

    @Column(name = "FECHA_ASIGNACION", nullable = false)
    private LocalDateTime fechaAsignacion;

    public RolPermiso() {}
    public RolPermiso(Long id) { this.id = id; }

    public static RolPermiso crear(Rol rol, Permiso permiso) {
        RolPermiso rp = new RolPermiso();
        rp.rol = rol;
        rp.permiso = permiso;
        rp.estado = EstadoGeneralEnum.ACTIVO;
        rp.fechaAsignacion = LocalDateTime.now();
        return rp;
    }

    @Override public boolean equals(Object o) { if (this == o) return true; if (!(o instanceof RolPermiso that)) return false; if (this.id == null || that.id == null) return false; return Objects.equals(id, that.id); }
    @Override public int hashCode() { return Objects.hashCode(id); }
    @Override public String toString() { return "RolPermiso{" + "id=" + id + ", estado=" + estado + '}'; }
}
