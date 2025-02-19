package com.example.finalEclips.eclips.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.finalEclips.eclips.common.dto.LoginType;
import com.example.finalEclips.eclips.common.dto.UserRole;
import com.example.finalEclips.eclips.config.exception.AlreadyExistedUserException;
import com.example.finalEclips.eclips.config.property.ErrorMessagePropertySource;
import com.example.finalEclips.eclips.user.dto.CreateBizUserDto;
import com.example.finalEclips.eclips.user.dto.CreateUserDto;
import com.example.finalEclips.eclips.user.dto.UserDto;
import com.example.finalEclips.eclips.user.repository.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final ErrorMessagePropertySource errorMessagePropertySource;

    // 아이디 중복확인
    @Override
    public UserDto getUser(String id) {
        return userMapper.findUserById(id);
    }

    @Transactional
    @Override
    public void createUser(CreateUserDto createUserDto) {

        // 아이디 중복확인
        UserDto userId = getUser(createUserDto.getUserId());
        if (userId != null) {
            throw new AlreadyExistedUserException(errorMessagePropertySource.getAlreadyExistedUser());
        }

        // 비밀번호 암호화
        createUserDto.setPassword(passwordEncoder.encode(createUserDto.getPassword()));

        // 권한 설정: 개인회원
        createUserDto.setRole(UserRole.ROLE_USER);

        // 로그인 타입 설정
        createUserDto.setLoginType(LoginType.LOCAL);

        // 개인회원 정보 저장
        userMapper.saveUser(createUserDto);

    }

    @Transactional
    @Override
    public void createBizUser(CreateBizUserDto createBizUserDto) {

        // 아이디 중복확인
        UserDto userId = getUser(createBizUserDto.getUserId());
        if (userId != null) {
            throw new AlreadyExistedUserException(errorMessagePropertySource.getAlreadyExistedUser());
        }

        // 비밀번호 암호화
        createBizUserDto.setPassword(passwordEncoder.encode(createBizUserDto.getPassword()));

        // 권한 설정: 사업자
        createBizUserDto.setRole(UserRole.ROLE_BIZ);

        // 로그인 타입 설정
        createBizUserDto.setLoginType(LoginType.LOCAL);

        // 시도/시군구 ID 조회
        System.out.println("sidoName: " + createBizUserDto.getSidoName());
        System.out.println("sigunguName: " + createBizUserDto.getSigunguName());
        Integer sigunguId = userMapper.findSigunguId(createBizUserDto.getSidoName(), createBizUserDto.getSigunguName());

        if (sigunguId == null) {
            throw new IllegalArgumentException("유효하지 않은 시도/시군구 정보입니다.");
        }
        createBizUserDto.setSigunguId(sigunguId);

        // 사업자 정보 저장
        userMapper.saveUser(createBizUserDto);
        userMapper.saveBizUser(createBizUserDto);

    }

}
