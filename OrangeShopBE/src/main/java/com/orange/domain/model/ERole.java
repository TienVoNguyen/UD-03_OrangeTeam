package com.orange.domain.model;

public enum ERole {
    ROLE_USER("User"),
    ROLE_MODERATOR("Editor"),
    ROLE_ADMIN("Admin");
    private final String value;

    ERole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
