package com.example.finalEclips.eclips.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalEclips.eclips.user.dto.EmailVerificationDto;
import com.example.finalEclips.eclips.user.service.EmailService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailVerificationController {

    private final EmailService emailService;

    // 이메일 인증 코드 전송
    @PostMapping("/send")
    public ResponseEntity<Map<String, String>> sendVerificationEmail(@RequestBody EmailVerificationDto request) {
        Map<String, String> response = new HashMap<>();
        try {
            emailService.sendVerificationEmail(request.getEmail());
            response.put("message", "이메일 전송 완료");
            return ResponseEntity.ok(response);
        } catch (MessagingException e) {
            response.put("message", "이메일 전송 실패");
            return ResponseEntity.badRequest().body(response);
        } catch (IllegalStateException e) {
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    // 인증 코드 검증
    @PostMapping("/verify")
    public ResponseEntity<Map<String, Boolean>> verifyEmail(@RequestBody EmailVerificationDto request) {
        Map<String, Boolean> response = new HashMap<>();
        boolean isVerified = emailService.verifyCode(request.getEmail(), request.getVerificationCode());

        response.put("success", isVerified);
        if (!isVerified) {
            response.put("message", false); // 인증 실패시 false 값 넣기
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }

    // 인증 상태 확인
    @PostMapping("/check")
    public ResponseEntity<Map<String, Boolean>> checkEmailVerification(@RequestBody EmailVerificationDto request) {
        Map<String, Boolean> response = new HashMap<>();
        boolean isVerified = emailService.isEmailVerified(request.getEmail());

        response.put("isVerified", isVerified);
        return ResponseEntity.ok(response);
    }

    // 이메일 인증 캐시 삭제
    @PostMapping("/clear")
    public ResponseEntity<Map<String, String>> clearVerification(@RequestBody EmailVerificationDto request) {
        Map<String, String> response = new HashMap<>();
        emailService.clearVerification(request.getEmail());

        response.put("message", "인증 캐시 삭제 완료");
        return ResponseEntity.ok(response);
    }

}
