package com.example.finalEclips.eclips.user.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateBizUserDto extends CreateUserDto {

    @NotNull
    private long businessRegNo;

    @NotNull
    @Size(max = 300)
    private String storeName;

    @NotNull
    @Size(max = 500)
    private String address;

    @NotNull
    private int sigunguId;

    @NotNull
    private String sidoName;

    @NotNull
    private String sigunguName;

    @NotNull
    private String lat;

    @NotNull
    private String lng;

}
