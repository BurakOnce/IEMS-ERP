package com.example.iems.model.Enum;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN("ADMIN"),
    ROLE_EMPLOYEE("EMPLOYEE"),
    ROLE_MANAGER("MANAGER"),
    ROLE_SUPPLIER("SUPPLIER");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
