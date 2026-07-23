package com.banquito.platform.identity.domain.enums;

import lombok.Getter;

@Getter
public enum ResultadoAuditoriaSeguridadEnum {
    OK("OK"),
    ERROR("ERROR"),
    DENEGADO("DENEGADO"),
    ALERTA("ALERTA");

    private final String value;

    ResultadoAuditoriaSeguridadEnum(String value) {
        this.value = value;
    }
}
