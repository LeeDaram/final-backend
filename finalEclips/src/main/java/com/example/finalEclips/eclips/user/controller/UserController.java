package com.example.finalEclips.eclips.user.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalEclips.eclips.config.jwt.TokenDto;
import com.example.finalEclips.eclips.config.jwt.TokenProvider;
import com.example.finalEclips.eclips.helper.CookieHelper;
import com.example.finalEclips.eclips.user.dto.CreateBizUserDto;
import com.example.finalEclips.eclips.user.dto.CreateUserDto;
import com.example.finalEclips.eclips.user.dto.SignInDto;
import com.example.finalEclips.eclips.user.dto.TermsAagreementDto;
import com.example.finalEclips.eclips.user.dto.TermsDto;
import com.example.finalEclips.eclips.user.dto.UserDto;
import com.example.finalEclips.eclips.user.dto.UserInfoUpdate;
import com.example.finalEclips.eclips.user.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CookieHelper cookieHelper;
    private final TokenProvider tokenProvider;

    // 아이디 중복확인
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") String id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    // 회원가입 : 개인회원
    @PostMapping("/sign-up/personal")
    public ResponseEntity<String> registerUser(@Valid @RequestBody CreateUserDto createUserDto) {
        userService.createUser(createUserDto);
        return ResponseEntity.ok("개인회원 회원가입 성공");
    }

    // 회원가입 : 관리자
    @PostMapping("/sign-up/admin")
    public ResponseEntity<String> registerAdmin(@Valid @RequestBody CreateUserDto createUserDto) {
        userService.createAdmin(createUserDto);
        return ResponseEntity.ok("관리자 회원가입 성공");
    }

    // 회원가입 : 사업자
    @PostMapping("/sign-up/biz")
    public ResponseEntity<String> registerBizUser(@Valid @RequestBody CreateBizUserDto createBizUserDto) {
        userService.createBizUser(createBizUserDto);
        return ResponseEntity.ok("사업자 회원가입 성공");
    }

    // 로그인
    @PostMapping("/sign-in")
    public ResponseEntity<TokenDto> signIn(@RequestBody @Valid SignInDto signInDto) {
        String token = userService.createToken(signInDto);

        // 쿠키에 토큰 추가
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Set-Cookie", cookieHelper.makeJwtCookie(token));

        return new ResponseEntity<>(TokenDto.builder().token(token).build(), httpHeaders, HttpStatus.OK);
    }

    // 로그아웃
    @PostMapping("/sign-out")
    public ResponseEntity<Void> signOut() {
        HttpHeaders httpHeaders = new HttpHeaders();
        cookieHelper.deleteJwtCookie(httpHeaders);
        return ResponseEntity.ok().headers(httpHeaders).body(null);
    }

    // 사용자 정보 조회
    @GetMapping("/me")
    public ResponseEntity<Optional<UserDto>> getLoggedUser() {
        return ResponseEntity.ok(userService.getLoggedUser());
    }

    // 구글 사용자 정보 조회 (JWT 포함)
    @GetMapping("/oauth2/me")
    public ResponseEntity<?> getOAuth2User(HttpServletRequest request) {
        String token = getTokenFromCookie(request);

        if (token == null) {
            return ResponseEntity.status(401).body(Collections.singletonMap("error", "Token not found"));
        }

        try {
            if (!tokenProvider.isValidToken(token)) {
                return ResponseEntity.status(401).body(Collections.singletonMap("error", "Invalid token"));
            }

            Authentication authentication = tokenProvider.getAuthentication(token);
            String email = authentication.getName();

            UserDto userDto = userService.getUser(email);
            if (userDto == null) {
                return ResponseEntity.status(404).body(Collections.singletonMap("error", "User not found"));
            }

            Map<String, Object> response = new HashMap<>();
            response.put("user", userDto);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonMap("error", "Internal Server Error"));
        }
    }

    private String getTokenFromCookie(HttpServletRequest request) {
        if (request.getCookies() != null) {
            return Arrays.stream(request.getCookies()).filter(cookie -> "Authorization".equals(cookie.getName()))
                    .map(Cookie::getValue).findFirst().orElse(null);
        }
        return null;
    }

    // 약관 불러오기
    @GetMapping("/terms")
    private ResponseEntity<List<TermsDto>> getAllTerms() {
        return ResponseEntity.ok(userService.getAllTerms());
    }

    // 약관 동의 정보 불러오기
    @GetMapping("/terms/{id}")
    public ResponseEntity<TermsAagreementDto> getUserIdTermsAgreement(@PathVariable("id") String id) {
        return ResponseEntity.ok(userService.getUserIdTermsAgreement(id));
    }

    @PutMapping("/update/personal/{userId}")
    public String updateUserInfo(@PathVariable("userId") String userId, @RequestBody UserInfoUpdate request) {

        UserDto userDto = request.getUserDto();
        TermsAagreementDto termsAgreementDto = request.getTermsAgreementDto();

        userDto.setUserId(userId);
        termsAgreementDto.setUserId(userId);

        userService.updateUserInfo(userDto);
        userService.updateTermsAgreement(termsAgreementDto);
        System.out.println("받은 데이터: " + request);

        return "정보가 성공적으로 업데이트되었습니다.";
    }

}
