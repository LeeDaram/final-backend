package com.example.finalEclips.eclips.config.jwt;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.example.finalEclips.eclips.config.exception.ErrorDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {
    private final TokenProvider tokenProvider;
    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String token = extractTokenFromRequestHeader(httpServletRequest);
        try {
            if (StringUtils.hasText(token) && tokenProvider.isValidToken(token)) {
                Authentication authentication = tokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setContentType("application/json");
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), e.getMessage(), token);

            JavaTimeModule javaTimeModule = new JavaTimeModule();
            javaTimeModule.addSerializer(LocalDateTime.class,
                    new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS")));

            String json = new ObjectMapper().registerModule(javaTimeModule).writeValueAsString(errorDetails);

            httpResponse.getWriter().write(json);
        }
    }

    // Request Header 에서 토큰 정보를 추출
    private String extractTokenFromRequestHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        return StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ") ? bearerToken.substring(7) : null;
    }
}
