package com.banquito.platform.identity.domain.enums;

import lombok.Getter;

@Getter
public enum EstadoAsignacionRolEnum {
    ACTIVO("ACTIVO"),
    REVOCADO("REVOCADO"),
    INACTIVO("INACTIVO");

    private final String value;

    EstadoAsignacionRolEnum(String value) {
        this.value = value;
    }
}
