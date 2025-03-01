package com.example.finalEclips.eclips.user.dto;

import lombok.Data;

@Data
public class PasswordChangeDto {
    private String userId;
    private String currentPassword;
    private String newPassword;
}
