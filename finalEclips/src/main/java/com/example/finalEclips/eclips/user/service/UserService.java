package com.example.finalEclips.eclips.user.service;

import com.example.finalEclips.eclips.user.dto.CreateBizUserDto;
import com.example.finalEclips.eclips.user.dto.CreateUserDto;
import com.example.finalEclips.eclips.user.dto.UserDto;

public interface UserService {

    // 아이디 중복확인
    UserDto getUser(String id);

    // 회원가입 - 공통
    void createUser(CreateUserDto createUserDto);

    // 회원가입 - 사업자
    void createBizUser(CreateBizUserDto createBizUserDto);

}
