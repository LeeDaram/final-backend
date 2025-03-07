package com.example.finalEclips.eclips.user.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.finalEclips.eclips.user.dto.CreateBizUserDto;
import com.example.finalEclips.eclips.user.dto.CreateOAuthUserDto;
import com.example.finalEclips.eclips.user.dto.CreateUserDto;
import com.example.finalEclips.eclips.user.dto.PasswordChangeDto;
import com.example.finalEclips.eclips.user.dto.TermsAagreementDto;
import com.example.finalEclips.eclips.user.dto.TermsDto;
import com.example.finalEclips.eclips.user.dto.UserDto;

@Mapper
public interface UserMapper {

    // 아이디 중복확인
    UserDto findUserById(String id);

    // 회원가입 - 구글
    void saveOAuthUser(CreateOAuthUserDto createOAuthUserDto);

    // 회원가입 - 공통
    void saveUser(CreateUserDto createUserDto);

    // 시군구 찾기
    Integer findSigunguId(@Param("sidoName") String sidoName, @Param("sigunguName") String sigunguName);

    // 회원가입 - 사업자
    void saveBizUser(CreateBizUserDto createBizUserDto);

    // 약관 불러오기
    List<TermsDto> findAllTerms();

    // 동의여부 저장하기
    void saveTermsAgreement(@Param("userId") String userId, @Param("isAgree") String isAgree);

    // 동의여부 불러오기
    TermsAagreementDto findUserIdTermsAgreement(String id);

    // 사용자 정보 업데이트
    void updateUserInfo(UserDto userDto);

    // 약관 동의 업데이트
    void updateTermsAgreement(TermsAagreementDto termsAagreementDto);

    // 비밀번호 변경
    void updatePassword(PasswordChangeDto passwordChangeDto);

    // 사용자 삭제
    void deleteUserById(String id);

    // 아이디 찾기
    UserDto findUserByName(@Param("name") String name, @Param("email") String email);

    // 비밀번호 찾기
    UserDto findUserPw(@Param("userId") String userId, @Param("name") String name, @Param("email") String email);
}
