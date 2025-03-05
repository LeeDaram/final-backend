package com.example.finalEclips.eclips.mypage.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class StoreRegisterDto {

    @JsonIgnore
    private String userId;

    private String mainMenu;
    private int price;
    private String contact;
    private int industryId;
    private char takeout;
    private char delivery;
    private char wifi;
    private char pet;
    private char kid;
    private char parking;
    private char isActivate;

}
