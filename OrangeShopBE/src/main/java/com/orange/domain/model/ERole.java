package com.orange.domain.model;

public enum ERole {
    ROLE_USER("user"),
    ROLE_STAFF("editor"),
    ROLE_ADMIN("admin");
    private final String value;

    ERole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
