package com.example.finalEclips.eclips.user.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.finalEclips.eclips.common.dto.LoginType;
import com.example.finalEclips.eclips.common.dto.UserRole;
import com.example.finalEclips.eclips.config.exception.AlreadyExistedUserException;
import com.example.finalEclips.eclips.config.jwt.TokenProvider;
import com.example.finalEclips.eclips.config.property.ErrorMessagePropertySource;
import com.example.finalEclips.eclips.helper.SecurityHelper;
import com.example.finalEclips.eclips.user.dto.CreateBizUserDto;
import com.example.finalEclips.eclips.user.dto.CreateOAuthUserDto;
import com.example.finalEclips.eclips.user.dto.CreateUserDto;
import com.example.finalEclips.eclips.user.dto.PasswordChangeDto;
import com.example.finalEclips.eclips.user.dto.SignInDto;
import com.example.finalEclips.eclips.user.dto.TermsAagreementDto;
import com.example.finalEclips.eclips.user.dto.TermsDto;
import com.example.finalEclips.eclips.user.dto.UserDto;
import com.example.finalEclips.eclips.user.repository.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final ErrorMessagePropertySource errorMessagePropertySource;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final EmailService emailService;

    // 유저찾기 : 아이디
    @Override
    public UserDto getUser(String id) {
        return userMapper.findUserById(id);
    }

    // 개인회원 회원가입
    @Override
    public void createUser(CreateUserDto createUserDto) {

        // 아이디 중복 확인
        UserDto existingUser = getUser(createUserDto.getUserId());
        if (existingUser != null) {
            throw new AlreadyExistedUserException(errorMessagePropertySource.getAlreadyExistedUser());
        }

        // 이메일 인증 확인
        if (!emailService.isEmailVerified(createUserDto.getEmail())) {
            throw new IllegalStateException("이메일 인증이 완료되지 않았습니다.");
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(createUserDto.getPassword());
        createUserDto.setPassword(encodedPassword);

        // 권한 설정: 개인회원
        createUserDto.setRole(UserRole.ROLE_USER);

        // 로그인 타입 설정
        createUserDto.setLoginType(LoginType.LOCAL);

        // 개인회원 정보 저장
        userMapper.saveUser(createUserDto);

        // 약관 동의 여부 저장
        String agreementStatus = createUserDto.getIsAgree() ? "T" : "F";
        saveTermsAgreement(createUserDto.getUserId(), agreementStatus);

        // 이메일 인증정보 삭제
        emailService.clearVerification(createUserDto.getEmail());
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
    @Override
    public void createBizUser(CreateBizUserDto createBizUserDto) {

        // 아이디 중복확인
        UserDto userId = getUser(createBizUserDto.getUserId());
        if (userId != null) {
            throw new AlreadyExistedUserException(errorMessagePropertySource.getAlreadyExistedUser());
        }

        // 이메일 인증 확인
        if (!emailService.isEmailVerified(createBizUserDto.getEmail())) {
            throw new IllegalStateException("이메일 인증이 완료되지 않았습니다.");
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

        // 약관 동의여부 저장
        saveTermsAgreement(createBizUserDto.getUserId(), createBizUserDto.getIsAgree() ? "T" : "F");

    }

    // jwt 로그인, 로그아웃
    @Override
    public String createToken(SignInDto signInDto) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    signInDto.getUserId(), signInDto.getPassword());

            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

            UserDto user = getUser(signInDto.getUserId());

            return tokenProvider.createToken(authentication, user.getName(), user.getLoginType());
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

    // OAuth 회원가입 (구글 로그인)
    @Override
    public void saveOAuthUser(CreateOAuthUserDto createOAuthUserDto) {

        // 이메일 중복확인
        UserDto userEmail = getUser(createOAuthUserDto.getEmail());
        if (userEmail != null) {
            throw new AlreadyExistedUserException(errorMessagePropertySource.getAlreadyExistedUser());
        }

        // 권한설정
        createOAuthUserDto.setRole(UserRole.ROLE_USER);

        // 로그인 타입 설정
        createOAuthUserDto.setLoginType(LoginType.SOCIAL);

        // 저장
        userMapper.saveOAuthUser(createOAuthUserDto);
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = new DefaultOAuth2UserService().loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");
        if (email == null) {
            throw new OAuth2AuthenticationException("OAuth2 로그인 시 이메일 정보가 없습니다.");
        }

        UserDto existingUser = userMapper.findUserById(email);

        if (existingUser == null) {
            CreateOAuthUserDto newUser = new CreateOAuthUserDto();
            newUser.setEmail(email);
            newUser.setName(oAuth2User.getAttribute("name"));
            newUser.setRole(UserRole.ROLE_USER);
            newUser.setLoginType(LoginType.SOCIAL);
            userMapper.saveOAuthUser(newUser);
            saveTermsAgreement(newUser.getEmail(), "F");
            existingUser = userMapper.findUserById(email);
        }

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(existingUser.getRole().name())),
                oAuth2User.getAttributes(), "email");
    }

    // 약관 불러오기
    @Override
    public List<TermsDto> getAllTerms() {
        return userMapper.findAllTerms();
    }

    // 약관 동의
    @Override
    public void saveTermsAgreement(String userId, String isAgree) {
        userMapper.saveTermsAgreement(userId, isAgree);
    }

    // 약관동의 여부 불러오기
    @Override
    public TermsAagreementDto getUserIdTermsAgreement(String id) {
        return userMapper.findUserIdTermsAgreement(id);
    }

    // 사용자 정보 업데이트
    public void updateUserInfo(UserDto userDto) {
        userMapper.updateUserInfo(userDto);
    }

    // 약관 동의 정보 업데이트
    public void updateTermsAgreement(TermsAagreementDto termsAagreementDto) {
        userMapper.updateTermsAgreement(termsAagreementDto);
    }

    // 비밀번호 변경
    @Override
    public void updatePassword(PasswordChangeDto passwordChangeDto) {

        // 사용자 정보 불러오기
        UserDto user = userMapper.findUserById(passwordChangeDto.getUserId());
        if (user == null) {
            throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
        }
        if (!passwordEncoder.matches(passwordChangeDto.getCurrentPassword(), user.getPassword())) {
            throw new IllegalArgumentException("기존 비밀번호와 일치하지 않습니다.");
        }

        // 비밀번호 암호화
        passwordChangeDto.setNewPassword(passwordEncoder.encode(passwordChangeDto.getNewPassword()));

        // 저장
        userMapper.updatePassword(passwordChangeDto);
    }

    // 회원탈퇴
    @Override
    public void deleteUser(String userId, String password) {

        // 사용자 정보 불러오기
        UserDto user = userMapper.findUserById(userId);
        if (user == null) {
            throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
        }

        // 비밀번호 확인
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("기존 비밀번호와 일치하지 않습니다.");
        }

        userMapper.deleteUserById(userId);
    }

    // 소셜 사용자 삭제
    @Override
    public void deleteSocialUser(String userId) {
        userMapper.deleteUserById(userId);
    }

}
