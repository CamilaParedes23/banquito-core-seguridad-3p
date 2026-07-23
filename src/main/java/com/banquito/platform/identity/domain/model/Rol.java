package com.banquito.platform.identity.domain.model;

import com.banquito.platform.identity.domain.enums.EstadoGeneralEnum;
import com.banquito.platform.identity.domain.enums.TipoRolEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "ROL")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "CODIGO", length = 60, nullable = false)
    private String codigo;

    @Column(name = "NOMBRE", length = 120, nullable = false)
    private String nombre;

    @Column(name = "DESCRIPCION", length = 300)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_ROL", length = 15, nullable = false)
    private TipoRolEnum tipoRol;

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

    public Rol() {}
    public Rol(Integer id) { this.id = id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rol that)) return false;
        if (this.id == null || that.id == null) return false;
        return Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() { return Objects.hashCode(id); }
    @Override
    public String toString() { return "Rol{" + "id=" + id + ", codigo='" + codigo + '\'' + ", tipoRol=" + tipoRol + ", estado=" + estado + '}'; }
}
