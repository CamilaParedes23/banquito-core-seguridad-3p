package com.banquito.platform.identity.domain.enums;

import lombok.Getter;

@Getter
public enum EstadoOutboxEventEnum {
    PENDIENTE("PENDIENTE"),
    PUBLICADO("PUBLICADO"),
    ERROR("ERROR"),
    DESCARTADO("DESCARTADO");

    private final String value;

    EstadoOutboxEventEnum(String value) {
        this.value = value;
    }
}
