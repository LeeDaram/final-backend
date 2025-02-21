package com.example.finalEclips.eclips.helper;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityHelper {
    public static Optional<String> getLoggedId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            return Optional.empty();
        }

        String loggedId = null;
        if (authentication.getPrincipal() instanceof UserDetails springSecurityUser) {
            loggedId = springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            loggedId = (String) authentication.getPrincipal();
        }

        return Optional.ofNullable(loggedId);
    }
}