package com.banquito.platform.identity.domain.enums;

import lombok.Getter;

@Getter
public enum EstadoApiClientEnum {
    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO"),
    REVOCADO("REVOCADO"),
    EXPIRADO("EXPIRADO");

    private final String value;

    EstadoApiClientEnum(String value) {
        this.value = value;
    }
}
