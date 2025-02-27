package com.example.finalEclips.eclips.user.service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final Map<String, VerificationInfo> verificationCache = new ConcurrentHashMap<>();
    private final Set<String> verifiedEmails = ConcurrentHashMap.newKeySet(); // 인증 완료된 이메일 저장

    @Value("${spring.mail.username}")
    private String fromEmail;

    private static class VerificationInfo {
        String code;
        LocalDateTime expirationTime;

        VerificationInfo(String code, LocalDateTime expirationTime) {
            this.code = code;
            this.expirationTime = expirationTime;
        }
    }

    // 인증 코드 생성
    public String generateVerificationCode() {
        return String.valueOf(new Random().nextInt(900000) + 100000); // 6자리 코드 생성
    }

    // 인증 메일 전송
    public void sendVerificationEmail(String email) throws MessagingException {
        String verificationCode = generateVerificationCode();
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(3);

        // 기존 인증 정보 덮어씌우기 (중복 인증 방지)
        verificationCache.put(email, new VerificationInfo(verificationCode, expirationTime));

        // 이미 인증된 이메일인지 확인
        if (verifiedEmails.contains(email)) {
            throw new IllegalStateException("이미 인증된 이메일입니다.");
        }

        MimeMessage message = mailSender.createMimeMessage();
        message.setFrom(fromEmail);
        message.setRecipients(MimeMessage.RecipientType.TO, email);
        message.setSubject("착한녀석들 - 이메일 인증 코드");

        // 인증 코드를 직접 표시
        String emailContent = "<div style='font-family: Arial, sans-serif; max-width: 600px; margin: auto; padding: 20px; border: 1px solid #ddd; border-radius: 10px;'>"
                + "<h2 style='color: #4CAF50; text-align: center;'>이메일 인증 코드</h2>"
                + "<p style='font-size: 16px; color: #333;'>안녕하세요! 착한녀석들 서비스를 이용해 주셔서 감사합니다.<br>"
                + "아래의 인증 코드를 사이트에 입력해주세요.</p>"
                + "<div style='text-align: center; padding: 15px; background: #f4f4f4; border-radius: 5px; font-size: 32px; font-weight: bold; color: #333;'>"
                + verificationCode + "</div>" + "<p style='font-size: 14px; color: #777;'>* 인증 코드는 3분 동안 유효합니다.</p>"
                + "<hr style='border: 0; border-top: 1px solid #ddd;'>"
                + "<p style='font-size: 12px; color: #999; text-align: center;'>ⓒ 2025 착한녀석들. All rights reserved.</p>"
                + "</div>";

        message.setContent(emailContent, "text/html; charset=utf-8");

        mailSender.send(message);
    }

    // 인증 코드 검증
    public boolean verifyCode(String email, String code) {
        VerificationInfo verificationInfo = verificationCache.get(email);

        if (verificationInfo != null && verificationInfo.code.equals(code)
                && verificationInfo.expirationTime.isAfter(LocalDateTime.now())) {

            // 인증 성공 시 인증 완료 목록에 추가
            verifiedEmails.add(email);
            verificationCache.remove(email); // 인증 캐시에서도 삭제

            return true;
        }
        return false;
    }

    // 이메일 인증 여부 확인
    public boolean isEmailVerified(String email) {
        return verifiedEmails.contains(email);
    }

    // 인증 캐시 삭제 (수동 초기화)
    public void clearVerification(String email) {
        verificationCache.remove(email);
        verifiedEmails.remove(email);
    }
}
