package com.example.finalEclips.eclips.user.dto;

import java.sql.Date;

import com.example.finalEclips.eclips.common.dto.LoginType;
import com.example.finalEclips.eclips.common.dto.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class UserDto {
    private String userId;

    @JsonIgnore
    private String password;

    private String name;

    private Date birthDate;

    private String phoneNumber;

    private String email;

    private UserRole role;

    private LoginType loginType;
}
