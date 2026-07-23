package com.banquito.platform.identity.domain.enums;

import lombok.Getter;

@Getter
public enum TipoApiClientEnum {
    SERVICE("SERVICE"),
    GATEWAY("GATEWAY"),
    EXTERNAL("EXTERNAL");

    private final String value;

    TipoApiClientEnum(String value) {
        this.value = value;
    }
}
