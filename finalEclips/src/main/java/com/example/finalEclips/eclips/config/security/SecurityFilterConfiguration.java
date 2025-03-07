package com.example.finalEclips.eclips.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.finalEclips.eclips.config.exception.JwtAccessDeniedHandler;
import com.example.finalEclips.eclips.config.exception.JwtAuthenticationEntryPoint;
import com.example.finalEclips.eclips.config.jwt.CustomOAuth2SuccessHandler;
import com.example.finalEclips.eclips.config.jwt.JwtFilter;
import com.example.finalEclips.eclips.config.jwt.TokenProvider;
import com.example.finalEclips.eclips.user.service.UserServiceImpl;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityFilterConfiguration {
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final TokenProvider tokenProvider;
    private final CustomOAuth2SuccessHandler customOAuth2SuccessHandler;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, UserServiceImpl userService) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                // 회원가입, 로그인, 로그아웃, 이메일 인증
        		.requestMatchers("/**").permitAll()
//                .requestMatchers("/api/users/sign-up/**", "/api/users/sign-in", "/api/users/sign-out").permitAll()
//                .requestMatchers("/", "/login/**", "/oauth2/**", "/api/users/**", "/api/email/**", "/api/mypage/**",
//                        "/api/approval")
//                .permitAll()
                // 역할
                .requestMatchers("/api/users/personal/**").hasRole("USER").requestMatchers("/api/users/biz/**")
                .hasRole("BIZ").requestMatchers("/api/users/admin/**").hasRole("ADMIN")

                // 그 외 모든 요청
                .anyRequest().authenticated());

        http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.csrf(AbstractHttpConfigurer::disable);
        http.formLogin(AbstractHttpConfigurer::disable);
        http.httpBasic(AbstractHttpConfigurer::disable);
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));

        // 로컬 로그인 예외 처리
        http.exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler));

        // JWT 필터 추가
        http.addFilterBefore(new JwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);

        // OAuth2 로그인
        http.oauth2Login(oauth2 -> oauth2.failureUrl("/login?error=true")
                .userInfoEndpoint(userInfo -> userInfo.userService(userService))
                .successHandler(customOAuth2SuccessHandler));

        // 로그아웃 처리
        http.logout(logout -> logout.logoutUrl("/api/users/sign-out").invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "Authorization")
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.setStatus(HttpServletResponse.SC_OK);
                }));

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*"); // 실제로 서버와 통신할 패턴만 주는게 좋다고 하는데 일단 몰?루
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
