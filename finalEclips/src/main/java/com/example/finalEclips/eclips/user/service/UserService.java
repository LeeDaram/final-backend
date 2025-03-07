package com.example.finalEclips.eclips.user.service;

import java.util.List;
import java.util.Optional;

import com.example.finalEclips.eclips.user.dto.CreateBizUserDto;
import com.example.finalEclips.eclips.user.dto.CreateOAuthUserDto;
import com.example.finalEclips.eclips.user.dto.CreateUserDto;
import com.example.finalEclips.eclips.user.dto.PasswordChangeDto;
import com.example.finalEclips.eclips.user.dto.SignInDto;
import com.example.finalEclips.eclips.user.dto.TermsAagreementDto;
import com.example.finalEclips.eclips.user.dto.TermsDto;
import com.example.finalEclips.eclips.user.dto.UserDto;

public interface UserService {

    // 아이디 중복확인
    UserDto getUser(String id);

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

    // 약관동의여부 조회
    TermsAagreementDto getUserIdTermsAgreement(String id);

    // 사용자 정보 업데이트
    void updateUserInfo(UserDto userDto);

    // 약관 동의 업데이트
    void updateTermsAgreement(TermsAagreementDto termsAagreementDto);

    // 비밀번호 변경
    void updatePassword(PasswordChangeDto passwordChangeDto);

    // 사용자 삭제
    void deleteUser(String userId, String password);

    // 소셜 사용자 삭제
    void deleteSocialUser(String userId);

    // 비밀번호 찾기
    boolean resetPassword(String userId, String name, String email);

}
