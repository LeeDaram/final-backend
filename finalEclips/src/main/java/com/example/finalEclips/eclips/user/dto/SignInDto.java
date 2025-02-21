package com.example.finalEclips.eclips.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignInDto {

    @NotBlank
    @Size(max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9]{5,20}$")
    private String userId;

    @NotBlank
    @Size(min = 8, max = 16)
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*])[a-zA-Z\\d!@#$%^&*]{8,16}$")
    private String password;

}
