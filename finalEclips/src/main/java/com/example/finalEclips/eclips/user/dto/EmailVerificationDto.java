package com.example.finalEclips.eclips.user.dto;

import lombok.Data;

@Data
public class EmailVerificationDto {

    private String email;
    private String verificationCode;

}
