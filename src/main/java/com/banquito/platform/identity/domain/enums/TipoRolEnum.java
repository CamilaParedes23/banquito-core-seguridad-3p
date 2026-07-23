package com.banquito.platform.identity.domain.enums;

import lombok.Getter;

@Getter
public enum TipoRolEnum {
    INTERNO("INTERNO"),
    CLIENTE("CLIENTE"),
    SERVICIO("SERVICIO");

    private final String value;

    TipoRolEnum(String value) {
        this.value = value;
    }
}
