package com.example.finalEclips.eclips.user.service;

import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.finalEclips.eclips.common.dto.LoginType;
import com.example.finalEclips.eclips.common.dto.UserRole;
import com.example.finalEclips.eclips.config.exception.AlreadyExistedUserException;
import com.example.finalEclips.eclips.config.jwt.TokenProvider;
import com.example.finalEclips.eclips.config.property.ErrorMessagePropertySource;
import com.example.finalEclips.eclips.helper.SecurityHelper;
import com.example.finalEclips.eclips.user.dto.CreateBizUserDto;
import com.example.finalEclips.eclips.user.dto.CreateUserDto;
import com.example.finalEclips.eclips.user.dto.SignInDto;
import com.example.finalEclips.eclips.user.dto.UserDto;
import com.example.finalEclips.eclips.user.repository.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final ErrorMessagePropertySource errorMessagePropertySource;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    // 아이디 중복확인
    @Override
    public UserDto getUser(String id) {
        return userMapper.findUserById(id);
    }

    // 개인회원 회원가입
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

    // 관리자 회원가입
    @Override
    public void createAdmin(CreateUserDto createUserDto) {

        // 아이디 중복확인
        UserDto userId = getUser(createUserDto.getUserId());
        if (userId != null) {
            throw new AlreadyExistedUserException(errorMessagePropertySource.getAlreadyExistedUser());
        }

        // 비밀번호 암호화
        createUserDto.setPassword(passwordEncoder.encode(createUserDto.getPassword()));

        // 권한 설정: 관리자
        createUserDto.setRole(UserRole.ROLE_ADMIN);

        // 로그인 타입 설정
        createUserDto.setLoginType(LoginType.LOCAL);

        // 개인회원 정보 저장
        userMapper.saveUser(createUserDto);

    }

    // 사업자 회원가입
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

    // jwt 로그인, 로그아웃
    @Override
    public String createToken(SignInDto signInDto) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    signInDto.getUserId(), signInDto.getPassword());

            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

            return tokenProvider.createToken(authentication);
        } catch (Exception ex) {
            throw new BadCredentialsException(errorMessagePropertySource.getBadCredentials());
        }
    }

    // 로그인된 사용자 정보
    @Override
    public Optional<UserDto> getLoggedUser() {
        Optional<String> loggedId = SecurityHelper.getLoggedId();
        return loggedId.map(userMapper::findUserById);
    }

}
