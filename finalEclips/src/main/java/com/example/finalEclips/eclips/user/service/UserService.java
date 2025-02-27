package com.example.finalEclips.eclips.user.service;

import java.util.List;
import java.util.Optional;

import com.example.finalEclips.eclips.user.dto.CreateBizUserDto;
import com.example.finalEclips.eclips.user.dto.CreateOAuthUserDto;
import com.example.finalEclips.eclips.user.dto.CreateUserDto;
import com.example.finalEclips.eclips.user.dto.SignInDto;
import com.example.finalEclips.eclips.user.dto.TermsDto;
import com.example.finalEclips.eclips.user.dto.UserDto;

public interface UserService {

    // 아이디 중복확인
    UserDto getUser(String id);

    // 이메일 중복확인
    UserDto getUserEmail(String email);

    // 이메일 아이디 확인
    UserDto getUserByEmailAndUserId(String email, String userId);

    // 회원가입 - 구글
    void saveOAuthUser(CreateOAuthUserDto createOAuthUserDto);

    // 회원가입 - 개인회원
    void createUser(CreateUserDto createUserDto);

    // 회원가입 - 관리자
    void createAdmin(CreateUserDto createUserDto);

    // 회원가입 - 사업자
    void createBizUser(CreateBizUserDto createBizUserDto);

    // 로그인
    String createToken(SignInDto signInDto);

    // 로그인된 사용자 조회
    Optional<UserDto> getLoggedUser();

    // 약관
    List<TermsDto> getAllTerms();

    // 약관동의
    void saveTermsAgreement(String userId, String isAgree);

}
