package com.example.finalEclips.eclips.config.security;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.example.finalEclips.eclips.user.dto.UserDto;
import com.example.finalEclips.eclips.user.repository.UserMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(final String id) {
        UserDto user = userMapper.findUserById(id);
        List<SimpleGrantedAuthority> grantedAuthorities = List.of(user.getRole()).stream()
                .map(role -> new SimpleGrantedAuthority(role.name())).toList();
        return new User(user.getUserId(), user.getPassword(), grantedAuthorities);
    }
}
