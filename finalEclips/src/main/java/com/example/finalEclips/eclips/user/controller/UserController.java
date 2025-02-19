package com.example.finalEclips.eclips.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalEclips.eclips.user.dto.CreateBizUserDto;
import com.example.finalEclips.eclips.user.dto.CreateUserDto;
import com.example.finalEclips.eclips.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입 : 개인회원
    @PostMapping("/sign-up/personal")
    public ResponseEntity<String> registerUser(@Valid @RequestBody CreateUserDto createUserDto) {
        userService.createUser(createUserDto);
        return ResponseEntity.ok("회원가입 성공");
    }

    // 회원가입 : 사업자
    @PostMapping("/sign-up/biz")
    public ResponseEntity<String> registerBizUser(@Valid @RequestBody CreateBizUserDto createBizUserDto) {
        userService.createBizUser(createBizUserDto);
        return ResponseEntity.ok("사업자 회원가입 성공");
    }

}
