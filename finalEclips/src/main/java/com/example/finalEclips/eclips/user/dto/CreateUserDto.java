package com.example.finalEclips.eclips.user.dto;

import java.sql.Date;

import com.example.finalEclips.eclips.common.dto.LoginType;
import com.example.finalEclips.eclips.common.dto.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserDto {

    @NotBlank
    @Size(max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9]{5,20}$")
    private String userId;

    @NotBlank
    @Size(min = 8, max = 16)
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*])[a-zA-Z\\d!@#$%^&*]{8,16}$")
    private String password;

    @NotBlank
    @Size(max = 20)
    private String name;

    private Date birthDate;

    @Pattern(regexp = "^[0-9]{10,13}$")
    private String phoneNumber;

    @NotBlank
    @Size(max = 100)
    @Email
    private String email;

    private UserRole role;

    private LoginType loginType;

}
