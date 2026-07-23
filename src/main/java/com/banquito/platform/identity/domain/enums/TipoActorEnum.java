package com.banquito.platform.identity.domain.enums;

import lombok.Getter;

@Getter
public enum TipoActorEnum {
    CLIENTE("CLIENTE"),
    EMPLEADO("EMPLEADO"),
    SERVICIO("SERVICIO");

    private final String value;

    TipoActorEnum(String value) {
        this.value = value;
    }
}
