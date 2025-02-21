package com.example.finalEclips.eclips.user.controller;

import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalEclips.eclips.config.jwt.TokenDto;
import com.example.finalEclips.eclips.helper.CookieHelper;
import com.example.finalEclips.eclips.user.dto.CreateBizUserDto;
import com.example.finalEclips.eclips.user.dto.CreateUserDto;
import com.example.finalEclips.eclips.user.dto.SignInDto;
import com.example.finalEclips.eclips.user.dto.UserDto;
import com.example.finalEclips.eclips.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CookieHelper cookieHelper;

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

}
