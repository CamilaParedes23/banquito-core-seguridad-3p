package com.banquito.platform.identity.domain.model;

import com.banquito.platform.identity.domain.enums.ResultadoAuditoriaSeguridadEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "AUDITORIA_SEGURIDAD")
public class AuditoriaSeguridad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "UUID_CORRELACION", length = 36)
    private String uuidCorrelacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USUARIO_ID")
    private UsuarioIdentidad usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "API_CLIENT_ID")
    private ApiClient apiClient;

    @Column(name = "EVENTO", length = 60, nullable = false)
    private String evento;

    @Enumerated(EnumType.STRING)
    @Column(name = "RESULTADO", length = 15, nullable = false)
    private ResultadoAuditoriaSeguridadEnum resultado;

    @Column(name = "IP_ORIGEN", length = 45)
    private String ipOrigen;

    @Column(name = "USER_AGENT", length = 500)
    private String userAgent;

    @Column(name = "DETALLE_JSON", columnDefinition = "json")
    private String detalleJson;

    @Column(name = "FECHA_EVENTO", nullable = false)
    private LocalDateTime fechaEvento;

    public AuditoriaSeguridad() {}
    public AuditoriaSeguridad(Long id) { this.id = id; }

    public static AuditoriaSeguridad crear(String uuidCorrelacion, UsuarioIdentidad usuario, ApiClient apiClient,
                                           String evento, ResultadoAuditoriaSeguridadEnum resultado,
                                           String ipOrigen, String userAgent, String detalleJson) {
        AuditoriaSeguridad auditoria = new AuditoriaSeguridad();
        auditoria.uuidCorrelacion = uuidCorrelacion;
        auditoria.usuario = usuario;
        auditoria.apiClient = apiClient;
        auditoria.evento = evento;
        auditoria.resultado = resultado;
        auditoria.ipOrigen = ipOrigen;
        auditoria.userAgent = userAgent;
        auditoria.detalleJson = detalleJson;
        auditoria.fechaEvento = LocalDateTime.now();
        return auditoria;
    }

    @Override public boolean equals(Object o) { if (this == o) return true; if (!(o instanceof AuditoriaSeguridad that)) return false; if (this.id == null || that.id == null) return false; return Objects.equals(id, that.id); }
    @Override public int hashCode() { return Objects.hashCode(id); }
    @Override public String toString() { return "AuditoriaSeguridad{" + "id=" + id + ", evento='" + evento + '\'' + ", resultado=" + resultado + '}'; }
}
