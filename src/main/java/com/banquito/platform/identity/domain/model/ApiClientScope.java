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
@Table(name = "API_CLIENT_SCOPE")
public class ApiClientScope {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "API_CLIENT_ID", nullable = false)
    private ApiClient apiClient;

    @Column(name = "SCOPE", length = 120, nullable = false)
    private String scope;

    @Column(name = "DESCRIPCION", length = 300)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO", length = 15, nullable = false)
    private EstadoGeneralEnum estado;

    @Column(name = "FECHA_CREACION", nullable = false)
    private LocalDateTime fechaCreacion;

    public ApiClientScope() {}
    public ApiClientScope(Long id) { this.id = id; }

    @Override public boolean equals(Object o) { if (this == o) return true; if (!(o instanceof ApiClientScope that)) return false; if (this.id == null || that.id == null) return false; return Objects.equals(id, that.id); }
    @Override public int hashCode() { return Objects.hashCode(id); }
    @Override public String toString() { return "ApiClientScope{" + "id=" + id + ", scope='" + scope + '\'' + ", estado=" + estado + '}'; }
}
