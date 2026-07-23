package com.banquito.platform.identity.domain.enums;

import lombok.Getter;

@Getter
public enum TipoParametroSeguridadEnum {
    STRING("STRING"),
    INTEGER("INTEGER"),
    DECIMAL("DECIMAL"),
    BOOLEAN("BOOLEAN"),
    TIME("TIME"),
    JSON("JSON");

    private final String value;

    TipoParametroSeguridadEnum(String value) {
        this.value = value;
    }
}
