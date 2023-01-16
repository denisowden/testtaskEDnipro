package com.journal.demo.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    TEACHER;

    @Override
    public String getAuthority() {
        return name();
    }
}
