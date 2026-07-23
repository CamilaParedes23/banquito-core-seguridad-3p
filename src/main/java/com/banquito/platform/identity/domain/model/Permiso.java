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
@Table(name = "PERMISO")
public class Permiso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "CODIGO", length = 100, nullable = false)
    private String codigo;

    @Column(name = "NOMBRE", length = 150, nullable = false)
    private String nombre;

    @Column(name = "MODULO", length = 50, nullable = false)
    private String modulo;

    @Column(name = "ACCION", length = 50, nullable = false)
    private String accion;

    @Column(name = "RECURSO", length = 80, nullable = false)
    private String recurso;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO", length = 15, nullable = false)
    private EstadoGeneralEnum estado;

    @Column(name = "FECHA_CREACION", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "FECHA_ACTUALIZACION", nullable = false)
    private LocalDateTime fechaActualizacion;

    @Version
    @Column(name = "VERSION", nullable = false)
    private Integer version;

    public Permiso() {}
    public Permiso(Integer id) { this.id = id; }

    @Override public boolean equals(Object o) { if (this == o) return true; if (!(o instanceof Permiso that)) return false; if (this.id == null || that.id == null) return false; return Objects.equals(id, that.id); }
    @Override public int hashCode() { return Objects.hashCode(id); }
    @Override public String toString() { return "Permiso{" + "id=" + id + ", codigo='" + codigo + '\'' + ", modulo='" + modulo + '\'' + ", accion='" + accion + '\'' + '}'; }
}
