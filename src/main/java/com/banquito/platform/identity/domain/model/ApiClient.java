package com.banquito.platform.identity.domain.model;

import com.banquito.platform.identity.domain.enums.EstadoApiClientEnum;
import com.banquito.platform.identity.domain.enums.TipoApiClientEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "API_CLIENT")
public class ApiClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "CLIENT_ID", length = 100, nullable = false)
    private String clientId;

    @Column(name = "CLIENT_SECRET_HASH", length = 255, nullable = false)
    private String clientSecretHash;

    @Column(name = "NOMBRE", length = 150, nullable = false)
    private String nombre;

    @Column(name = "SERVICIO_ORIGEN", length = 80, nullable = false)
    private String servicioOrigen;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_CLIENTE", length = 20, nullable = false)
    private TipoApiClientEnum tipoCliente;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO", length = 15, nullable = false)
    private EstadoApiClientEnum estado;

    @Column(name = "FECHA_EXPIRACION")
    private LocalDateTime fechaExpiracion;

    @Column(name = "FECHA_CREACION", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "FECHA_ACTUALIZACION", nullable = false)
    private LocalDateTime fechaActualizacion;

    @Version
    @Column(name = "VERSION", nullable = false)
    private Integer version;

    public ApiClient() {}
    public ApiClient(Long id) { this.id = id; }

    public boolean estaActivo(LocalDateTime ahora) {
        return EstadoApiClientEnum.ACTIVO.equals(this.estado)
                && (this.fechaExpiracion == null || this.fechaExpiracion.isAfter(ahora));
    }

    @Override public boolean equals(Object o) { if (this == o) return true; if (!(o instanceof ApiClient that)) return false; if (this.id == null || that.id == null) return false; return Objects.equals(id, that.id); }
    @Override public int hashCode() { return Objects.hashCode(id); }
    @Override public String toString() { return "ApiClient{" + "id=" + id + ", clientId='" + clientId + '\'' + ", estado=" + estado + '}'; }
}
