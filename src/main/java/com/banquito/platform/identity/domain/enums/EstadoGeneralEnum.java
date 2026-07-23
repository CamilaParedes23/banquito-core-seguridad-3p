package com.banquito.platform.identity.domain.enums;

import lombok.Getter;

@Getter
public enum EstadoGeneralEnum {
    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO");

    private final String value;

    EstadoGeneralEnum(String value) {
        this.value = value;
    }
}
