package com.banquito.platform.identity.domain.enums;

import lombok.Getter;

@Getter
public enum EstadoSesionEnum {
    ACTIVA("ACTIVA"),
    EXPIRADA("EXPIRADA"),
    REVOCADA("REVOCADA"),
    CERRADA("CERRADA");

    private final String value;

    EstadoSesionEnum(String value) {
        this.value = value;
    }
}
