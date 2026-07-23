package com.banquito.platform.identity.domain.enums;

import lombok.Getter;

@Getter
public enum EstadoUsuarioIdentidadEnum {
    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO"),
    BLOQUEADO("BLOQUEADO"),
    PENDIENTE("PENDIENTE"),
    REVOCADO("REVOCADO");

    private final String value;

    EstadoUsuarioIdentidadEnum(String value) {
        this.value = value;
    }
}
