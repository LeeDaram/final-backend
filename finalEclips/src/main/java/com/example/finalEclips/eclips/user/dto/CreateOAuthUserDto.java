package com.example.finalEclips.eclips.user.dto;

import com.example.finalEclips.eclips.common.dto.LoginType;
import com.example.finalEclips.eclips.common.dto.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateOAuthUserDto {

    @NotBlank
    @Size(max = 100)
    @Email
    private String userId;

    @NotBlank
    @Size(max = 20)
    private String name;

    @NotBlank
    @Size(max = 100)
    @Email
    private String email;

    private UserRole role;

    private LoginType loginType;
}
