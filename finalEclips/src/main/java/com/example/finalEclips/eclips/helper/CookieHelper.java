package com.example.finalEclips.eclips.helper;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import com.example.finalEclips.eclips.config.property.JwtPropertySource;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CookieHelper {
    private final JwtPropertySource jwtPropertySource;

    public String makeJwtCookie(String jwt) {
        return ResponseCookie.from(jwtPropertySource.getCookieName(), jwt)
                .httpOnly(jwtPropertySource.isEnabledHttpOnly()).secure(jwtPropertySource.isEnabledSecure())
                .path(jwtPropertySource.getPath()).maxAge(jwtPropertySource.getMaxAge()).build().toString();
    }

    public void deleteJwtCookie(HttpHeaders httpHeaders) {
        String cookie = ResponseCookie.from(jwtPropertySource.getCookieName(), "")
                .httpOnly(jwtPropertySource.isEnabledHttpOnly()).secure(jwtPropertySource.isEnabledSecure())
                .path(jwtPropertySource.getPath()).maxAge(0).build().toString();
        httpHeaders.add("Set-Cookie", cookie);

    }
}
