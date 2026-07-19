package com.example.buyorrentacar.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_SOPSTVENIK,
    ROLE_KUPUVAC;
    @Override
    public String getAuthority() {
        return name();
    }
}
