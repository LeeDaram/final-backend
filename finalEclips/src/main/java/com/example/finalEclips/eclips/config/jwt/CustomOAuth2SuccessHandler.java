package com.example.finalEclips.eclips.config.jwt;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.finalEclips.eclips.config.property.JwtPropertySource;
import com.example.finalEclips.eclips.user.dto.UserDto;
import com.example.finalEclips.eclips.user.repository.UserMapper;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomOAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final TokenProvider tokenProvider;
    private final JwtPropertySource jwtPropertySource;
    private final UserMapper userMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException {

        String email = authentication.getName();

        UserDto user = userMapper.findUserById(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        String token = tokenProvider.createToken(authentication, user.getName(), user.getLoginType());

        addJwtToCookie(response, token);

        getRedirectStrategy().sendRedirect(request, response, "http://localhost:3000");
    }

    private void addJwtToCookie(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie("Authorization", token);
        cookie.setHttpOnly(false);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(Math.toIntExact(jwtPropertySource.getExpirationSeconds()));
        response.addCookie(cookie);
    }
}
