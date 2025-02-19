package com.example.finalEclips.eclips.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.finalEclips.eclips.user.dto.CreateBizUserDto;
import com.example.finalEclips.eclips.user.dto.CreateUserDto;
import com.example.finalEclips.eclips.user.dto.UserDto;

@Mapper
public interface UserMapper {

    // 아이디 중복확인
    UserDto findUserById(String id);

    // 회원가입 - 공통
    void saveUser(CreateUserDto createUserDto);

    // 시군구 찾기
    Integer findSigunguId(@Param("sidoName") String sidoName, @Param("sigunguName") String sigunguName);

    // 회원가입 - 사업자
    void saveBizUser(CreateBizUserDto createBizUserDto);
}
