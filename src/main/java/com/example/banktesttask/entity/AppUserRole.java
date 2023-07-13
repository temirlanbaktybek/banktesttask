package com.example.banktesttask.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * Роли
 */
public enum AppUserRole implements GrantedAuthority {
    ROLE_ADMIN, ROLE_CLIENT, ROLE_CHARITY, ROLE_VOLUNTEER, ROLE_USER, ROLE_DONOR;

    public String getAuthority() {
        return name();
    }

}
